import javax.servlet.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class ViewCompanyProducts extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {   
        PrintWriter out=response.getWriter();
       out.println ("<!DOCTYPE html>");
       out.println ("<html lang='en'>");
       out.println ("<head>");
       out.println ("<link rel='stylesheet' href='ViewRecord.css'>");
       out.println("<link rel='preconnect' href='https://fonts.googleapis.com'>");
       out.println("<link rel='preconnect' href='https://fonts.gstatic.com' crossorigin>");
       out.println("<link href='https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:wght@100&display=swap' rel='stylesheet'>");
       out.println("<link href='https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:wght@100&family=Ubuntu:wght@300&display=swap' rel='stylesheet'>") ;
	   out.println ("<meta charset='UTF-8'>");
	   out.println ("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
	   out.println ("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
       out.println ("<style>");
       out.println ("table{");
       out.println ("margin-right: 167px;");
       out.println ("margin-top: -396px");
       out.println ("}");
	   out.println ("<title>Client Record</title>");
       out.println ("</head>");
       out.println ("<body>");
        HttpSession session=request.getSession(false);
    
       if(session==null||(!(session.getAttribute("validateDealerlogin").equals("dealer"))))
       {
           out.println("Please login first");
           response.sendRedirect("index.html");
       }
       else{         
		out.println ("<table>");
                out.println("<tr><th>ID</th><th>Title</th><th>Type</th><th>SubType</th><th>CreatedAt</th><th>Cost</th></tr>"); 
           ArrayList<Product> prod=Dealerdao.viewCompanyProducts();
           for(Product p: prod)
           {
	      
              out.println("<tr><td>"+p.getid()+"</td><td>"+p.gettitle()+"</td><td>"+p.gettype()+"</td><td>"+p.getsubtype()+"</td><td>"+p.getcreatedAt()+"</td><td>"+p.getcost()+"</td></tr>");
           }
           request.getRequestDispatcher("CompanySide.html").include(request, response);
       }
       out.println ("<table>");
       out.println ("</body>");
}
}
