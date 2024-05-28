package in.hotelreservationapp.dao;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import in.hotelreservationapp.connection.JdbcUtil;
import in.hotelreservationapp.model.Customer;

public  class CustomerDao {
	Connection connection=null;
	PreparedStatement pstm=null;
	ResultSet resultset=null;
	Customer customer=null;
	
	public String addCustomer(Customer customer) {
		try {
			connection=JdbcUtil.getConnection();
			if(connection!=null) {
			String query="Insert into Reservation (reid,`cuname`,mob,`roomnum`)values(?,?,?,?)";
			pstm=connection.prepareStatement(query);
			}
			
			if(pstm!=null){
				pstm.setInt(1,customer.getReservation_id());
				pstm.setString(2,customer.getName());
				pstm.setString(3,customer.getMobile());
				pstm.setString(4,customer.getRoom_num());
				
				int rowAffected=pstm.executeUpdate();
				if(rowAffected==1)
					return "success";
			}
		  } catch (SQLException | IOException e) {
			e.printStackTrace();
		  }
		return "failure";	
	}

	public Customer searchCustomer(Integer re_id) {
		try {
			connection=connection=JdbcUtil.getConnection();
			if(connection!=null) {
			String query="Select reid,cuname,mob,roomnum from Reservation  where reid=?";
			pstm=connection.prepareStatement(query);
			}
			if(pstm!=null){
				pstm.setInt(1, re_id);
				resultset=pstm.executeQuery();
			}
			if(resultset!=null)
				if(resultset.next()) {
					customer = new Customer();
					
					customer.setReservation_id(resultset.getInt(1));
					customer.setName(resultset.getString(2));
					customer.setMobile(resultset.getString(3));
					customer.setRoom_num(resultset.getString(4));
					return customer;
				}
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return customer;	
	}

	public String updateCustomer(Customer customer) {
			try {
				connection=connection=JdbcUtil.getConnection();
				if (connection != null) {
					String sqlUpdateQuery = "update Reservation set cuname=?,mob=?,roomnum=? where reid=?";
					pstm = connection.prepareStatement(sqlUpdateQuery);
				}
				if (pstm != null) {

					pstm.setString(1, customer.getName());
					pstm.setString(2, customer.getMobile());
					pstm.setString(3, customer.getRoom_num());
					pstm.setInt(4, customer.getReservation_id());

					int rowAffected = pstm.executeUpdate();
					if (rowAffected !=0) {
						return "success";
					}
				}
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			return "failure";
		}

	public String deleteCustomer(Integer id) {
		try {
			connection=connection=JdbcUtil.getConnection();
			if(connection!=null){
			String query="delete from Reservation  where reid=?";
			pstm=connection.prepareStatement(query);
			}
			
			if(pstm!=null) {
				
				pstm.setInt(1, id);
				int rowAffected=pstm.executeUpdate();
				if(rowAffected==1);
					return "success";
		   }	
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return "failure";
	}
}
