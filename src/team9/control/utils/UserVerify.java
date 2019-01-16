package team9.control.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class UserVerify {

	public static Boolean userVerify(String userName, String password) {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		Connection conn = null;
		String sql = "select * from user where userName = ? and password = ?;";
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, userName);
			st.setString(2, password);
			rs = st.executeQuery();
			if(rs.next()){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			return false;
		} finally {
			DataSourceUtil.closeResource(conn, st, rs);
		}
	}
	
	public static String userQuerry(String userName) {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		String sql = "select * from user where userName = ?;";
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, userName);
			rs = st.executeQuery();
			rs.next();
			return rs.getString("roleInfo");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeResource(conn, st, rs);
		}
		return null;
	}
}