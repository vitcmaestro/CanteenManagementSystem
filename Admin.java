package canteenmanagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Admin{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    
    
    boolean login() throws IOException {
        System.out.println("Enter the userName\t");
        String name = br.readLine();
        System.out.println("Enter the password");
        String pwd = br.readLine();
        return name.equalsIgnoreCase("admin") && pwd.equals("admin");
    }

    
    
    
    int provideOptions() throws IOException {
        System.out.println("What do you want to do?\n1.AddItem\t2.ModifyItem\t3.DeleteItem\t4.AddMoney\t5.Open/close Canteen\t6.Quit");
        return Integer.parseInt(br.readLine());
    }

   
    
    
    UserAccount[] addMoney(UserAccount[] ua, int UserCount) throws IOException {
        System.out.print("Enter The username\t");
        String name = br.readLine();
        int found = 0;
        for(int i=0;i<UserCount;i++)
        {
         if(ua[i].name.equalsIgnoreCase(name))
         {
             found = 1;
             System.out.println("Enter the amount\t");
             int money = Integer.parseInt(br.readLine());
             ua[i].balance += money;
         }
        }
        return ua;
    }

    
    
    
    FoodMenu[] DeleteItem(FoodMenu[] fm, int FoodCount) throws IOException {
        System.out.println("Enter the item name to delete");
        String name = br.readLine();
        int found =0;
        for(int i=0;i<FoodCount-1;i++)
        {
            if(fm[i].Item.equalsIgnoreCase(name))
            {
                found = 1;
                fm[i] = fm[i+1];
            }
        }
        fm[FoodCount-1] = null;
        return fm;
    }

    
    
    
    FoodMenu[] ModifyItem(FoodMenu[] fm, int FoodCount) throws IOException {
        int found = 0;
        while(found != 1){
        System.out.println("Enter the item name to modify");
        String name = br.readLine();
        for(int i=0;i<FoodCount;i++)
        {
            if(fm[i].Item.equalsIgnoreCase(name))
            {
                found = 1;
                System.out.println("What has to be modified?\n1.count\t2.price\t3.Maximum available per person");
                int opt = Integer.parseInt(br.readLine());
                while(opt>3 || opt<1){
                switch(opt)
                {
                    case 1:
                        int count =getCount();
                        fm[i].count = count;
                        break;
                    case 2:
                        int price = getPrice();
                        fm[i].price = price;
                        break;
                    case 3:
                        String max = getMax();
                        fm[i].max = max;
                        break;
                    default:
                        System.out.print("Invalid option");
                }
                }
            }
        }
            if(found == 0) {
                System.out.println("Item not found!!!");
            }
        }
        return fm;
    }

    
    
    
    
    void ViewItem(FoodMenu[] fm, int FoodCount) {
        for(int i=0;i<FoodCount;i++)
            System.out.println(fm[i].Item+" "+fm[i].count+" "+fm[i].price+" "+fm[i].max);
    }

    
    
    
    
    FoodMenu[] AddItem(FoodMenu[] fm, int FoodCount) throws IOException {
        
        System.out.println("Enter the item name\t");
        String name = br.readLine();
        int count = getCount();
        int price = getPrice();
        String max = getMax();
        System.out.println(FoodCount);
        fm[FoodCount] = new FoodMenu(name,count,price,max);
        return fm;
    }

    
    
    
    
    private int getCount() throws IOException {
        System.out.println("Enter the count available\t");
        int count = Integer.parseInt(br.readLine());
        while(count <0)
        {
            System.out.println("Invalid count, Enter again count:");
            count = Integer.parseInt(br.readLine());
        }
        return count;
    }

    
    
    
    
    private int getPrice() throws IOException {
        System.out.println("Enter the price per item\t");
        int price = Integer.parseInt(br.readLine());
        while(price<0)
        {
            System.out.println("Invalid price, enter again");
            price = Integer.parseInt(br.readLine());
        }
        return price;
    }

    
    
    
    
    private String getMax() throws IOException {
        System.out.println("Enter the max available per head");
        String max = br.readLine();
        while(!max.equalsIgnoreCase("u") && !checkIsNum(max))
        {
            System.out.println("Enter the maximum limit per person");
            max = br.readLine();
        }
        return max;
    }

    
    
    
    
    private boolean checkIsNum(String max) {
        
        for(int i=0;i<max.length();i++)
        {
            if((int)max.charAt(i)>57 || (int)max.charAt(i)<48)
                return false;
            
        }
        return true;
    }

}
