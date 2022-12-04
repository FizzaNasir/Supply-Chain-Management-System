import javax.servlet.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class ViewRecord extends HttpServlet {
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
	   out.println ("<title>Client Record</title>");
       out.println ("<style>");
        out.println ("table{");
          out.println ("      margin-right: 155px;");
         out.println ("       margin-top: -568px;");
         out.println ("}");     
       out.println ("#r1{");
        out.println ("    background-color: whitesmoke;");
        out.println ("}");
        out.println ("#r1 a{");
        out.println ("    color: black;");
        out.println (" }");
        out.println (" </style>");
       out.println ("</head>");
       out.println ("<body>");
        HttpSession session=request.getSession(false);
        String Did=(String)session.getAttribute("DealerUserid");
       if(session==null||(!(session.getAttribute("validateDealerlogin").equals("dealer"))))
       {
           out.println("Please login first");
           response.sendRedirect("index.html");
       }
       else{         
		out.println ("<table>");
                out.println("<tr><th>Name</th><th>CNIC</th><th>Purchases</th></tr>"); 
           ArrayList<Client> clientRec=Dealerdao.viewRecord(Did);
           for(Client c: clientRec)
           {
	      
              out.println("<tr><td>"+c.getName()+"</td><td>"+c.getCNIC()+"</td><td>"+c.getPurchases()+"</td></tr>");
           }
           request.getRequestDispatcher("Clientside.html").include(request, response);
       }
       out.println ("<table>");
       out.println ("</body>");
       
}
}
