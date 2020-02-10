
package canteenmanagement;

class OpenClose {

    OpenClose()
    {}

    

    UserAccount[] Addmoney(UserAccount[] ua,int uc) {
        for(int i=0;i<uc;i++)
            ua[i].balance = 300;
        return ua;
    }
    
}
