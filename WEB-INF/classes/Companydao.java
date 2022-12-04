import javax.servlet.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;
public class Companydao {
    public static Connection getCon(){
		Connection con=null;
		try{
            Class.forName("com.mysql.jdbc.Driver");      
            String url = "jdbc:mysql://127.0.0.1/supplyandchain";        
            con=DriverManager.getConnection(url, "root", "root");        
		}catch(Exception e){System.out.println(e);}
		return con;
	}
    public static int storeOrder(Order o)
    {
        int status=0;
		try{
			Connection con=getCon();
			PreparedStatement ps=con.prepareStatement("insert into orderdetails(Dealer, ProdId, Noofitems) values(?,?,?)");
			ps.setString(1,o.getDid());
			ps.setString(2,o.getPid());
            ps.setInt(3, o.getitems());
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;}

    public static ArrayList<Order> getOrderDetails()
	{
		ArrayList<Order> order = new ArrayList<Order>();
        try{
			Connection con=getCon();
            PreparedStatement ps =con.prepareStatement("SELECT * from orderdetails");
			ResultSet rs=ps.executeQuery();
            while(rs.next())
			{ 
				Order o=new Order();
				o.setDid(rs.getString(1));
				o.setPid(rs.getString(2));
				o.setitems(rs.getInt(3));
				order.add(o);
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return order;    
	}

    public static int AddItemsInDealerInventory(DealerInventory d_in)
    {
        int status=0;
		 try{
			Connection con=getCon();
			PreparedStatement pstmt=con.prepareStatement("SELECT ProductId from dealersinventory where ProductId=? AND Did=?");
			pstmt.setString(1,d_in.getPid());
			pstmt.setString(2,d_in.getDid());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next())
			{
				Statement stmt = con.createStatement();
				 		      
				   String sql = "UPDATE dealersinventory set TotalItems=TotalItems+"+d_in.getitems()+" WHERE ProductId='"+rs.getString("ProductId")+"'";
				   status=stmt.executeUpdate(sql);
			}
			else{
			PreparedStatement ps=con.prepareStatement("insert into dealersinventory(Did, ProductId, TotalItems) values(?,?,?)");
			ps.setString(1,d_in.getDid());
			ps.setString(2,d_in.getPid());
			ps.setInt(3,d_in.getitems());
			status=ps.executeUpdate();
			con.close();}
			}catch(Exception e){System.out.println(e);}
		return status;
    }

	public static int AddDealers(Dealer d)
	{
		int status=0;
		try{
			Connection con=getCon();
			PreparedStatement ps=con.prepareStatement("insert into dealerinfo(Name, UserId, Email, Password, District) values(?,?,?,?,?)");
			ps.setString(1,d.getName());
			ps.setString(2, d.getUserId());
			ps.setString(3, d.getEmail());
			ps.setString(4, d.getPassword());
			ps.setString(5, d.getDistrict());
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}

	public static int addDealersPurRec(DealersPurchaseRec dpr)
	{
		int status=0;
		try{
			Connection con=getCon();
			Statement stmt = con.createStatement();
			String sql="insert into dealerspurchaserec(Did, Purchases, Month, Year, Date) values('"+dpr.getDid()+"', '"+dpr.getPurchases()+"','"+dpr.getMonth()+"','"+dpr.getYear()+"','"+dpr.getDate()+"')";
			status=stmt.executeUpdate(sql);
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	public static ArrayList<DealersPurchaseRec> viewCompanySalesByPoint(int year)   //dealers pur rec
	{
		ArrayList<DealersPurchaseRec> dpr = new ArrayList<DealersPurchaseRec>();
        try{
			Connection con=getCon();
            PreparedStatement ps =con.prepareStatement("SELECT CEILING(LOG(SUM(Purchases))), Month from dealerspurchaserec where Year=? GROUP BY(Month)");
			ps.setInt(1, year);
			ResultSet rs=ps.executeQuery();
            while(rs.next())
			{ 
				DealersPurchaseRec o=new DealersPurchaseRec();
				o.setPurchases(rs.getInt(1));
				o.setMonth(rs.getString(2));
				dpr.add(o);
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return dpr;   
	}

	public static ArrayList<Integer> viewCompanySalesByAmount(int year) 
	{
		ArrayList<Integer> dpr = new ArrayList<Integer>();
        try{
			Connection con=getCon();
            PreparedStatement ps =con.prepareStatement("SELECT SUM(Purchases) from dealerspurchaserec where Year=? GROUP BY(Month)");
			ps.setInt(1, year);
			ResultSet rs=ps.executeQuery();			
            while(rs.next())
			{
				dpr.add(rs.getInt(1));			
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return dpr;   
	}
	public static ArrayList<Dealer> viewDealersRec() 
	{
		ArrayList<Dealer> dpr = new ArrayList<Dealer>();
        try{
			Connection con=getCon();
            PreparedStatement ps =con.prepareStatement("SELECT dealerinfo.Name, dealerinfo.UserId, dealerinfo.Email, dealerinfo.District, SUM(dealerspurchaserec.Purchases) FROM dealerinfo JOIN dealerspurchaserec ON dealerinfo.UserId=dealerspurchaserec.Did GROUP BY dealerspurchaserec.Did");
			ResultSet rs=ps.executeQuery();			
            while(rs.next())
			{
				Dealer d=new Dealer();
				d.setName(rs.getString(1));
				d.setUserId(rs.getString(2));
				d.setEmail(rs.getString(3));
				d.setDistrict(rs.getString(4));
				d.setPur(rs.getInt(5));
				dpr.add(d);
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return dpr;   
	}
	
}
