public class Client {
    String Name;
    String CNIC;
    int purchases;
    public void setName(String name)
    {
       this.Name=name;
    }

    // public void setCId(String id)
    // {
    //    this.C_id=id;
    // }

    public void setCNIC(String cnic)
    {
       this.CNIC=cnic;
    }

    public String getName()
    {
     return this.Name;
    }
    
    // public String getCId()
    // {
    //  return this.C_id;
    // }

    public String getCNIC()
    {
     return this.CNIC;
    }

    public void setPurchases(int amount)
    {
          purchases=amount;
    }

    public int getPurchases()
    {
          return purchases;
    }
}
