package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modal.DredgePersonnel;
import util.DBUtil;

public class DredgePersonnelDaoImp implements DredgePersonnelDao {

	private static String table="dredgepersonnel";
	
	//根据地点查询相关疏导人员
	public List<DredgePersonnel> load(String location) {
		String sql="select * from "+table+" where location like '%"+location+"%'";
		Connection con=DBUtil.getConnection();
		PreparedStatement state=null;
		ResultSet result=null;
		List<DredgePersonnel>personnels=new ArrayList<DredgePersonnel>();
		try {
			state=con.prepareStatement(sql);
			result=state.executeQuery();
			while(result.next()) {
				DredgePersonnel personnel=new DredgePersonnel();
				personnel.setId(result.getInt("id"));
				personnel.setName(result.getString("name"));
				personnel.setTel(result.getString("tel"));
				personnel.setLocation(result.getString("location"));
				personnel.setState(result.getString("state"));
				personnels.add(personnel);
			}
			System.out.println("调出数据");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("调出数据错误");
		}
		DBUtil.close(result);
		DBUtil.close(state);
		DBUtil.close(con);
		return personnels;
	}

	public List<DredgePersonnel> load(int i, String name) {
		String sql="select * from "+table+" where "+"name"+" like '%"+name+"%'";
		Connection con=DBUtil.getConnection();
		PreparedStatement state=null;
		ResultSet result=null;
		List<DredgePersonnel>personnels=new ArrayList<DredgePersonnel>();
		try {
			state=con.prepareStatement(sql);
			result=state.executeQuery();
			while(result.next()) {
				DredgePersonnel personnel=new DredgePersonnel();
				personnel.setId(result.getInt("id"));
				personnel.setName(result.getString("name"));
				personnel.setTel(result.getString("tel"));
				personnel.setLocation(result.getString("location"));
				personnel.setState(result.getString("state"));
				personnels.add(personnel);
			}
			System.out.println("调出数据");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("调出数据错误");
		}
		DBUtil.close(result);
		DBUtil.close(state);
		DBUtil.close(con);
		return personnels;
	}

}
