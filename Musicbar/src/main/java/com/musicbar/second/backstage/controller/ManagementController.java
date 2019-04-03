package com.musicbar.second.backstage.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.musicbar.core.annotation.LoggerAnnotation;
import com.musicbar.core.base.Result;
import com.musicbar.core.utils.DateUtils;
import com.musicbar.second.backstage.service.ManagementService;
import com.musicbar.second.comm.param.ParamUtil;

@Controller
@RequestMapping("/backstage")
public class ManagementController {
	@Autowired
	private ManagementService managementService;
	@Autowired
	private ParamUtil paramUtil;
	
	 /**
     * 进入统计页面
    * @param map
    * @return
    */
	@RequestMapping("/manageQuery")
	@LoggerAnnotation(begin="进入统计页面开始",end="进入统计页面结束")
	public ModelAndView query(ModelAndView moView) {
		moView.setViewName("backstage/pages/management/list");
		return moView;
	}
	
	 /**
     * 统计订单总数量，总销售额，总销量
     * 如果存在月份，则统计当月的数据，如果存在年份，则统计当前年的12个月数据
    * @param map
    * @return
    */
	@RequestMapping("/manageQueryTotal")
	@LoggerAnnotation(begin="统计订单总数量，总销售额，总销量开始",end="统计订单总数量，总销售额，总销量结束")
	@ResponseBody
	public String queryTotal(String year,String month) {
		Map<String, String> map = new HashMap<>();
		Map<String, String> m;
		List<Map<String, String>> total = new ArrayList<>();
		String startTime = null;
		String endTime = null;
		int i = -1;
		if(month == null || month.isEmpty()) {
			Date time;
			if(year != null && !year.isEmpty()) {
				String _time = year.concat("-01-01");
				time = DateUtils.getStringDateToDate(_time);
				time = DateUtils.addYear(time, 1);
				time = DateUtils.addMonth(time, -1);
			}else {
				time= new Date();
				time = DateUtils.addMonth(time, -1);
			}
			total = managementService.commMonth(time);
		}else if(month != null && !month.isEmpty()) {
			total = managementService.commDay(month);
		}
		Result res = new Result(1);
		res.setData(total);
		return res.toString();
	}
	/**
	 * 根据时间按商品分类名称与商品名称统计销售量
	 * @param month 传入时间
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/query_num")
	public String selectNumTotalByTypeNameAndGoodsName(String month){
		Map<String, String> map = new HashMap<>();
		String startTime = month.concat("-01");//开始时间
		Date time = DateUtils.getLastDateOfMonth(DateUtils.getStringDateToDate(startTime));//该月最后一天的日期
		String endTime = DateUtils.fotmatDateTOymd(time);//获取该月最后一天的日期的字符串
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		List<Map<String, Object>> m = managementService.selectNumTotalByTypeNameAndGoodsName(map);
		for (Map<String, Object> map2 : m) {
			map2.put("typeName",map2.get("typeName") == null ? "已删除类" : map2.get("typeName"));
		}
		Result res = new Result(1);
		res.setData(m);
		return res.toString();
	}
	/**
	 * 往年对比,查询年份
	 * @return
	 */
	@RequestMapping("query_time")
	@ResponseBody
	@LoggerAnnotation(begin="查询时间开始",end="查询时间结束")
	public String selectMaxAndMinTime(){
		Map<String, String> map = managementService.selectMaxAndMinTime();
		map.put("minTime", String.valueOf(map.get("minTime")).substring(0, 4));
		map.put("maxTime", String.valueOf(map.get("maxTime")).substring(0, 4));
		Result res = new Result(1);
		res.setData(map);
		return res.toString();
	 }
	
	@RequestMapping("query_formerYears")
	@ResponseBody
	@LoggerAnnotation(begin="往年对比开始",end="往年对比结束")
	/**
	 * 往年对比查询一年总和
	 * @param minTime
	 * @param maxTime
	 * @return
	 */
	public String selectFormerYears(String minTime,String maxTime){
		List<Map<String, String>> list = managementService.selectFormerYears(minTime, maxTime);
		Result res = new Result(1);
		res.setData(list);
		return res.toString();
	}
	@RequestMapping("query_towYears")
	@ResponseBody
	@LoggerAnnotation(begin="两年对比开始",end="两年对比结束")
	/**
	 *多年月对比 查询多年的12个月数据,
	 * @param minTime 开始年份
	 * @param maxTime 结束年份
	 * @return
	 */
	public String selectTowYears(String minTime, String maxTime){
		List<List<Map<String, String>>> towTotal = managementService.selectTowYears(minTime, maxTime);
		Result res = new Result(1);
		res.setData(towTotal);
		return res.toString();
	}
}