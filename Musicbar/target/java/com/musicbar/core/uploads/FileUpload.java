package com.musicbar.core.uploads;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.musicbar.core.utils.StringUtil;


public class FileUpload {
	// 输出文件地址
	private String url = "";
	// 上传文件名
	private String fileName = "";
	// 提示
	private String state = "";
	// 文件类型
	private String type = "";
	// 原始文件名
	private String originalName = "";
	// 文件大小
	private long size;
	//文件标题
	private String title = "";

	// 上传的根路径(格式 file:c:\\uploads/ 最后需加上/)
	private String saveFilePath = "";
	
	//上传路径（自己需要加的目录）(格式  type/ 如果存在最后需加上/);
	private String path = "";
	
	// 文件允许格式
	private String allowFiles = ".rar, .doc, .docx, .zip, .pdf,.txt, .swf, .wmv, .gif, .png, .jpg, .jpeg, .bmp" ;
	// 文件大小限制，单位Byte
	private long maxSize = 1024000L;
	
	//文件后缀名
	private String suffix;
	
	// 上传是否成功
	private boolean is = false;
	/**
	 * 
	 * @param file MultipartFile类型文件
	 * @param fileUpload FileUpload类，需先set属性（必须属性saveFilePath,path）
	 * @return
	 */
	public void  fileUpload(MultipartFile file,FileUpload fileUpload) {
		//创建输入流输出流
		BufferedInputStream fis = null;//输入流
		FileOutputStream fos = null;//输出流
		if(file.getSize() > fileUpload.getMaxSize()) {//文件大小超过限制
			fileUpload.setState("文件大小超过限制，最多"+fileUpload.getMaxSize()+"M!");
			fileUpload.setIs(false);
		}else if(file.getSize() != 0 && //file不为空 截取后缀名，判断格式是否正确
				fileUpload.getAllowFiles().indexOf(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."))) == -1) {
			fileUpload.setState("文件格式不正确!");
			fileUpload.setIs(false);
		}else if (file != null && file.getSize()>0){//代表有文件上传
			String originalName =file.getOriginalFilename();//文件原名
			fileUpload.setOriginalName(originalName);
			
			fileUpload.setSuffix(originalName.substring(originalName.lastIndexOf(".")));//扩展名
			
			fileUpload.setSize(file.getSize());//文件大小
			
			String fileName = StringUtil.getUUIDValue()+fileUpload.getSuffix();//上传文件名(获取的uuid加上后缀名)
			fileUpload.setFileName(fileName);
			
			String root = fileUpload.getSaveFilePath();//得到根目录
			String path = fileUpload.getPath();//得到上传目录
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化时间
			
			path += sdf.format(new Date())+"/"; //上传目录+格式化的时间目录
			
			fileUpload.setUrl(root + path + fileName);//输出文件地址
			fileUpload.setPath(path);//处理后重新更新相对路径
			
			File f = new File(root + path); //真实路径
			if(!f.exists()){//如果路径不存在，则要创建此路径，每天上传的第一张图像，必须要创建当前的目录
				f.mkdirs();
			}
			try {
				fis = new BufferedInputStream(file.getInputStream());//输入流  用文件得到输入流
				fos = new FileOutputStream(root + path + fileName); //创建输出流
				byte[]bys = new byte[1024];//创建字符数组，每次传输的大小
				int len = 0;//长度为0
				while((len = fis.read(bys)) != -1){//结束时fis.read()返回-1，未结束返回读取的字节数
					fos.write(bys,0,len);//在bys中输出数据，从0开始，读取len个字节
				}
				fos.flush();//把缓冲区的数据强行输出
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fileUpload.setState("文件上传失败");
				fileUpload.setIs(false);
			}
			fileUpload.setState("文件上传成功");
			fileUpload.setIs(true);
		}
	}
	/**
	 * @param fileUpload FileUpload类（获取根目录路径）
	 * @param oldFileUrl 需要删除的文件相对路径 
	 */
	public void delete(FileUpload fileUpload,String oldFileUrl) {
		if(oldFileUrl != null && !oldFileUrl.isEmpty()){
			String delPath = fileUpload.getSaveFilePath() + oldFileUrl;
			File delF = new File(delPath);
			if(!delF.delete()){//如果删除失败，有可能是此文件正在被访问中，设置为服务停止时删除
				delF.deleteOnExit();
			}
		}
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 得到 根路径
	 * @return
	 */
	public String getSaveFilePath() {
		return saveFilePath;
	}
	/**
	 * //去除file：后的真实根路径
	 * @param saveFilePath
	 */
	public void setSaveFilePath(String saveFilePath) {
		if(saveFilePath.indexOf("file:") != -1) {
			saveFilePath = saveFilePath.substring(saveFilePath.indexOf(":")+2);//得到去除file：后的真实根路径
		}
		this.saveFilePath = saveFilePath;
	}
	public String getAllowFiles() {
		return allowFiles;
	}
	public void setAllowFiles(String allowFiles) {
		this.allowFiles = allowFiles;
	}
	public long getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(long maxSize) {
		this.maxSize = maxSize;
	}
	/**
	 * 得到上传目录
	 * @return
	 */
	public String getPath() {
		return path;
	}
	/**
	 * 
	 * @param path 上传路径
	 * （自己需要加的目录，会加在数据库字段里面去）
	 * (格式  type/ 如果存在最后需加上/);
	 */
	public void setPath(String path) {
		this.path = path;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public boolean isIs() {
		return is;
	}
	public void setIs(boolean is) {
		this.is = is;
	}
	
}
