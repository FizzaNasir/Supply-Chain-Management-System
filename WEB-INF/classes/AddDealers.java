import javax.servlet.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
public class AddDealers extends HttpServlet 
{     public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       HttpSession session=request.getSession(false);
       PrintWriter out=response.getWriter();
        if(session==null||(!(session.getAttribute("validateAdminlogin").equals("admin"))))
        {
            out.println("Please login first");
            response.sendRedirect("index.html");
        }    
        else
        {
        String dname =request.getParameter("dname");
        //int items=Integer.parseInt(request.getParameter("items"));
        String d_id =request.getParameter("did");
        String password =request.getParameter("pass");
        String email =request.getParameter("email");
        String district =request.getParameter("dist");
        Dealer d=new Dealer();
        d.setName(dname);
        d.setUserId(d_id);
        d.setPassword(password);
        d.setEmail(email);
        d.setDistrict(district);
        int status=Companydao.AddDealers(d);
        if (status>0)
        {
           out.println("Record entered successfully!");
        }
    }
    }
}

