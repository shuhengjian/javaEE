package com.creatorblue.cbitedu.system.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;

import com.creatorblue.cbitedu.core.utils.SpringContextHolder;
import com.creatorblue.cbitedu.system.domain.TsysSerialno;
/**
 * 
 * @author yuncai.zhu
 * 
 */
public class SerialNumber {
	/*
	 * 单例
	 */
	private static SerialNumber serialNumber=null;
	/** 
     * 生成流水号的名称 
     */  
    private String secound_name = "";
	/** 
     * 数据库访问类 
     */  
	
	private static TsysSerialnoService<T> tsysSerialnoService;
	
	
    private SerialNumber() {  
        super();  
    } 
    
    public SerialNumber(String secound_name) {
		this.secound_name=secound_name;
	}
    
	public static SerialNumber newInstance(String secound_name) {  
	    synchronized (SerialNumber.class) {  
	        serialNumber = new SerialNumber(secound_name);  
	    } 
	    tsysSerialnoService=SpringContextHolder.getBean(TsysSerialnoService.class);//自动注入
        return serialNumber;  
    }  
	
	
    public String toString(int num,int length){
    	String str="";
    	String numlen=""+num;
    	for(int i=length-(numlen.length());i>0;i-- ){
    		str=str+"0";
    	}
    	return str+num;
    }
    
    public synchronized String SerialNextNo() throws Exception{
    	Map<String,String> map_code=new HashMap<String,String>();
    	map_code.put("secound_name", secound_name);
    	TsysSerialno bean=tsysSerialnoService.selectTsysSerialno(map_code);
    	String s=bean.getFormular_regx(); 
		
		int step=Integer.parseInt(bean.getStep());//步长
		int serial_length=Integer.parseInt(bean.getSerial_length());//流水长度
		String current_value=bean.getCurrent_value();
		//确定年月日在流水号中的位置
		String s_h=s;
		s_h=s_h.replaceAll("\\{YYYY\\}", "YYYY");
		s_h=s_h.replaceAll("\\{MM\\}", "MM");
		s_h=s_h.replaceAll("\\{DD\\}", "DD");
		//流水号长度用!补
		String s_temp="";
		for(int i=0;i<serial_length;i++){
			s_temp+="!";
		}
		s_h=s_h.replaceAll("\\{NO\\}", s_temp);
		int year_position=s_h.indexOf("YYYY");//年
		int month_position=s_h.indexOf("MM");//月
		int day_position=s_h.indexOf("DD");//日
		int no_position=s_h.indexOf(s_temp);//顺序号		
		
		//替换年月日
		Calendar cal=Calendar.getInstance();//使用日历类
		String year=String.valueOf(cal.get(Calendar.YEAR));//得到年
		String month=addZero(cal.get(Calendar.MONTH)+1);//得到月，因为从0开始的，所以要加1
		String day=addZero(cal.get(Calendar.DAY_OF_MONTH));//得到天		
		//System.out.println(s.indexOf("{YYYY}"));
		
		//替換年、月、日
		s=s.replaceAll("\\{YYYY\\}", year);
		s=s.replaceAll("\\{MM\\}", month);
		s=s.replaceAll("\\{DD\\}", day);	
		//生成方式
		String create_type=bean.getCreate_type();
		//生成流水号
		int number=0;
		//当前值为空时
		
		if(current_value==null || current_value.equals("")){
			number=Integer.parseInt(bean.getInit_value());
		}else{
			//得到当前序号值
			//String cur_no=current_value.substring(no_position,no_position+serial_length);
			String cur_no=current_value;
			if(create_type.equals("1")||create_type.equals("5")){//递增
				number+=Integer.parseInt(cur_no)+step;
			}else{
				if(create_type.equals("2")){//每日生成(每日从初始值记数,递增)
					//确定年月日是否与当前年月日相等
					boolean flag_year=false;
					boolean flag_month=false;
					boolean flag_day=false;
					String cur_year=current_value.substring(year_position,year_position+4);
					flag_year=equal(year,cur_year);
					String cur_month=current_value.substring(month_position,month_position+2);
					flag_month=equal(month,cur_month);
					String cur_day=current_value.substring(day_position,day_position+2);
					flag_day=equal(day,cur_day);
					if(flag_year&&flag_month&&flag_day)					
						number+=Integer.parseInt(cur_no)+step;
					else
						number=Integer.parseInt(bean.getInit_value());
				}else{
					if(create_type.equals("3")){//每月生成(每月从初始值记数,递增)
						//确定年月是否与当前年月相等
						boolean flag_year=false;
						boolean flag_month=false;
						String cur_year=current_value.substring(year_position,year_position+4);
						flag_year=equal(year,cur_year);
						String cur_month=current_value.substring(month_position,month_position+2);
						flag_month=equal(month,cur_month);
						if(flag_year&&flag_month)					
							number+=Integer.parseInt(cur_no)+step;
						else
							number=Integer.parseInt(bean.getInit_value());
					}else{
						if(create_type.equals("4")){//每年生成(每年从初始值记数,递增)
							//确定年是否与当前年相等
							boolean flag_year=false;
							String cur_year=current_value.substring(year_position,year_position+4);
							flag_year=equal(year,cur_year);
							if(flag_year)					
								number+=Integer.parseInt(cur_no)+step;
							else
								number=Integer.parseInt(bean.getInit_value());
						}
					}
				}
			}
		}	
		//生成序号
		String number_str;
		if(create_type.equals("5")){
			number_str=String.valueOf(number);
		}else{
			number_str=toString(number,serial_length);
		}
		//生成流水号
		s=s.replaceAll("\\{NO\\}", number_str);//替换序号
		//System.out.println(s);	
		//更新当前流水号
		map_code.put("current_value", s);
		tsysSerialnoService.updateSerialnoCurNo(map_code);
    	return s;
    }
    public static String addZero(int date){
    	String str=String.valueOf(date); 
    	if(date<10)
    		str="0"+str;
    	return str;  
    } 
    public static boolean equal(String str1,String str2){
        if(str1.equals(str2))
        	return true;
        return false;  
    } 
   
}