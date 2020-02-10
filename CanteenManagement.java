
package canteenmanagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CanteenManagement {
    CanteenManagement canteen = new CanteenManagement();
    static int UserCount=0,FoodCount=0;
    static int DayCount=1;
     static Admin admin = new Admin();
     static UserFunctions uf = new UserFunctions();
     static OrderFunctions of = new OrderFunctions();
     static OpenClose oc = new OpenClose();
     static int openclose=1;
     static UserDet[] usr = new UserDet[100];
     static FoodMenu[] fm = new FoodMenu[100];
     static UserAccount[]  ua = new UserAccount[100]; 
     static OrderDet[] od = new OrderDet[200];
     static int day=0;
     static int totalOrders=0;
     static int token = 1;
     static BillAmount[] ba = new BillAmount[100];;
     
        
     
     static FoodMenu[] revive() {
        fm[0]  = new FoodMenu("idli",50,10,"u");
        fm[1] = new FoodMenu("Dosa",25,20,"1");
        fm[2] = new FoodMenu("Pongal",20,25,"u");
        fm[3] = new FoodMenu("Vada",50,10,"2");
        fm[4] = new FoodMenu("Poori",30,10,"2");
        return fm;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
        fm = revive();
        FoodCount = 5;
        System.out.println("                                        Canteen Management System                                       ");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.println("Are you 1.Admin or 2.User or 3.End Program");
        int Sysopt = Integer.parseInt(br.readLine());
        while(Sysopt !=3)
        {
        if(Sysopt == 1)
        {
            while(!admin.login())
            {
                System.out.println("Invalid!!! Try again\n");
            }
            int Adminopt = admin.provideOptions();
            while(Adminopt!=6){
            switch(Adminopt)
            {
                case 1:
                    fm = admin.AddItem(fm,FoodCount);
                    FoodCount++;
                    admin.ViewItem(fm,FoodCount);
                    break;
                case 2:
                    fm = admin.ModifyItem(fm,FoodCount);
                    admin.ViewItem(fm,FoodCount);
                    break;
                case 3:
                    fm = admin.DeleteItem(fm,FoodCount);
                    FoodCount--;
                    admin.ViewItem(fm,FoodCount);
                    break;
                case 4:
                    ua = admin.addMoney(ua,UserCount);
                    break;
                case 5:
                    String yorn;
                    if(openclose == 1)
                    {
                        System.out.println("The canteen is close, Do you want to open it? y/n");
                        yorn = br.readLine();
                        if(yorn.equalsIgnoreCase("y"))
                        {
                            day++;
                            openclose = 0;
                            fm = revive();
                            FoodCount = 5;
                            if(DayCount%3 == 0)
                            {
                                ua = oc.Addmoney(ua,UserCount);
                            }
                            admin.ViewItem(fm, FoodCount);
                            
                        }
                        
                    }
                    else
                    {
                        System.out.println("The canteen is open, do you want to close it? y/n");
                        yorn = br.readLine();
                        if(yorn.equalsIgnoreCase("y"))
                        {
                            of.ViewReprt(ba,od,totalOrders,token);
                            admin.ViewItem(fm, FoodCount);
                            DayCount++;
                            openclose = 1;
                        }
                        
                    }
                    break;
                default:
                    System.out.println("Enter valid option from above list");
            }        Adminopt = admin.provideOptions();   
                    
            }
            
        }
        else if(Sysopt == 2)
        {
            if(openclose == 1)
            {
                System.out.println("Sorry canteen is closed\n");
            }
            else
            {
            int UserOpt = uf.ProvideOptions();
            while(UserOpt !=3){
            if(UserOpt == 1)
            {
                usr = uf.SignUp(usr,UserCount);
                ua = uf.CreateAcnt(ua,usr[UserCount].name,UserCount);
                UserCount++;
                
            }
            else if(UserOpt == 2)
            {
                System.out.print("Enter username\t");
                String uname = br.readLine();
                while(!uf.login(usr,UserCount,uname))
                {
                    System.out.println("Invalid!!! Try again");
                }
                int LoginOpt = uf.ProvideLoginOpt();
                while(LoginOpt != 4)
                {
                    switch(LoginOpt)
                    {
                        case 1:
                            uf.placeOrder(fm,FoodCount,uname,ua);
                            int billAmount = uf.getTotal();
                            int[] order = uf.getOrder();
                            int[] RespCount = uf.getCount();
                            int count = uf.getTotCount();
                            if(uf.EnoughAmount(ua,uname,UserCount))
                            {
                                ua = uf.reduceMoney(ua,UserCount,uname,billAmount);
                                fm = uf.ReduceCount(fm);
                                fm = uf.deleteItem(fm,FoodCount);
                                FoodCount = uf.CountItems(fm);
                                od = of.UpdateOrder(od,uname,fm,order,RespCount,count,day,totalOrders,token);
                                ba[token] = new BillAmount(token,billAmount);
                                token++;
                                totalOrders+=count;
                            }
                            else{
                                System.out.println("Sorry, not enough balance, please re-submit your order\n");
                            }
                            
                            break;
                        case 2:
                            uf.ViewBalance(ua,UserCount,uname);
                            break;
                        case 3:
                            of.orderReport(od,totalOrders,uname);
                        default:
                            System.out.println("Invalid option,select any of options above");
                            LoginOpt = Integer.parseInt(br.readLine());
                    }
                    LoginOpt = uf.ProvideLoginOpt();

                }
                System.out.println("Logged out");
                
            }
            
            else
            {
                System.out.println("Invalid Selections,try again");
                
            }
            UserOpt = uf.ProvideOptions();
            }
        }
        }
        else if(Sysopt == 3)
                System.out.println("Thank you");
        else
        {
            System.out.println("Invalid Option");
        }
        System.out.println("1.Admin or 2.user 3.End program");
                Sysopt = Integer.parseInt(br.readLine());
        }
        
            }
    
}
