
package canteenmanagement;

public class OrderDet {
    int date;
    String item;
    String UserName;
    int count;
    int token;
    OrderDet(int token,String name,int date,String item,int count)
    {
        this.token = token;
        this.UserName = name;
        this.date = date;
        this.item = item;
        this.count = count;
    }

  
}
