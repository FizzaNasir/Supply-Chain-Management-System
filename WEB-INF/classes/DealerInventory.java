public class DealerInventory {
    String ProdId;
    int TotalItems;
    String D_id;

    public void setPid(String pid)
    {
        this.ProdId=pid;
    }

    public void setitems(int items)
    {
        this.TotalItems=items;
    }

    public String getPid()
    {
        return ProdId;
    }
  
    public void setDid(String did)
    {
        this.D_id=did;
    }

    public String getDid()
    {
        return D_id;
    }

    public int getitems()
    {
        return TotalItems;
    }
}
