import javax.servlet.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
public class Addrecord extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {    PrintWriter out=response.getWriter();
         HttpSession session=request.getSession(false);
	 
        if(session==null||(!(session.getAttribute("validateDealerlogin").equals("dealer"))))
        {
            out.println("Please login first");
            response.sendRedirect("index.html");
        }
        else{
        String Did=(String)session.getAttribute("DealerUserid");
        String name=request.getParameter("Name");
        String cnic=request.getParameter("cnic");
        String p_id=request.getParameter("prodid");
         int items=Integer.parseInt(request.getParameter("items"));
        Client c=new Client();
        int cost=Productdao.getProductById(p_id);
        int amount=items*cost;
        c.setName(name);
        c.setCNIC(cnic);
        c.setPurchases(amount);

        LocalDate currentdate = LocalDate.now();
        String date=currentdate.toString();

        Month currentMonth = currentdate.getMonth();
        String month=currentMonth.toString();

        int year = currentdate.getYear();
        DealerSales ds=new DealerSales();
        ds.setDid(Did);
        ds.setSales(amount);
        ds.setMonth(month);
        ds.setYear(year);
        ds.setDate(date);
        int check=Dealerdao.addInfoRecord(c, Did);
        int check2=Dealerdao.addPurchaseRecord(c);
        int check3=Dealerdao.addDealerSalesRecord(ds);
        out.println("Record added successfully");
        }
    }
}
