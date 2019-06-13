package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

	private static final String database = "hexagon";

	public static Connection getConnection() {

		String driver = "com.mysql.jdbc.Driver";
		String root = "root";
		String pass = "sunyu";
		String url = "jdbc:mysql://localhost:3306/" + database + "?useUnicode=true&characterEncoding=UTF-8";

		try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, root, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;

	}

	public static void close(Connection connection) {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("连接对象关闭失败");
		}
	}

	public static void close(PreparedStatement preparedStatement) {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} catch (SQLException e) {
			System.out.println("语句传输对象关闭失败");
		}
	}

	public static void close(ResultSet resultSet) {
		try {
			if (resultSet != null)
				resultSet.close();
		} catch (SQLException e) {
			System.out.println("结果集对象关闭失败");
		}
	}
}
