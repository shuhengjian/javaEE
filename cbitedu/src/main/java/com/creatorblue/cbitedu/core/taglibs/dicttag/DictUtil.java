package com.creatorblue.cbitedu.core.taglibs.dicttag;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.creatorblue.cbitedu.core.utils.StringHelpers;
import com.creatorblue.cbitedu.system.domain.TsysDict;
import com.creatorblue.cbitedu.system.service.TsysDictService;


/**
 * <p> Title:封装字典传递的辅助类 </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2015</p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
@Component
public class DictUtil {
    @Autowired
    private TsysDictService tsysDictService;
    public DictUtil() {
    }

    /**
     * 按内部值查找显示值
     * @param val
     * @param codeName
     * @return
     * @author
     * @since 2014-4-28
     */
    public String getKeyByVal(String codeType, String val) {
        Map<String, List<TsysDict>> parsed = initIfEmpty();

        if (StringHelpers.isNull(val)) {
            return val;
        }

        List<TsysDict> list = parsed.get(codeType);

        if (list == null) {
            return null;
        }

        for (TsysDict p : list) {
            if (p.getDictCode().equals(val)) {
                return p.getDictValue();
            }
        }

        return null;
    }

    /**
     * 按显示值查找内部值
     *
     * @param key
     * @param codeName
     * @return
     * @author
     * @since 2014-4-28
     */
    public String getValByKey(String codeType, String key) {
        Map<String, List<TsysDict>> parsed = initIfEmpty();

        if (StringHelpers.isNull(key)) {
            return key;
        }

        List<TsysDict> list = parsed.get(codeType);

        if (list == null) {
            return null;
        }

        for (TsysDict p : list) {
            if (p.getDictValue().equals(key)) {
                return p.getDictCode();
            }
        }

        return null;
    }

    /**
     * 取某一类型的编码
     *
     * @param type
     * @return
     */
    public List<TsysDict> getByType(String type) {
        Map<String, List<TsysDict>> parsed = initIfEmpty();

        List<TsysDict> list = parsed.get(type);

        return list;
    }

    /**
     * 将所有编码按类型分类
     *
     * @param params
     * @return
     */
    private Map<String, List<TsysDict>> initIfEmpty() {
        List<TsysDict> params = tsysDictService.selectPageTsysDict();
        Map<String, List<TsysDict>> parsed = new HashMap<String, List<TsysDict>>(128);

        for (TsysDict p : params) {
            String type = p.getDictType();
            List<TsysDict> list = parsed.get(type);

            
            if (list == null) {
                list = new ArrayList<TsysDict>();
            }

            list.add(p);
            parsed.put(type, list);
        }

        return parsed;
    }
}
