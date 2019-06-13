package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import dao.DredgePersonnelDao;
import dao.DredgePersonnelDaoImp;
import modal.DredgePersonnel;

/**
 * 实时监控界面交互服务接口
 */
public class MonitorService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MonitorService() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in.......");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		/*
		 * 	# 条件查询疏导人员
		 */
		String type = request.getParameter("type");
		String value = request.getParameter("value");
		//测试
		//System.out.println("value"+value);
		if (type != null && !type.equals("")) {
			if (value != null && !value.equals("")) {
				//get data
				List<DredgePersonnel> personnels = loadDredgePersonnels(type,value);
				if(personnels!=null) {
					//json
					JsonArray ja=toJsonArray(personnels);
					//response
					out.print(ja);
					out.flush(); 
					out.close();
				}
			}else {
				System.out.println("warn:查询的条件value=" + value);
			}
		} else {
			System.out.println("warn:type=" + type);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public static List<DredgePersonnel> loadDredgePersonnels(String type,String value){
		DredgePersonnelDao dao=new DredgePersonnelDaoImp();
		List<DredgePersonnel> personnels = null;
		if (type.equals("1")) {
			personnels=dao.load(1, value);// 按 姓名 查询此地的疏导人员
		} else if (type.equals("2")) {
			personnels = dao.load(value);// 按 地点位置  查询此地的疏导人员
			//System.out.println(personnels);
		}
		return personnels;
	}

	public static JsonArray toJsonArray(List<DredgePersonnel>personnels) {
		Gson gson=new Gson();
		JsonArray ja=gson.toJsonTree(personnels).getAsJsonArray();
		//System.out.println("json array:"+ja);
		return ja;
	}
	
}
