import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Validateform extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        PrintWriter out=res.getWriter();
        String Userid=req.getParameter("uid");
        String password=req.getParameter("pass");
        if(Userid.equals("") || password.equals("")){
         out.println("Please fill the fields");
         req.getRequestDispatcher("index.html").include(req, res);        
         }

         else{
              req.getRequestDispatcher("Login").forward(req, res);
           }
}
}
