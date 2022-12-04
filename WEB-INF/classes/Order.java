public class Order {
    String Dealerid;
    String ProdId;
    int no_of_items;

    public void setDid(String did)
    {
       this.Dealerid=did;
    }

    public void setPid(String pid)
    {
       this.ProdId=pid;
    }

    public void setitems(int item)
    {
       this.no_of_items=item;
    }

    public String getDid()
    {
        return Dealerid;
    }

    public String getPid()
    {
        return ProdId;
    }
    
    public int getitems()
    {
        return no_of_items;
    }
}
