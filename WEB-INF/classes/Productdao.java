import javax.servlet.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class Productdao {
    public static Connection getCon(){
		Connection con=null;
		try{
            Class.forName("com.mysql.jdbc.Driver");      
            String url = "jdbc:mysql://127.0.0.1/supplyandchain";        
            con=DriverManager.getConnection(url, "root", "root");        
		}catch(Exception e){System.out.println(e);}
		return con;
	}
  
    public static int getProductById(String p_id)
    { int cost=0;
        try{
			Connection con=getCon();
			PreparedStatement ps=con.prepareStatement("Select ID, Cost from productinfo where ID=?");
			ps.setString(1,p_id);
		   ResultSet rs= ps.executeQuery();
           if(rs.next())
           {
             cost=rs.getInt(2);
           }
			con.close();
		}catch(Exception e){System.out.println(e);}
        return cost;
    }

	
    
}
