
public class DealersPurchaseRec {
    String did;
    int purchases;
    String month;
    int year;
    String date;
    void setDid(String did)
    {
       this.did=did;
    }
    void setPurchases(int amount)
    {
       this.purchases=amount;
    }
    void setDate(String date)
    {
        this.date=date;
    }
    void setMonth(String month)
    {
       this.month=month;
    }
    void setYear(int year)
    {
        this.year=year;
    }
    String getDid()
    {
        return did;
    }
    int getPurchases()
    {
        return purchases;
    }
    String getMonth()
    {
        return month;
    }
    int getYear()
    {
        return year;
    }
    String getDate()
    {
        return date;
    }
}
