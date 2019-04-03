package com.musicbar.second.backstage.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicbar.core.utils.PinYinDemo;
import com.musicbar.core.utils.StringUtil;
import com.musicbar.second.comm.base.Constants;
import com.musicbar.second.comm.config.ConfigProperties;
import com.musicbar.second.domain.TAttach;
import com.musicbar.second.domain.TGoodsInfo;
import com.musicbar.second.domain.TParameter;
import com.musicbar.second.domain.TUser;
import com.musicbar.second.mapper.TAttachMapper;
import com.musicbar.second.mapper.TParameterMapper;
import com.musicbar.second.mapper.TUserMapper;
/**
 * 用户管理
 * @author MECHREV
 *
 */
@Service
public class TUserService {
	@Autowired
	private TUserMapper mapper;
	
	@Autowired
    private TAttachMapper attachMapper;//图片
	
	@Autowired
	private ConfigProperties configProperties;

	/**
	 * 删除
	 * @param userId
	 * @return
	 */
	public int deleteByPrimaryKey(String userId) {
		return mapper.deleteByPrimaryKey(userId);
	}

	/**
	 * 新增
	 * @param user
	 * @return
	 */
	public int insert(TUser user) {
		return mapper.insert(user);
	}

	public int insertSelective(TUser record) {
		return mapper.insertSelective(record);
	}

	/**
	 * 根据id查询
	 * @param userId
	 * @return
	 */
	public TUser selectByPrimaryKey(String userId) {
		return mapper.selectByPrimaryKey(userId);
	}

	public int updateByPrimaryKeySelective(TUser record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 修改
	 * @param user
	 * @return
	 */
	public int updateByPrimaryKey(TUser user) {
		return mapper.updateByPrimaryKey(user);
	}
	
	/**
	 * 条件查询所有用户信息
	 * @return
	 */
	public List<TUser> selectAll(TUser user) {
		return mapper.selectAll(user);
	}
	

	/**
	 * 查询用户状态
	 * @return
	 */
	/*public List<TUser> selectState() {
		return mapper.selectState();
	}*/
	
	public List<TParameter> selectState() {
		return mapper.selectState();
	}
	
	/**
	 * 添加图片
	 * @return
	 */
    public int insert(TAttach record) {
    	record.setId(StringUtil.getUUIDValue());
        return attachMapper.insert(record);
    }

    /**
     * 新增和修改用户 
     * -1手机号已存在 -2身份证已存在 
     * @param user
     * @param fileUrl
     * @return
     */
    public int saveOrUpdate(TUser user) {
    	String _userId = user.getUserId();
    	int success = 0;
    	TUser u = new TUser();
		u.setUserMobile(user.getUserMobile());
		u.setUserId(user.getUserId());
		if(mapper.selectUserMobileAndIdcard(u) > 0) {
			return -1;//手机号已存在
		}
		u.setUserMobile(null);
		u.setUserIdcard(user.getUserIdcard());
		if(mapper.selectUserMobileAndIdcard(u) > 0) {
			return -2;//身份证已存在
		}
		//判断新增修改
		if(_userId != null && !_userId.isEmpty()) {
			success = mapper.updateByPrimaryKey(user);
		}else {
			user.setUserId(StringUtil.getUUIDValue());
            String pwd = user.getUserIdcard().substring(12, 18);//截取密码
            pwd = new Sha256Hash(pwd.toString(),Constants.YD).toHex();//密码加密        
            user.setUserPassword(pwd);//获取身份证，截取后6位，给密码赋值
			String pid = user.getUserId();
			success = mapper.insert(user);
		}
		//添加图片
		List<TAttach> attachs = user.getAttach();
		if (attachs != null && !attachs.isEmpty()) {
			String path = configProperties.getPath();
			if(path.indexOf("file:") != -1) {
				path = path.substring(path.indexOf(":")+2);//得到去除file：后的真实根路径
			}
			for (TAttach attach : attachs) {
				attach.setPkid(user.getUserId());
				attach.setId(StringUtil.getUUIDValue());
				//如果是修改操作，则可能需要删除图片
				if(_userId != null && !_userId.isEmpty()) {
					String oldfileUel = attachMapper.selectFileUelByRemarkAndPkid(user.getUserId(),attach.getFileRemark());
					//删除数据库数据
					attachMapper.deleteByRemarkAndPkid(user.getUserId(),attach.getFileRemark());
					//删除本地文件
					if(attach != null && attach.getFileUel() != null) { 
						String delPath = path + oldfileUel;
						File delF = new File(delPath);
						if(!delF.delete()){//如果删除失败，有可能是此文件正在被访问中，设置为服务停止时删除
							delF.deleteOnExit();
						}
					}
				}
			}
    		attachMapper.insertAttach(user.getAttach());//添加数据库数据
    	}
		return success;
	}
    
	public TUser selectMobilePwd(TUser user) {
		return mapper.selectMobilePwd(user);
	}

	public TUser selectMobile(String userMobile) {
		return mapper.selectMobile(userMobile);
	}

	/**
	 * 
	 * 批量删除
	 * @param userId
	 * @return
	 */
	public int deleteAll(List<String> userId) {
		return mapper.deleteAll(userId);
	}

	/**
	 * 在职，离职
	 * @param user
	 * @return
	 */
	public int changeState(Map<String, Object> map) {
		return mapper.changeState(map);
	}

	public TUser  selectNamePwd(TUser user) {
		return mapper.selectByNamePwd(user);
		
	}

}
