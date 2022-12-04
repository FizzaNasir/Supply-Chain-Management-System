import javax.servlet.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;
public class Dealerdao {
    public static Connection getCon(){
		Connection con=null;
		try{
            Class.forName("com.mysql.jdbc.Driver");      
            String url = "jdbc:mysql://127.0.0.1/supplyandchain";        
            con=DriverManager.getConnection(url, "root", "root");        
		}catch(Exception e){System.out.println(e);}
		return con;
	}
	
    public static int addInfoRecord(Client c, String did)
    {
        int status=0;
		try{
			Connection con=getCon();
			PreparedStatement ps=con.prepareStatement("insert into clientinfo(Name, Cnic, Did) values(?,?,?)");
			ps.setString(1,c.getName());
			ps.setString(2,c.getCNIC());
			ps.setString(3,did);
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
    }

    public static int addPurchaseRecord(Client c) 
    {
        int status=0;
        try{
			Connection con=getCon();
            PreparedStatement ps =con.prepareStatement("insert into clientpurchases(Cnic, Purchases) values(?,?)");
            ps.setString(1, c.getCNIC());
            ps.setInt(2, c.getPurchases());
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
    }

	public static ArrayList<Client> viewRecord(String did)
	{
		ArrayList<Client> clientRec = new ArrayList<Client>();
        try{
			Connection con=getCon();
            PreparedStatement ps =con.prepareStatement("SELECT clientinfo.Name, clientinfo.Cnic, clientpurchases.Purchases FROM clientpurchases INNER JOIN clientinfo ON clientpurchases.Cnic=clientinfo.Cnic WHERE clientinfo.Did=?");
			ps.setString(1, did);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{ 
				Client cl=new Client();
				cl.setName(rs.getString(1));
				cl.setCNIC(rs.getString(2));
				cl.setPurchases(rs.getInt(3));
				clientRec.add(cl);
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return clientRec;    
	}

	public static ArrayList<Product> viewCompanyProducts()
	{
		ArrayList<Product> Prodinfo = new ArrayList<Product>();
        try{
			Connection con=getCon();
            PreparedStatement ps =con.prepareStatement("SELECT * FROM productinfo");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{ 
				Product p=new Product();
				p.setid(rs.getString(1));
				p.settitle(rs.getString(2));
				p.settype(rs.getString(3));	
				p.setSubtype(rs.getString(4));
				p.setcreatedAt(rs.getString(5));
				p.setcost(rs.getInt(6));
				Prodinfo.add(p);
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return Prodinfo;    
	}
	
	public static ArrayList<Product> viewInventory(String did)
	{
		ArrayList<Product> prod = new ArrayList<Product>();
        try{
			Connection con=getCon();
            PreparedStatement ps =con.prepareStatement("SELECT productinfo.ID, productinfo.Title, productinfo.Type, productinfo.Subtype, productinfo.CreatedAt, productinfo.Cost FROM dealersinventory INNER JOIN productinfo ON dealersinventory.ProductId=productinfo.ID WHERE dealersinventory.Did=?");
		    ps.setString(1,did);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{ 
				Product p=new Product();
				p.setid(rs.getString(1));
				p.settitle(rs.getString(2));
				p.settype(rs.getString(3));	
				p.setSubtype(rs.getString(4));
				p.setcreatedAt(rs.getString(5));
				p.setcost(rs.getInt(6));
				prod.add(p);
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return prod;    
	}


	 public static int removeProduct(Product prod){
		int status=0;
		try{
			Connection con=getCon();
			PreparedStatement ps=con.prepareStatement("delete from dealersinventory where ProductId=?");
			ps.setString(1, prod.getid());
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}

	public static int addDealerSalesRecord(DealerSales d) 
    {
        int status=0;
        try{
			Connection con=getCon();
            PreparedStatement ps =con.prepareStatement("insert into dealersales(Did, Amount, Month, Year, Date) values(?,?,?,?,?)");
            ps.setString(1, d.getDid());
            ps.setInt(2, d.getSales());
			ps.setString(3, d.getMonth());
			ps.setInt(4,d.getYear());
			ps.setString(5, d.getDate());
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
    }

	public static ArrayList<DealerSales> ViewSalesRecord(String did) 
	{
		ArrayList<DealerSales> dsales = new ArrayList<DealerSales>();
        try{
			Connection con=getCon();
            PreparedStatement ps =con.prepareStatement("SELECT SUM(Amount), Month, Year, Date FROM dealersales WHERE Did=? Group by Month,Year");
			ps.setString(1,did);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{ 
				DealerSales ds=new DealerSales();
				ds.setSales(rs.getInt(1));
				ds.setMonth(rs.getString(2));
				ds.setYear(rs.getInt(3));
				ds.setDate(rs.getString(4));			
				dsales.add(ds);
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return dsales;    
	}
  
}
