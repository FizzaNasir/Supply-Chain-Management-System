import javax.servlet.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.text.NumberFormat.Style;
import java.util.ArrayList;
public class ViewDealerRec extends HttpServlet 
{     public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    out.println (" <style>");
    out.println ("table{");
    out.println ("    margin-right: 172px;");
    out.println ("margin-top: -480px");
    out.println ("  }");
    out.println ("</style>");
    out.println ("<title>Dealers Record</title>");
    out.println ("</head>");
    out.println ("<body>");
    HttpSession session=request.getSession(false);
      
        if(session==null||(!(session.getAttribute("validateAdminlogin").equals("admin"))))
        {
            out.println("Please login first");
            response.sendRedirect("index.html");
        }    
        else
        {       
            out.println ("<table>");
            out.println("<tr><th>Name</th><th>ID</th><th>Email</th><th>District</th><th>Purchase</th></tr>"); 
       ArrayList<Dealer> dr=Companydao.viewDealersRec();
       for(Dealer d: dr)
       {
          out.println("<tr><td id='did'>"+d.getName()+"</td><td id='pid'>"+d.getUserId()+"</td><td id='items'>"+d.getEmail()+"</td><td id='items'>"+d.getDistrict()+"</td><td id='items'>"+d.getPur()+"</td></tr>");    
        }    
        request.getRequestDispatcher("dealerSide.html").include(request, response);
    }
    out.println ("</table>");
    out.println ("</body>");

    }
}

