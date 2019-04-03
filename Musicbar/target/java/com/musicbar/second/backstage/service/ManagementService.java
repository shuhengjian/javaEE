package com.musicbar.second.backstage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicbar.core.base.Result;
import com.musicbar.core.utils.DateUtils;
import com.musicbar.second.mapper.TOrdersMapper;

@Service("managementService")
public class ManagementService {
	@Autowired
	private TOrdersMapper tOrdersMapper;
	/**
     * 统计订单总数量，总销售额，总销量
    * @param map 开始时间，结束时间
    * @return
    */
	public Map<String, String> selectTotal(Map<String, String> map) {
		return tOrdersMapper.selectTotal(map);
	}
	  /**
     * 根据时间按商品分类名称与商品名称统计销售量
     * @param map 开始时间，结束时间
     * @return
     * @throws Exception
     */
	public List<Map<String, Object>> selectNumTotalByTypeNameAndGoodsName(Map<String, String> map){
		return tOrdersMapper.selectNumTotalByTypeNameAndGoodsName(map);

	}
 /**
    * 查询订单的最大时间与最小
    * @return
    */
   public Map<String,String> selectMaxAndMinTime(){
	   return tOrdersMapper.selectMaxAndMinTime();
   }
   
   /**
    * 往年对比查询一年的总和,
    * @param time
    * @return
    */
   public List<Map<String, String>> selectFormerYears(String minTime,String maxTime){
	   int _minTime = Integer.parseInt(minTime);
		int _maxTime = Integer.parseInt(maxTime);
		List<Map<String, String>> list = new ArrayList<>();
		while(_minTime <= _maxTime) {
			list.add(tOrdersMapper.selectFormerYears(String.valueOf(_minTime)));
			_minTime++;
		}
		return list;
   }

   /**
	 *多年月对比 查询多年的12个月数据
	 * @param minTime 开始年份
	 * @param maxTime 结束年份
	 * @return
	 */
	public List<List<Map<String, String>>> selectTowYears(String minTime, String maxTime){
		List<List<Map<String, String>>> towTotal = new ArrayList<>();
		int intMinTime = Integer.parseInt(minTime);
		while(intMinTime <= Integer.parseInt(maxTime)) {
			String _time = String.valueOf(intMinTime).concat("-01-01");
			Date minYear = DateUtils.getStringDateToDate(_time);
			minYear = DateUtils.addYear(minYear, 1);
			minYear = DateUtils.addMonth(minYear, -1);
			towTotal.add(this.commMonth(minYear));
			intMinTime++;
		}
		return towTotal;
	}
   
   /**
	 * 根据传入的结束日期，统计前12个月的数据
	 * @param time
	 * @return
	 */
	public List<Map<String, String>> commMonth(Date time){
		Map<String, String> map = new HashMap<>();
		Map<String, String> m;
		List<Map<String, String>> total = new ArrayList<>();
		String startTime = null;
		String endTime = null;
		int i = -1;
		while (i >= -12) {
			time = DateUtils.getLastDateOfMonth(time);
			startTime = DateUtils.fotmatDateTOymd(DateUtils.getFirstDateOfMonth(time));
			endTime = DateUtils.fotmatDateTOymd(time);
			time = DateUtils.addMonth(time, -1);
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			m = tOrdersMapper.selectTotal(map);
			m.put("startTime", startTime.substring(0,startTime.lastIndexOf("-")));
			total.add(m);
			--i;
		}
		return total;
	}
	/**
	 * 根据传入的结束日期，查询本月的订单数，销售额
	 * @param month
	 * @return
	 */
	public List<Map<String, String>>  commDay(String month) {
		String ymd = month.concat("-01");
		int i = -1;
		String startTime = null;
		String endTime = null;
		Map<String, String> m;
		Map<String, String> map = new HashMap<>();
		List<Map<String, String>> total = new ArrayList<>();
		Date time = DateUtils.getLastDateOfMonth(DateUtils.getStringDateToDate(ymd));//该月最后一天的日期
		String eDay = DateUtils.fotmatDateTOymd(time);//获取该月最后一天的日期的字符串
		while (i >= (0-(Integer.parseInt(eDay.substring(eDay.lastIndexOf("-") + 1))))) {
			endTime = DateUtils.fotmatDateTOymd(time);
			time = DateUtils.addDay(time, -1); //拿到最后一天后减一天
			startTime = DateUtils.fotmatDateTOymd(time);
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			m = tOrdersMapper.selectTotal(map);
			m.put("startTime", startTime);
			total.add(m);
			--i;
		}
		return total;
	}
}
