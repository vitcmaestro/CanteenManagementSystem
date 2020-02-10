/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canteenmanagement;

/**
 *
 * @author MS
 */
class FoodMenu {
    String Item;
    int count;
    int price;
    String max;
    FoodMenu(String name, int count, int price, String max) {
        this.Item = name;
        this.count = count;
        this.price = price;
        this.max = max;
    }
    
}
