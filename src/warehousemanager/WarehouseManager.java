/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package warehousemanager;

/**
 *
 * @author kamus
 */
import View.AuthView;
import javax.swing.SwingUtilities;
/**
 *
 * @author kamus
 */
public class WarehouseManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       SwingUtilities.invokeLater(()-> new AuthView());

   
   }

    
}
