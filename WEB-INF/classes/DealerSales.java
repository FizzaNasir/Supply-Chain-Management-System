
public class DealerSales {
    String did;
    int sales;
    String month;
    int year;
    String date;
    void setDid(String did)
    {
       this.did=did;
    }
    void setSales(int amount)
    {
       this.sales=amount;
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
    int getSales()
    {
        return sales;
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
