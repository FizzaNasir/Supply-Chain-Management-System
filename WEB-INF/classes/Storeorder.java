import javax.servlet.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
public class Storeorder extends HttpServlet 
{     public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       HttpSession session=request.getSession(false);
       PrintWriter out=response.getWriter();
        if(session==null||(!(session.getAttribute("validateDealerlogin").equals("dealer"))))
        {
            out.println("Please login first");
            request.getRequestDispatcher("/index.html").include(request, response);
        }    
        else
        {
        String Did=(String)session.getAttribute("DealerUserid");
        String Pid=request.getParameter("prodid");
        int items=Integer.parseInt(request.getParameter("items"));
        Order o=new Order();
        o.setDid(Did);
        o.setPid(Pid);
        o.setitems(items);
        int status=Companydao.storeOrder(o);
        if (status>0)
        {
           out.println("Record entered successfully!");
        }
    }
    }
}

