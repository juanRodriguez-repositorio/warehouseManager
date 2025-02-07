/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model;

import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author kamus
 */
public interface ProductsCallback {
    void onProductsRetrieved(List<Product> products);
}
