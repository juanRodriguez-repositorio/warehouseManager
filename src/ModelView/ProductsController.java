/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelView;


import Model.FirebaseServices;
import java.util.List;
import java.util.ArrayList;
import Model.Product;
import java.util.Collections;
import java.util.Comparator;
import View.ProductsView;
import Model.ProductsCallback;

/**
 *
 * @author kamus
 */
public class ProductsController {
    public static List<Product> products;
    public static void orderProductsByAsc(ProductsView view){
    
        FirebaseServices.getAllProducts(new ProductsCallback() {
            @Override
            public void onProductsRetrieved(List<Product> products) {
                if (products == null || products.isEmpty()) {
                    System.out.println("No hay productos para ordenar.");
                    return;
                }

                // Ordenar por nombre ascendente (A-Z)
                Collections.sort(products, Comparator.comparing(Product::getName));
                ProductsController.products=products;
                view.displayProducts(products);
            }
        });
    }
    public static void orderProductsByUnits(ProductsView view){
        if (products == null || products.isEmpty()) {
            System.out.println("No hay productos para ordenar.");
            return;
        }

        // Ordenar por unidades ascendentes
        Collections.sort(products, Comparator.comparingInt(Product::getUnits));
        view.displayProducts(products);
        
    }
    public static void orderProductsByPrice(ProductsView view){
        if (products == null || products.isEmpty()) {
            System.out.println("No hay productos para ordenar.");
            return;
        }

        // Ordenar por precio ascendente
        Collections.sort(products, Comparator.comparingDouble(Product::getPrice));
        view.displayProducts(products);
        
    }
    
}
