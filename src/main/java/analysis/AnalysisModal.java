package analysis;

import java.util.List;
import java.util.Map;

/**
 *	统计分析的 线性表的数据结构
 */
public class AnalysisModal {
	/*
	 * 	 jsonarray - jsonobject:
	 * 
	 * 			     jsonobject - date:
	 * 				            - data:
	 * 
	 *  						  data - location:
	 *  					 		   - count
	 */
	private String date;
	private List<Map<String,String>> data;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<Map<String, String>> getData() {
		return data;
	}
	public void setData(List<Map<String, String>> data) {
		this.data = data;
	}
	
	
}
