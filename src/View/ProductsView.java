/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author kamus
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import Model.Product;
import ModelView.ProductsController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.TableCellRenderer;


public class ProductsView extends JFrame {
    private JTable productsTable;
    private DefaultTableModel tableModel;
    private List<Product> products;
   

    public ProductsView() {
        // Configuración de la ventana
        setTitle("Productos");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana
        setBackground(new Color(255, 240, 220)); // Color piel claro
        

        // Crear el modelo de la tabla
        String[] columnNames = {"Nombre", "Precio", "Unidades", "Categoría"};
        tableModel = new DefaultTableModel(columnNames, 0);
        productsTable = new JTable(tableModel);

        // Llenar la tabla con los productos
        ProductsController.orderProductsByAsc(this);

        // Crear botones de ordenamiento
        JPanel panel = new JPanel();
        JButton sortByUnitsButton = new JButton("Ordenar por unidades");
        JButton sortByPriceButton = new JButton("Ordenar por Precio");
        JButton goToModifyButton= new JButton("Operaciones en los productos");
        
        //darle estilos a los botones
        
        styleButton(sortByUnitsButton);
        styleButton(sortByPriceButton);
        styleButton(goToModifyButton);

        sortByUnitsButton.addActionListener(e -> ProductsController.orderProductsByUnits(ProductsView.this));
        sortByPriceButton.addActionListener(e -> ProductsController.orderProductsByPrice(ProductsView.this));

        panel.add(sortByUnitsButton);
        panel.add(sortByPriceButton);
        panel.add(goToModifyButton);
        
        //estilos adicionales
        
        panel.setBackground(new Color(255, 240, 220));
        productsTable.setBackground(new Color(255, 240, 220));
        productsTable.setGridColor(new Color(255, 240, 220)); // Color de las líneas de la cuadrícula

        // Configurar el renderizador de las celdas para que tengan el color de fondo piel
        TableCellRenderer renderer = productsTable.getDefaultRenderer(Object.class);
        productsTable.setDefaultRenderer(Object.class, (table, value, isSelected, hasFocus, row, column) -> {
            Component c = renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            c.setBackground(new Color(255, 240, 220)); // Fondo color piel
            return c;
        });
        
        JScrollPane scrollPane=new JScrollPane(productsTable);
        scrollPane.setBackground(new Color(255, 240, 220));
        scrollPane.setOpaque(false);

        // Configurar el layout
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        setVisible(true);
    }

    // Método para cargar los productos en la tabla
    private void loadTableData() {
        tableModel.setRowCount(0); // Limpiar la tabla antes de cargar nuevos datos

        for (Product product : products) {
            Object[] row = {product.getName(), product.getPrice(), product.getUnits(), product.getCategory()};
            tableModel.addRow(row);
        }
    }
    // Método para darle estilo a los botones
    private void styleButton(JButton button) {
        button.setBackground(new Color(0, 123, 255)); // Color de fondo (azul)
        button.setForeground(Color.WHITE); // Color de texto (blanco)
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente
        button.setFocusPainted(false); // Eliminar el borde de enfoque
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Agregar padding
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(0, 105, 217)); // Color de fondo más claro al pasar el ratón
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(0, 123, 255)); // Restaurar el color original
            }

            @Override
            public void mousePressed(MouseEvent e) {
                UIManager.put("Button.select",new Color(0, 85, 173));
                button.revalidate(); // Cambiar el color cuando se hace clic
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (button.getBackground() != new Color(0, 85, 173)) {
                     //button.setBackground(hoverColor);
                     UIManager.put("Button.select",new Color(0, 105, 217));
                     } else {
                       //button.setBackground(backgroundColor);
                       UIManager.put("Button.select",new Color(0, 123, 255));
                     }
            
                } // Restaurar el color original al soltar el clic
            
            });
        }

    // Método para actualizar los productos ordenados
    public void displayProducts(List<Product> sortedProducts) {
        this.products = sortedProducts;  // Actualiza la lista de productos
        loadTableData();  // Actualiza la tabla con los productos ordenados
    }

    // Ordenar productos por nombre (ascendente)
    

    
}