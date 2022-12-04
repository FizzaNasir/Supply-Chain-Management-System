import javax.servlet.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
public class AddItemsInDealerInventory extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {   HttpSession session=request.getSession(false);
         PrintWriter out=response.getWriter();
        if(session==null||(!(session.getAttribute("validateAdminlogin").equals("admin"))))
        {
            out.println("Please login first");
            response.sendRedirect("index.html");
        }
        else{

        String D_id=request.getParameter("did");
        String P_id=request.getParameter("pid");
        int items=Integer.parseInt(request.getParameter("noOfItems"));
        DealerInventory DIn=new DealerInventory();
        DIn.setDid(D_id);
        DIn.setPid(P_id);
        DIn.setitems(items);
        int cost=Productdao.getProductById(P_id);
        int amount=items*cost;
        int status=Companydao.AddItemsInDealerInventory(DIn);
        response.sendRedirect("ShowOrderDetails");
        LocalDate currentdate = LocalDate.now();
        String date=currentdate.toString();

        Month currentMonth = currentdate.getMonth();
        String month=currentMonth.toString();

        int year = currentdate.getYear();
        DealersPurchaseRec dpr =new DealersPurchaseRec();
        dpr.setDid(D_id);
        dpr.setPurchases(amount);
        dpr.setDate(date);
        dpr.setMonth(month); 
        dpr.setYear(year);
        int status1=Companydao.addDealersPurRec(dpr);


        }
    }
}
