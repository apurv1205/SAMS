import java.net.InetAddress;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class test {
/*public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sams","root","");
		java.sql.PreparedStatement statement = con.prepareStatement("SELECT * FROM `salesperson`");
		ResultSet result=statement.executeQuery();
		while(result.next()){
			System.out.println(result.getString(1)+"  "+result.getInt(2));
			
		}
	}*/
	public static Connection getConnection() throws Exception{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/","root","");
			Statement stmt = null;
			stmt = conn.createStatement();
		    String sql = "CREATE DATABASE IF NOT EXISTS SAMS";
		    stmt.executeUpdate(sql);
			System.out.println("Connected...");
			java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sams","root","");
			
			
			/*Class.forName("com.mysql.jdbc.Driver");
			System.getProperties().setProperty("socksProxySet", "true");
		    System.getProperties().setProperty("socksProxyHost", "10.3.100.207");
		    System.getProperties().setProperty("socksProxyPort", "8080");
			java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://www.db4free.net:3306/assign4_sams","assign_4","samssams");
			System.out.println("Connected...");*/
			
			return (Connection) con;
		}
		catch(Exception e){
			System.out.println(e);
		}
		return null;
	}

}
