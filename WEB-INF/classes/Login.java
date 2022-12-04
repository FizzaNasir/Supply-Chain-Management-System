import javax.servlet.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class Login extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
{
    PrintWriter out=res.getWriter();
     String Userid=req.getParameter("uid");
     String password=req.getParameter("pass");
     if(Userid.contains("D")){
        try{

            Class.forName("com.mysql.jdbc.Driver");
        
            String url = "jdbc:mysql://127.0.0.1/supplyandchain";
        
            Connection con=DriverManager.getConnection(url, "root", "root");
        
            Statement st=con.createStatement();
        
             
            String query="Select * from dealerinfo where UserId='"+Userid+"' AND Password='"+password+"' ";
   
            ResultSet rs = st.executeQuery( query );
          
            if(rs.next()){
               HttpSession session= req.getSession();
               session.setAttribute("validateDealerlogin", "dealer");
               session.setAttribute("DealerUserid", Userid);
               RequestDispatcher rd=req.getRequestDispatcher("/DealerInterface.html");
               rd.include(req, res);
             }
            
            else{
                     out.println("Invalid username or password");
                     res.sendRedirect("/index.html");
                }
        
          }
          catch(Exception e){
        
            out.println(e);
          }
     }

     else if(Userid.contains("A")){
      try{

          Class.forName("com.mysql.jdbc.Driver");
      
          String url = "jdbc:mysql://127.0.0.1/supplyandchain";
      
          Connection con=DriverManager.getConnection(url, "root", "root");
      
          Statement st=con.createStatement();
      
           
          String query="Select * from admininfo where UserId='"+Userid+"' AND Password='"+password+"'  ";
 
          ResultSet rs = st.executeQuery( query );
        
          if(rs.next()){
             HttpSession session= req.getSession();
             session.setAttribute("validateAdminlogin", "admin");
             session.setAttribute("AdminUserid", Userid);
             RequestDispatcher rd=req.getRequestDispatcher("/CompanyInterface.html");
             rd.include(req, res);
           }
          
          else{
                   out.println("Invalid admin username or password");
                   res.sendRedirect("/index.html");
                   
              }
      
        }
        catch(Exception e){
      
          out.println(e);
        }
}
}}
