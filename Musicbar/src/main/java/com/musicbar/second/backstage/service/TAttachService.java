/**   
 * 功能描述：
 * @Package: com.musicbar.second.front.service 
 * @author: shj 
 * @date: 2019年3月12日 下午2:51:31 
 */
package com.musicbar.second.backstage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicbar.second.domain.TAttach;
import com.musicbar.second.mapper.TAttachMapper;

/**    
* @ClassName: TAttachServic.java
* @Description: 资源service
*/
@Service
public class TAttachService {
	@Autowired
	private TAttachMapper tAttachMapper;
	
    /**
     *根据关联的Pkid查询资源 
     */
    public TAttach selectByPkID(String id) {
    	TAttach att=new TAttach();
    	att= tAttachMapper.selectByPkID(id);
    	return att;
    }
   
    /**
     *查询所有上架的商品图片 
     */
   public  List<TAttach> selectAllGoodsAttach(Map<String, Object> obj){
	   List<TAttach> list =new ArrayList<>();
	   list= tAttachMapper.selectAllGoodsAttach(obj);
	   return list;
   }


   /**
    * 删除
    * @param id
    * @return
    */
   public int deleteById(String id) {
	   return tAttachMapper.deleteByPrimaryKey(id);
   }
   /**
    * 批量删除
    * @param id
    * @return
    */
   public int deleteAll(List<String> id) {
	   return tAttachMapper.deleteAll(id);
   }
   
   public int insertSelective(TAttach tAttach) {
	   return tAttachMapper.insertSelective(tAttach);
   }
   
   /**
    * 
    *根据关联的Pkid查询图片集合
    */
   public List<TAttach> selectListByPkID(String id){
	   return tAttachMapper.selectListByPkID(id);
   }
}
