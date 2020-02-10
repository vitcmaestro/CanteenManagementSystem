/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canteenmanagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class UserFunctions {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int total;
    int[] order;
    int[] c;
    int count;
    UserFunctions()
    {
        order = new int[10];
        c = new int[10];
        count =0;
        total =0;
    }
    
    
    int ProvideOptions() throws IOException {
        System.out.println("Select any of the options\t1.Signup\t.2.Login\t3.Quit");
        return Integer.parseInt(br.readLine());
    }

    
    
    UserDet[] SignUp(UserDet[] usr, int UserCount) throws IOException {
        System.out.print("Enter the username\t");
        String name = br.readLine();
        int unique = 0;
        while(unique != 1)
        {
            for(int i=0;i<UserCount;i++)
            {
                if(usr[i].name.equalsIgnoreCase(name))
                {
                    System.out.println("User name already there, try some other");
                    unique = 1;
                }
            }
            if(unique==0)
            {
                System.out.println("Set password");
                String pwd = br.readLine();
                usr[UserCount] = new UserDet(name,encrypted(pwd));
            } 
            System.out.println("Account created");
            unique = 1;
        }
        return usr;
    }

    
    UserAccount[] CreateAcnt(UserAccount[] ua,String name,int uc) {
        ua[uc] = new UserAccount(name,300);
        return ua;
    }
    
    
    
    boolean login(UserDet[] usr,int UserCount,String uname) throws IOException {
        
        System.out.print("Enter the password\t");
        String pwd = br.readLine();
        for(int i=0;i<UserCount;i++)
            {
                if(usr[i].name.equalsIgnoreCase(uname) && decrypted(usr[i].pwd).equals(pwd))
                {
                    return true;
                }
            }
        return false;
    }

    
    
    int ProvideLoginOpt() throws IOException {
        System.out.println("Select any one\n1.Place order\t2.View Balance \t3.View Order Report\t4.quit");
        return Integer.parseInt(br.readLine());
    }
 
    
    
    void placeOrder(FoodMenu[] fm,int fc,String uname,UserAccount[] ua) throws IOException {
        System.out.println("Enter the item number you want to order as per availability and press 'n' to quit");
        System.out.println("S.no\tItemName\tMax For A Person\tTotal Remaining");
        for(int i=0;i<fc;i++)
        {
            System.out.println((i+1)+"\t"+fm[i].Item+"\t"+fm[i].max+"\t\t"+fm[i].count);
        }
        String cntnue = "y";
        
        while(!cntnue.equalsIgnoreCase("n")){
            System.out.println("Enter the item id you want");
        order[count] = Integer.parseInt(br.readLine());
        System.out.println("Enter the count you want");
        c[count] = Integer.parseInt(br.readLine());
        while((fm[order[count]-1].equals("u") && (c[count]<0 || c[count]>fm[order[count]-1].count)) || (c[count]<0) || (c[count] > Integer.parseInt(fm[order[count]-1].max)) || (c[count] > fm[order[count]-1].count))
        {
            System.out.println("Item request count exceeded or less than zero, try again!!!");
            c[count] = Integer.parseInt(br.readLine());
        }
        total+= fm[order[count]-1].price*c[count];
        count++;
        System.out.println("Do you want to order more y/n");
        cntnue = br.readLine();
        while(cntnue.length() >1 && !cntnue.equalsIgnoreCase("y") && !cntnue.equalsIgnoreCase("n"))
        {
            System.out.println("Enter only y/n");
            cntnue = br.readLine();
        }
        }
    }

    
    
    void ViewBalance(UserAccount[] ua,int uc,String uname) {
        int found =0;
        for(int i=0;i<uc;i++)
        {
            if(ua[i].name.equalsIgnoreCase(uname))
            {
                found = 1;
                System.out.println("Your balance is "+ua[i].balance);
            }
        }
        if(found == 0)
        {
            System.out.println("Account Not Created");
        }
    }

    
    
    FoodMenu[] ReduceCount(FoodMenu[] fm) {
        for(int i =0;i<count;i++)
        fm[order[i]-1].count--;
        
        return fm;
    }

    
    
    FoodMenu[] deleteItem(FoodMenu[] fm,int fc) {
        for(int j =0;j<fc-1;j++)
        {
            if(fm[j].count == 0)
            {
                for(int i =j;i<fc-1;i++)
                {
                    fm[i] = fm[i+1];
                }
            }
            fm[fc] = null;
            fc--;
        }
        
        return fm;
    }

    
    
    int CountItems(FoodMenu[] fm) {
        int c=0;
        while(fm[c] != null)
            c++;
        return c;
    }

    
    
    boolean EnoughAmount(UserAccount[] ua, String uname,int uc) {
        for(int i=0;i<uc;i++){
            if(ua[i].name.equalsIgnoreCase(uname))
            {
                if(ua[i].balance >= total)
                {
                    total =0;
                    for(int j=0;j<count;j++)
                    {
                        order[j] =0;
                        c[j] = 0;
                    }
                    count = 0;
                    return true;
                }
            }
        }
        total = 0;
        
        for(int j=0;j<count;j++)
        {
            order[j] =0;
            c[j] = 0;
        }
        count = 0;
        return false;
    }
    
    
    
    int getTotal(){
            return total;
    }

    
    
    UserAccount[] reduceMoney(UserAccount[] ua, int UserCount, String uname, int billAmount) {
        for(int i=0;i<UserCount;i++)
        {
            if(ua[i].name.equalsIgnoreCase(uname))
            {
                ua[i].balance-=billAmount;
            }
        }
        return ua;
    }

    int[] getOrder() {
        return order;
    }

    int[] getCount() {
        return c;
    }

    int getTotCount() {
        return count;
    }

    private String encrypted(String pwd) {
        String res = "";
        for(int i=0;i<pwd.length();i++)
        {
            res = String.valueOf((char)(pwd.charAt(i)+1))+(res);
        }
        return res;
    }

    private Object decrypted(String pwd) {
        String res = "";
        for(int i=0;i<pwd.length();i++)
        {
            res = String.valueOf((char)(pwd.charAt(i)-1))+(res);
        }
        return res;
    }

    

    

    
    
}
