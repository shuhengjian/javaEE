package com.musicbar.second.comm.param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.musicbar.core.redis.RedisUtils;
import com.musicbar.second.domain.TParameter;

import net.sf.json.JSONObject;
@Component
public class ParamUtil {
	@Autowired
	private ParamService paramService;
	@Autowired
	private RedisUtils redisUtils;
	/**
	 * 参数
	 */
	private final String PARAMETER = "parameter";
	JSONObject json = new JSONObject();
	   /**
     * 	将参数按类型分类
     *
     * @param params
     * @return
     */
    private Map<String, List<TParameter>> initIfEmpty() {
    	
        //List<TParameter> params = paramService.slectAllTParameter(); //查询所有分类参数
        List<String> params = redisUtils.getCacheList(PARAMETER,0,
        		redisUtils.getCacheListSize(PARAMETER));
        List<TParameter> listParams = new ArrayList<>();
        for (String string : params) {
        	JSONObject _string = json.fromObject(string);
        	listParams.add((TParameter) json.toBean(_string, TParameter.class));
		}
        Map<String, List<TParameter>> parsed = new HashMap<String, List<TParameter>>(128);

        for (TParameter p : listParams) { //循环取出所有的参数数据
            String type = p.getParaType();//取出类型
            List<TParameter> list = parsed.get(type);//通过类型获取该类型的list集合

            if (list == null) {//如果该类型的list集合不存在，则创建list
                list = new ArrayList<TParameter>();
            }

            list.add(p);//将该类型的参数数据放入list中
            parsed.put(type, list);
        }

        return parsed;//返回map集合（key为类型，值为参数数据）
    }
    
    /**
     * 按内部值查找显示值
     * @param val
     * @param codeName
     * @return
     * @author
     * @since 2014-4-28
     */
    public String getKeyByVal(String paraType, String val) {
        Map<String, List<TParameter>> parsed = initIfEmpty();//返回了按类型（para_type）分类的参数集合

        if (val.isEmpty()) {
            return val;
        }

        List<TParameter> list = parsed.get(paraType);

        if (list == null) {
            return null;
        }

        for (TParameter p : list) {
            if (p.getParaNo().equals(val)) {//判断该参数编码是否与传入的编码是否相等
                return p.getParaVal(); //相等则返回该编号对应的参数值
            }
        }

        return null;
    }
    
    /**
     	* 返回list集合
     * @param paraType 类型
     * @return
     */
    public List<TParameter> getListByKey(String paraType) {
    	Map<String, List<TParameter>> parsed = initIfEmpty();//返回了按类型（para_type）分类的参数集合
        return parsed.get(paraType);
    }
    
    /**
     * 更新参数缓存
     * @param codeType
     */
    public void updateParaRedis(String codeType) {
    	List<TParameter> list = paramService.slectAllTParameter();
		
		List<String> list1 = new ArrayList<>();
		for(int i=0;i<list.size();i++) {
			TParameter tParameter =  list.get(i);
			JSONObject json1 = JSONObject.fromObject(tParameter);
			list1.add(json1.toString());	
		}
		redisUtils.deleteCache(PARAMETER);
		redisUtils.setCacheList(PARAMETER, list1);
    }
    
}
