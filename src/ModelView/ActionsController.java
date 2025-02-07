/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelView;

import Model.FirebaseServices;
import Model.Product;
import View.ActionsView;
import java.awt.Color;
/**
 *
 * @author kamus
 */
public class ActionsController {
    public static void addProduct(String category, String name,Double price,int units,ActionsView view){
        if(category.equals("bicycles") || category.equals("clothing") || category.equals("technology")){
            Product product=new Product(name,price,category,units);
            FirebaseServices.pushProductToCategory(product, category,view);
        }
        
    }
    public static void updateProduct(String categoryKey,String productKey, Double price,int units,ActionsView view){
        if(categoryKey.equals("bicycles") || categoryKey.equals("clothing") || categoryKey.equals("technology")){
            if(productKey.length()>40){return;}
                FirebaseServices.updateProductAttribute(categoryKey, productKey, price, units, view);
            
            }
        }
    public static void deleteProduct(String categoryKey,String name,ActionsView view){
        if(categoryKey.equals("bicycles") || categoryKey.equals("clothing") || categoryKey.equals("technology")){
            FirebaseServices.deleteProduct(categoryKey, name, view);
        }
        
    }
    public static void showMessage(String message,Color color,ActionsView view){
        view.showMessage(message,color);
    }
    
}
