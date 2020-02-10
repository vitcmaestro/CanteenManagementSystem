
package canteenmanagement;

public class OrderFunctions {
    
    static int token;
    OrderFunctions()
    {
        token = 1;
    }

    

    OrderDet[] UpdateOrder(OrderDet[] od, String uname, FoodMenu[] fm,int[] order,int[] RespCount, int count, int day, int totalOrders,int token) {
        for(int i=0;i<count;i++){
        od[totalOrders]= new OrderDet(token,uname,day,fm[order[i]-1].Item,RespCount[i]);
                }
        return od;
    }

    void orderReport(OrderDet[] od,int to,String uname) {
        for(int i=0;i<to;i++)
        {
            if(od[i].UserName.equals(uname))
            {
                System.out.println(od[i].token+" "+od[i].date+" "+od[i].item+" "+od[i].count);
            }
        }
    }

    void ViewReprt(BillAmount[] ba, OrderDet[] od, int totalOrders, int token) {
        for(int i =0;i<totalOrders;i++)
        {
            System.out.println(od[i].token+" "+od[i].UserName+" "+od[i].item+" "+od[i].count+" "+od[i].date);
            if(od[i].token != od[i+1].token)
            {
                System.out.println("                                                                                Total"+ba[token]);
                token++;
            }
        }
    }

    
}
