public class Dealer {
    String Name;
    String UserId;
    String Email;
    String Password;
    String District;
    int Purchases;

    public void setName(String name)
    {
       this.Name=name;
    }

    public void setUserId(String userid)
    {
       this.UserId=userid;
    }

    public void setPassword(String pass)
    {
       this.Password=pass;
    }
  
    public void setEmail(String email)
    {
       this.Email=email;
    }

    public void setDistrict(String dis)
    {
       this.District=dis;
    }
    
    public void setPur(int pur)
    {
       this.Purchases=pur;
    }

    public String getDistrict()
    {
     return this.Name;
    }

    public String getName()
    {
     return this.Name;
    }
    
    public String getUserId()
    {
     return this.UserId;
    }

    public String getPassword()
    {
       return Password;
    }

    public String getEmail()
    {
       return Email;
    }

    public int getPur()
    {
     return this.Purchases;
    }
}

