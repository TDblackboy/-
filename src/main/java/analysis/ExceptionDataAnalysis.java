package analysis;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.ExceptionDataDao;
import dao.ExceptionDataDaoImp;
import modal.ExceptionData;

public class ExceptionDataAnalysis {
	
	/*
	 *	统计基教一层 4个楼梯口中每个口爆发异常的次数总和 
	 *	return: list<Map<String,String>>
	 */
	public static List<Map<String,String>> count() {
		
		List<Map<String,String>> result=new ArrayList<Map<String,String>>();
		ExceptionDataDao dao=new ExceptionDataDaoImp();
		
		//105
		String floor05="基教一层105对面";
		List<ExceptionData> da1=dao.load("105");
		
		List<ExceptionData> real1=new ArrayList<ExceptionData>();
		for(ExceptionData ed:da1) {
			if(ed.getState().equals("1")) {//判断为异常数据
				real1.add(ed);
			}
		}
		
		int count1=real1.size();
		Map<String, String> m1=new HashMap<String,String>();
		m1.put("name", floor05);
		m1.put("count", String.valueOf(count1));
		
		//106
		String floor06="基教一层106对面";
		List<ExceptionData> da2=dao.load("106");
		
		List<ExceptionData> real2=new ArrayList<ExceptionData>();
		for(ExceptionData ed:da2) {
			if(ed.getState().equals("1")) {
				real2.add(ed);
			}
		}
		
		int count2=real2.size();
		Map<String, String> m2=new HashMap<String,String>();
		m2.put("name", floor06);
		m2.put("count", String.valueOf(count2));
		
		//111
		String floor11="基教一层111对面";
		List<ExceptionData> da3=dao.load("111");
		
		List<ExceptionData> real3=new ArrayList<ExceptionData>();
		for(ExceptionData ed:da3) {
			if(ed.getState().equals("1")) {
				real3.add(ed);
			}
		}
		
		int count3=real3.size();
		Map<String, String> m3=new HashMap<String,String>();
		m3.put("name", floor11);
		m3.put("count", String.valueOf(count3));
		
		//112
		String floor12="基教一层112对面";
		List<ExceptionData> da4=dao.load("112");
		
		List<ExceptionData> real4=new ArrayList<ExceptionData>();
		for(ExceptionData ed:da4) {
			if(ed.getState().equals("1")) {
				real4.add(ed);
			}
		}
		
		int count4=real4.size();
		Map<String, String> m4=new HashMap<String,String>();
		m4.put("name", floor12);
		m4.put("count", String.valueOf(count4));
		
		//比例
		float sum=count1+count2+count3+count4;
		NumberFormat format=NumberFormat.getPercentInstance();
		
		String perc1=format.format(count1/sum);
		String perc2=format.format(count2/sum);
		String perc3=format.format(count3/sum);
		String perc4=format.format(count4/sum);
		
		m1.put("percent", perc1);
		m2.put("percent", perc2);
		m3.put("percent", perc3);
		m4.put("percent", perc4);
		
		result.add(m1);
		result.add(m2);
		result.add(m3);
		result.add(m4);
		return result;
	}
	
	
	/*
	 * 	按时间排序 
	 * 	在某一时间段内 4个地点的出现异常的次数的对比
	 * 	时间段内 - 怎么确定？ 有 【 年（连续n年） - 月（1-12，年固定） - 日（1-31，年&月固定）】三个指标  
	 * 	- - 暂时按 月  即 1-12月内 - - 
	 * 	返回 ： 数组，大小为12（12个月）
	 * 		一条数据 包含  ： 【月（一个月）】date +data【  (location)地点（4）+ (count)每个地点出现异常的次数（4） 】
	 */
	public static List<AnalysisModal> lineAnalysisByMonth() {
		//设置存储的数据结构
		List<AnalysisModal> result=new ArrayList<AnalysisModal>();
		
		//两个定位条件 ：时间+地点 - 人数 再累积
		String yy="2019";//确定搜索参数的字符串格式 yy-MM  - 确定时间线
		
		ExceptionDataDao dao=new ExceptionDataDaoImp();
		
		for(int i=0;i<12;i++) {
			AnalysisModal item=new AnalysisModal();
			
			String mm=String.valueOf(i+1);
			String date=yy+"-"+mm;
			item.setDate(date);//item的时间搞定
			
			List<ExceptionData> eds=dao.loadByData(date);
			//确定  - 地点线索
			//解析list - 提取四个地点的人流量
			List<Map<String,String>> places=new ArrayList<Map<String,String>>();//保存四个地点信息
			if(eds!=null) {
				String locations[]= {"105","106","111","112"};
				for(int j=0;j<4;j++) {
					Map<String,String> place=new HashMap<String,String>();
					String location=locations[j];
					place.put("location","基教一层"+location);//place的name搞定
					int count=0;
					for(ExceptionData ed:eds) {
						if(ed.getLocation().contains(location)) {
							count++;
						}
					}
					place.put("count",String.valueOf(count));//place的frequency搞定
					places.add(place);
				}//for 4
			}//if null
			item.setData(places);
			result.add(item);
		}//for 12
		return result;
	}
	
	
	
	/*
	 * 	重新构造 线性图的数据格式
	 * 
	 */
	public static List<Map<String,String>> lineNeedData(List<AnalysisModal> origan){
		
		
		List<Map<String,String>> all=new ArrayList<Map<String,String>>();//48条数据 :0-47{p(j),number(j)}
		//四个地点 p0-p11:105,p12-p23:106,p24:-p35:111,p36-p47:112
		int j=0;//12个月，number0,number1,number2,number3........number47;
		for(int i=0;i<4;i++) {
			int month=1;
			for(AnalysisModal item:origan) {
				Map<String,String> one=new HashMap<String,String>();
				one.put("p"+j,String.valueOf(month));
				//month
				//System.out.println("month="+month);
				month++;
				if(month>12) {
					month=1;
				}
				one.put("number"+j, item.getData().get(i).get("count"));
				j++;
				//j
				//System.out.println("j="+j);
				all.add(one);
			}
			
		}
		
		return all;
		
	}
}
