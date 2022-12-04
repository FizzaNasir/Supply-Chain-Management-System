//import java.util.*;
public class Product {
    String id;
    String title;
    String type;
    String subtype;
    String createdAt;
    int cost;
    public void setid(String id)
    {
      this.id=id;
    }

    public void settitle(String title)
    {
      this.title=title;
    }

    public void settype(String type)
    {
      this.type=type;
    }

    public void setcreatedAt(String date)
    {
      this.createdAt=date;
    }

    public void setSubtype(String Subtype)
    {
      this.subtype=Subtype;
    }
    public void setcost(int cost)
    {
        this.cost=cost;
    }

    public String getid()
    {
        return id;
    }

    public String gettitle()
    {
        return title;
    }

    public String gettype()
    {
        return type;
    }

    public String getcreatedAt()
    {
        return createdAt;
    }

    public int getcost()
    {
        return cost;
    }

    public String getsubtype()
    {
        return subtype;
    }
}
