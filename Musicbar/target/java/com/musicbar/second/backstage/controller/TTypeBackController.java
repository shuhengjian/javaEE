package com.musicbar.second.backstage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.musicbar.core.annotation.LoggerAnnotation;
import com.musicbar.core.redis.RedisUtils;
import com.musicbar.core.uploads.FileUpload;
import com.musicbar.core.uploads.ImgCompress;
import com.musicbar.second.backstage.service.TAttachService;
import com.musicbar.second.backstage.service.TParameterService;
import com.musicbar.second.backstage.service.TTypeService;
import com.musicbar.second.comm.config.ConfigProperties;
import com.musicbar.second.comm.param.ParamTransformation;
import com.musicbar.second.domain.TAttach;
import com.musicbar.second.domain.TParameter;
import com.musicbar.second.domain.TType;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/backstage")
public class TTypeBackController  {
	@Autowired
	private TTypeService ttyService;
	@Autowired
	private ConfigProperties configProperties;
	@Autowired
	private ParamTransformation paramTransformation;
	@Autowired
	private TAttachService tAttachService;
	@Autowired
	private TParameterService tParameterService;//参数
	@Autowired
	private RedisUtils redisUtils;//缓存
	 /**
	  * 进入分类列表页面
	  * @return
	  */
	@RequestMapping("/query_list")
	@LoggerAnnotation(begin="进入查询分类列表方法",end="查询商品分类方法结束")
	public ModelAndView list() {
		ModelAndView mv  = new ModelAndView("backstage/pages/type/list");
		return mv;
	}
	
	
	/**
	 * 查询分类状态
	 * @return
	 */
	@RequestMapping("type_state")
	@ResponseBody
	public String selectState() { 
		List<TParameter> list = tParameterService.selectTypeState();
		return JSONArray.fromObject(list).toString(); 
	}
	 
	
	/**
	 * 
	 * @param type
	 * @param pageNum
	 * @return
	 */
	
	@RequestMapping("/type_query")
	@LoggerAnnotation(begin="进入查询分类列表方法",end="查询商品分类方法结束")
	@ResponseBody
	public String queryAll(TType type,int pageNum) {
		int pageSize=configProperties.getPageSize();//定义每页显示ji条记录
		PageHelper.startPage(pageNum,pageSize);
		List<TType> list = ttyService.findAll(type);
		//查询所有
		PageInfo<TType> page = new PageInfo<>(list);
		return JSONArray.fromObject(page).toString();
	}
	
	/**
	 * 启用，停用
	 * @param type
	 * @return
	 */
	@RequestMapping("/type_updateState")
	@ResponseBody
	public String updateState(TType type) {
		JSONObject json=new JSONObject();
		int count = ttyService.updateState(type);
		if(count == 1) {
		//	json.put("code", 200);
			json.put("msg", "操作成功");
		}else {
		//	json.put("code", 200);
			json.put("msg", "操作成功");
		}
		return json.toString();
	}
	/**
	 * 进入编辑页面
	 * @param typeId
	 * @return
	 */
	@RequestMapping("/type_open")
	@LoggerAnnotation(begin="进入分类添加页面方法",end="查询分类添加页面方法结束")
	public ModelAndView open(String typeId) {
		ModelAndView mv = new ModelAndView("backstage/pages/type/edit");
		if(typeId != null && !typeId.isEmpty()) {
			TType type = ttyService.selectTypeById(typeId);
			mv.addObject("type",type);
		}
		//查询分类状态
		List<TParameter> stateList = tParameterService.selectTypeState();
		mv.addObject("stateList",stateList);
		
		return mv;
		
	}
	
	
	/**
	 * 添加OR修改
	 * @param request
	 * @param type
	 * @return
	 */
	@RequestMapping("/type_save")
	@ResponseBody
	@LoggerAnnotation(begin="进入查询分类添加or修改的方法",end="查询商品分类添加or修改的方法结束")
	public String save(HttpServletRequest request,TType type,
			@RequestParam("file") MultipartFile file) {
		 TAttach attach = new TAttach();//创建资源类 
		//创建文件上传
		 FileUpload fileUpload = new FileUpload();
		JSONObject json=new JSONObject();
		 String fileUrl = request.getParameter("img");
		 String msg = type.getTypeId() != null && ! type.getTypeId().isEmpty()? "修改":"添加";
		 if(file !=null && file.getSize() > 0) {
			 fileUpload.setSaveFilePath(configProperties.getPath());//根路径
			 fileUpload.setPath(configProperties.getTypePath());//上传文件的相对路径
			 fileUpload.setAllowFiles(".jpg .jpeg .gif .png .bmp");//文件上传类型
			 fileUpload.fileUpload(file, fileUpload);
		 }
		 attach.setFileName(type.getTypeName());
		 if(fileUpload.isIs()) {
			 attach.setFileSuffix(fileUpload.getSuffix());
			 attach.setFileUel(fileUpload.getPath()+fileUpload.getFileName());
		 }
		 type.setAttch(attach);
		 int success = ttyService.saveOrUpdate(type, attach);
		 try {
				//压缩图片
				String url = fileUpload.getSaveFilePath()+fileUpload.getPath()+fileUpload.getFileName();
				ImgCompress imgCom = new ImgCompress(url);
				imgCom.resize(220,220,url);
			} catch (IOException e) {
				e.printStackTrace();
			}

		 if(success > 0) {
	       	  json.put("msg", msg+"成功");
	       	  json.put("flag", true);
		 } else {
			 if(success == 0){
	        	 json.put("msg", msg+"失败");
	        	 json.put("flag", false);
			 }else if(success == -1) {
	        	 json.put("msg", "分类名称已存在");
	        	 json.put("flag", false);
	         }else if(success == -2) {
	        	 json.put("msg", "分类排序号已存在");
	        	 json.put("flag", false);
	         }
		 }
		return json.toString();
	}
	
	/** 
	 * 图片上传 sss
	 * @param servletRequest
	 * @param file
	 * @return
	 * @throws IOException
	 */
	/*@RequestMapping("/upload_type_img")
    @ResponseBody
    public Map<String, Object> upload(HttpServletRequest servletRequest,
                         @RequestParam("file") MultipartFile file
                         ) throws IOException {
    	FileUpload fileUpload = new FileUpload();
        //如果文件内容不为空，则写入上传路径
        if (!file.isEmpty()) {
        	//设置上传的根路径
        	fileUpload.setSaveFilePath(configProperties.getPath());
            //获得上传文件路径
            String path = fileUpload.getSaveFilePath();
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //设置文件上传路径
            fileUpload.setPath(configProperties.getTypePath());
            String goods = fileUpload.getPath();
            goods +=sdf.format(new Date())+"/";
            String root = path + goods;
            //上传文件名 
            String filename = file.getOriginalFilename();
            String suffix = filename.substring(filename.lastIndexOf("."));//扩展名
			String newName = StringUtil.getUUIDValue() + suffix;//新的文件名
            goods +=newName;
            File filepath = new File(path, goods);
            
            //判断路径是否存在，没有就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            
            //将上传文件保存到一个目标文档中
            File file1 = new File(root + File.separator + newName);
            //System.out.println(file1);
           file.transferTo(file1);
            Map<String, Object> res = new HashMap<>();
            //返回的是一个url对象
            res.put("url", goods);
            return res;

        } else {
        	Map<String, Object> res = new HashMap<>();
        	res.put("msg", "fale");
            return res;
        }
    }*/
	
	/**
	 * 删除
	 * @param goodsId
	 * @return
	 */
	@RequestMapping("/type_delete")
	@ResponseBody
	public String delete(String typeId) {
		JSONObject json=new JSONObject();
		//Integer tyId = Integer.parseInt(typeId);
		int count = ttyService.deleteById(typeId);
		int num = tAttachService.deleteById(typeId);
		if(count > 0 && num > 0) {
			//json.put("code", 200);
			json.put("msg", "删除成功");
		}else {
			//json.put("code", 500);
			json.put("msg", "删除失败");
		}
		return json.toString();
	}
	/**
	 * 批量删除
	 */
	@RequestMapping("/type_deleteAll")
	@ResponseBody
	public String deleteAll(String id) {
		JSONObject json=new JSONObject();
		int count = 0,num = 0,um=0;
		if(!id.equals("")) {
			String[] split = id.split(",");
			List<String> list = new ArrayList<String>();
			for(String string : split) {
				list.add(string);
				/*Integer typeId = Integer.parseInt(string);
				count = ttyService.selectCount(typeId);
				if(count > 1){
					json.put("msg", "删除的分类已存在商品，不能删除");
					break;
				}*/
			}
			count = ttyService.deleteAll(list);
			num = tAttachService.deleteAll(list);
		}else {
			count = -1;
			num = -1;
		}
		if(count > 0 ) {
			//json.put("code", 200);
			json.put("msg", "操作成功");
		}/*else if(count > 1) {
			//json.put("code", 500);
			json.put("msg", "删除的分类已存在商品,");
		}*/else if(count == -1) {
			//json.put("code", 500);
			json.put("msg", "请勾选要删除的记录");
		}else {
		//	json.put("code", 500);
			json.put("msg", "操作失败");
		}
		return json.toString();
	}
	
	
}
