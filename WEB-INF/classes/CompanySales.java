import javax.servlet.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class CompanySales extends HttpServlet {
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {   
       PrintWriter out=response.getWriter();
       out.println ("<!DOCTYPE html>");
       out.println ("<html lang='en'>");
       out.println ("<head>");
       out.println("<link rel='stylesheet' href='buttons.css'>");   
       out.println("<link rel='stylesheet' href='ViewRecord.css'>");
       out.println("<link rel='stylesheet' href='hoverableDropdown.css'>");
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
     out.println ("body{");
      out.println ("background-image: url('bgImg.jpg');");
      out.println ("background-repeat: no-repeat;");
      out.println ("background-size: 100% auto;");
      out.println ("}");
      out.println ("</style>");
	   out.println ("<title>Sales</title>");
       out.println ("</head>");
       out.println ("<body>");
       
       request.getRequestDispatcher("CstatSide.html").include(request, response);
        HttpSession session=request.getSession(false);
    
       if(session==null||(!(session.getAttribute("validateAdminlogin").equals("admin"))))
       {
           out.println("Please login first");
           response.sendRedirect("index.html");
       }
       else{    
         int year=Integer.parseInt(request.getParameter("year"));
             	out.println ("<table>");
                out.println("<tr><th>Month</th><th>Sales By Points</th><th>Sales By Amount</th></tr>"); 
         ArrayList<DealersPurchaseRec> dpr=Companydao.viewCompanySalesByPoint(year);
          ArrayList<Integer> dr=Companydao.viewCompanySalesByAmount(year);
           Iterator<DealersPurchaseRec> it1 = dpr.iterator();
           Iterator<Integer> it2 = dr.iterator();
           
          while (it1.hasNext() && it2.hasNext())
           {
            DealersPurchaseRec dPurRec=it1.next();
            int PurAm=it2.next();
              out.println("<tr><td id='did'>"+dPurRec.getMonth()+"</td><td id='pid'>"+dPurRec.getPurchases()+"</td><td id='items'>"+PurAm+"</td></tr>");    
            }    
       }
       out.println ("</table>");
       out.println ("</body>");
 }
}
