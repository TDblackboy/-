package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import analysis.AnalysisModal;
import analysis.ExceptionDataAnalysis;
import dao.ExceptionDataDao;
import dao.ExceptionDataDaoImp;
import modal.ExceptionData;

public class ExceptionDataService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExceptionDataService() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 *  请求数据类型说明：
		 *  	type :all - 查询所有
		 *  	     : 1  - 条件输入查询	
		 *  		  ： 3  - 统计结果请求 - 环状图
		 *  		 : 4  - 分析结果 - 线性图
		 */
		//System.out.println("Enter ExceptionDataService.......");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String type=request.getParameter("type");
		if(type!=null) {
			ExceptionDataDao dao=new ExceptionDataDaoImp();
			List<ExceptionData> datas=null;
			Gson gson=new Gson();
			if(type.equals("all")) {
				System.out.println("Enter ExceptionDataService 0.......");
				int value=Integer.parseInt(request.getParameter("value"));
				if(value!=0) {
					datas=dao.load(value);
				}else {
					datas=dao.load();
				}
				JsonArray ja=gson.toJsonTree(datas).getAsJsonArray();
				out.print(ja);
			}else if(type.equals("1")) {
				System.out.println("Enter ExceptionDataService 1.......");
				String value=request.getParameter("value");
				datas=dao.load(value);
				JsonArray ja=gson.toJsonTree(datas).getAsJsonArray();
				out.print(ja);
			}else if(type.equals("3")) {
				System.out.println("Enter ExceptionDataService type=3.......");
				//统计结果信息返回
				List<Map<String,String>> results=ExceptionDataAnalysis.count();
				JsonArray ja=gson.toJsonTree(results).getAsJsonArray();
				out.print(ja);
				//System.out.println(ja);
			}else if(type.equals("4")) {
				System.out.println("Enter ExceptionDataService type=4.......");
				//统计结果信息返回
				List<AnalysisModal> results=ExceptionDataAnalysis.lineAnalysisByMonth();
				List<Map<String,String>> newData=ExceptionDataAnalysis.lineNeedData(results);
				//解析成json返回
				JsonArray ja=gson.toJsonTree(newData).getAsJsonArray();
				out.print(ja);
				//测试
				//System.out.println(ja);
			}
			out.flush(); 
			out.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
