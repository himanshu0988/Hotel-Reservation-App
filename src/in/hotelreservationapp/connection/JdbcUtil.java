package in.hotelreservationapp.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {
	// restrict the object creation from outside of class
	private JdbcUtil(){}
	static{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		public static Connection getConnection() throws SQLException, IOException {
			
			FileInputStream fis=new FileInputStream("C:\\Users\\HIMANSHU\\eclipse-workspace\\HotelReservationApp\\src\\in\\hotelreservationapp\\connection\\application.properties");
			Properties properties=new Properties();
			properties.load(fis);
			
			Connection connection=DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("user"),properties.getProperty("password"));
			return connection;
		}
}
