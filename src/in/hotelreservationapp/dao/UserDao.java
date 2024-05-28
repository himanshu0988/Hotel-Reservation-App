package in.hotelreservationapp.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;

import in.hotelreservationapp.connection.JdbcUtil;
import in.hotelreservationapp.model.*;

public class UserDao {
	Connection connection=null;
	PreparedStatement pstm=null;
	ResultSet resultSet=null;
	UserDao register=null;
	
	public String register(User register){
		try {
			connection=JdbcUtil.getConnection();
			if(connection !=null) {
				String query="Insert into User_Register (userid,`uname`,email,`pass`)values(?,?,?,?)";
				pstm=connection.prepareStatement(query);
			}
			if(pstm !=null) {
				pstm.setString(1, register.getUserId());
				pstm.setString(2,register.getName());
				pstm.setString(3,register.getEmail());
				pstm.setString(4,register.getPassword());
				
				int rowAffected=pstm.executeUpdate();
				if(rowAffected==1)
					return "success";
			}
		  } catch (SQLException | IOException e) {
			e.printStackTrace();
		  }
		return "failure";	
	}
	
	public String login(User login){
		try {
			connection=JdbcUtil.getConnection();
			if(connection !=null) {
				String query="Select userid,pass from User_Register where userid=? and pass=?";
				pstm=connection.prepareStatement(query);
			}
			if(pstm!=null){
				pstm.setString(1, login.getUserId());
				pstm.setString(2, login.getPassword() );
				resultSet=pstm.executeQuery();
			}
			if(resultSet!=null) {
				if(resultSet.next())
					return "success";
			}
		}catch (SQLException | IOException e) {
				e.printStackTrace();
			  }
		return "failure";
      }
}

