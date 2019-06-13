package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class TestService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TestService() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	
		request.getContentType();
		//测试
		System.out.println(request.getContentType());
		
		String data_type=request.getParameter("data_type");
		System.out.println(data_type);
		
		String data=request.getParameter("datas");
		System.out.println("String data:"+data);
		
		//
		Gson gson=new Gson();
		JsonObject jo=(JsonObject) new JsonParser().parse(data);
		String lovalue=jo.get("location").getAsString();
		String pers=jo.get("persons").getAsString();
		
		System.out.println("location:"+lovalue);
		System.out.println("persons:"+pers);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
