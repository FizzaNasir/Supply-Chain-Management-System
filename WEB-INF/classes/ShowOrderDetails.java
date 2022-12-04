import javax.servlet.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class ShowOrderDetails extends HttpServlet {
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {   
        PrintWriter out=response.getWriter();
       out.println ("<!DOCTYPE html>");
       out.println ("<html lang='en'>");
       out.println ("<head>");
       out.println("<link rel='stylesheet' href='buttons.css'>");   
       out.println("<link rel='stylesheet' href='ViewRecord.css'>");
       out.println ("<script src='ChangebuttonCss.js'></script>");
       out.println("<link rel='preconnect' href='https://fonts.googleapis.com'>");
       out.println("<link rel='preconnect' href='https://fonts.gstatic.com' crossorigin>");
       out.println("<link href='https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:wght@100&display=swap' rel='stylesheet'>");
       out.println("<link href='https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:wght@100&family=Ubuntu:wght@300&display=swap' rel='stylesheet'>") ;
       out.println("<link rel='stylesheet' href='https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap'>");
       out.println ("<meta charset='UTF-8'>");
	   out.println ("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
	   out.println ("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
       out.println ("<style>");
       out.println ("table{");
       out.println ("    margin-top:80px;");
       out.println ("}");
       out.println ("body{");
       out.println ("background-image: url('bgImg.jpg');");
       out.println ("background-repeat: no-repeat;");
       out.println ("background-size: 100% auto;");
       out.println (" }");
       out.println ("</style>");
	   out.println ("<title>Order Detail</title>");
       out.println ("</head>");
       out.println ("<body>");
       HttpSession session=request.getSession(false);
    
       if(session==null||(!(session.getAttribute("validateAdminlogin").equals("admin"))))
       {
           out.println("Please login first");
           response.sendRedirect("index.html");
       }
       else{      
		out.println ("<table>");
                out.println("<tr><th>Dealer</th><th>Product</th><th>No. of items</th><th></th><th></th></tr>"); 
           ArrayList<Order> order=Companydao.getOrderDetails();
           for(Order o: order)
           {
              out.println("<tr><td id='did'>"+o.getDid()+"</td><td id='pid'>"+o.getPid()+"</td><td id='items'>"+o.getitems()+"</td><td><a class='btn' href='AddItemsInDealerInventory?did="+o.getDid()+"&pid="+o.getPid()+"&noOfItems="+o.getitems()+"'>Accept Order</a></td><td><a href='#' class='btn'>Reject Order</a></td></tr>");    
           }    
        
        out.println ("<table>");
        out.println ("</body>");
 
       }
      
}
}
