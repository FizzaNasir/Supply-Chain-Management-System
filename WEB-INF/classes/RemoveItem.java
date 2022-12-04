import javax.servlet.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class RemoveItem extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {   HttpSession session=request.getSession(false);
		
         PrintWriter out=response.getWriter();
        if(session==null||(!(session.getAttribute("validateAdminlogin").equals("admin"))))
        {
            out.println("Please login first");
            request.getRequestDispatcher("/index.html").include(request, response);
        }
        else{
        String P_id=request.getParameter("pid");
        String title=request.getParameter("title");
        String type=request.getParameter("type");
        String subtype=request.getParameter("Subtype");
        String createdat=request.getParameter("Createdat");
        int cost=Integer.parseInt(request.getParameter("cost"));
        Product prod=new Product();
        prod.setid(P_id);
        prod.settitle(title);
        prod.settype(type);
        prod.setSubtype(subtype);
        prod.setcreatedAt(createdat);
        prod.setcost(cost);
        int status=Dealerdao.removeProduct(prod);
        response.sendRedirect("ViewInventory");
        }
    }
}
