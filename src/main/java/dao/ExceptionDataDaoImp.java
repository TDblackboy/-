package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modal.ExceptionData;
import util.DBUtil;

public class ExceptionDataDaoImp implements ExceptionDataDao {
	
	private static String table="exceptiondata";

	//每次加载查询前n条数据
	public List<ExceptionData> load(int n) {
		String sql="select * from "+table+" limit "+n;
		Connection con=DBUtil.getConnection();
		PreparedStatement state=null;
		ResultSet result=null;
		List<ExceptionData>exceptiondatas=new ArrayList<ExceptionData>();
		try {
			state=con.prepareStatement(sql);
			result=state.executeQuery();
			while(result.next()) {
				ExceptionData exception=new ExceptionData();
				exception.setId(result.getInt("id"));
				exception.setLocation(result.getString("location"));
				exception.setPersons(result.getString("persons"));
				exception.setDatatime(result.getString("datatime"));
				exception.setState(result.getString("state"));
				exceptiondatas.add(exception);
			}
			System.out.println("调出数据");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("调出数据错误");
		}
		DBUtil.close(result);
		DBUtil.close(state);
		DBUtil.close(con);
		return exceptiondatas;
		
	}
	
	//根据地点查询异常视频 - 模糊查询
	public List<ExceptionData> load(String location) {
			String sql="select * from "+table+" where location like '%"+location+"%'";
			Connection con=DBUtil.getConnection();
			PreparedStatement state=null;
			ResultSet result=null;
			List<ExceptionData>exceptiondatas=new ArrayList<ExceptionData>();
			try {
				state=con.prepareStatement(sql);
				result=state.executeQuery();
				while(result.next()) {
					ExceptionData exception=new ExceptionData();
					exception.setId(result.getInt("id"));
					exception.setLocation(result.getString("location"));
					exception.setPersons(result.getString("persons"));
					exception.setDatatime(result.getString("datatime"));
					exception.setState(result.getString("state"));
					
					exceptiondatas.add(exception);
				}
				System.out.println("调出数据");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("调出数据错误");
			}
			DBUtil.close(result);
			DBUtil.close(state);
			DBUtil.close(con);
			return exceptiondatas;
	}

	
	public void insertExceptionData(ExceptionData ed) {

		String sql = "insert into "+table+"(location,persons,datatime,state) values(?,?,?,?)";

		Connection con = DBUtil.getConnection();
		PreparedStatement state = null;
		try {
			
			state = con.prepareStatement(sql);
			//state.setInt(1, ed.getId());
			state.setString(1, ed.getLocation());
			state.setString(2, ed.getPersons());
			state.setString(3, ed.getDatatime());
			state.setString(4, ed.getState());
			
			state.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("添加失败");
		}
		DBUtil.close(state);
		DBUtil.close(con);
	}

	//按时间查询
	public List<ExceptionData> loadByData(String yyMM) {
		String sql="select * from "+table+" where datatime like '"+yyMM+"%'";
		Connection con=DBUtil.getConnection();
		PreparedStatement state=null;
		ResultSet result=null;
		List<ExceptionData>exceptiondatas=new ArrayList<ExceptionData>();
		try {
			state=con.prepareStatement(sql);
			result=state.executeQuery();
			while(result.next()) {
				ExceptionData exception=new ExceptionData();
				exception.setId(result.getInt("id"));
				exception.setLocation(result.getString("location"));
				exception.setPersons(result.getString("persons"));
				exception.setDatatime(result.getString("datatime"));
				exception.setState(result.getString("state"));
				exceptiondatas.add(exception);
			}
			System.out.println("调出数据");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("调出数据错误");
		}
		DBUtil.close(result);
		DBUtil.close(state);
		DBUtil.close(con);
		return exceptiondatas;
	}

	
	//查询全部
	public List<ExceptionData> load() {
		String sql="select * from "+table;
		Connection con=DBUtil.getConnection();
		PreparedStatement state=null;
		ResultSet result=null;
		List<ExceptionData>exceptiondatas=new ArrayList<ExceptionData>();
		try {
			state=con.prepareStatement(sql);
			result=state.executeQuery();
			while(result.next()) {
				ExceptionData exception=new ExceptionData();
				exception.setId(result.getInt("id"));
				exception.setLocation(result.getString("location"));
				exception.setPersons(result.getString("persons"));
				exception.setDatatime(result.getString("datatime"));
				exception.setState(result.getString("state"));
				exceptiondatas.add(exception);
			}
			System.out.println("调出数据");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("调出数据错误");
		}
		DBUtil.close(result);
		DBUtil.close(state);
		DBUtil.close(con);
		return exceptiondatas;
	}

}
