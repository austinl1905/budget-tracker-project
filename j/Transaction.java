import java.util.Date;

class Transaction
{   private String category;
    private Date date;
    private double amount;

    public Transaction(double amount, String category)
    {   this.date = new Date();
        this.amount = amount;
        this.category = category;
    }

    public double getAmount()
    {   return this.amount;   }

    public Date getDate()
    {   return this.date;   }

    public String getCategory()
    {   return this.category;   }

    @Override
    public String toString()
    {   return "Amount: " + this.amount + "/nDate created: " + this.date + "/nCategory: " + this.category;   }

}