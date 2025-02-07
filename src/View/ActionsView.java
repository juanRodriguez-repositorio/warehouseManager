/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import javax.swing.*;
import java.awt.*;
import ModelView.ActionsController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ActionsView extends JFrame {
    private JComboBox<String> categoryBox;
    private JTextField nameField, priceField, unitsField;
    private JButton createButton, modifyButton, deleteButton, backButton;
    private JLabel messageLabel;

    public ActionsView() {
        setTitle("Gestión de Productos");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(255, 240, 220));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Etiquetas y campos de entrada
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Categoría:"), gbc);
        gbc.gridx = 1;
        categoryBox = new JComboBox<>(new String[]{"technology", "bicycles", "clothing"});
        add(categoryBox, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField();
        add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Precio:"), gbc);
        gbc.gridx = 1;
        priceField = new JTextField();
        add(priceField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Unidades:"), gbc);
        gbc.gridx = 1;
        unitsField = new JTextField();
        add(unitsField, gbc);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        createButton = new JButton("Crear");
        modifyButton = new JButton("Modificar");
        deleteButton = new JButton("Eliminar");
        backButton = new JButton("Volver a lista");

        buttonPanel.add(createButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);

        gbc.gridx = 0; gbc.gridy = 4; 
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        // Mensaje en la parte inferior
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);
        gbc.gridy = 5;
        add(messageLabel, gbc);
        
        //estilos
        styleButton(createButton);
        styleButton(modifyButton);
        styleButton(backButton);
        styleRedButton(deleteButton);
        // Eventos
        createButton.addActionListener(e -> createProduct());
        modifyButton.addActionListener(e -> modifyProduct());
        deleteButton.addActionListener(e -> deleteProduct());
        backButton.addActionListener(e -> goToProductsView());

        setVisible(true);
    }

    private void createProduct() {
        try {
            String category = (String) categoryBox.getSelectedItem();
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int units = Integer.parseInt(unitsField.getText());

            ActionsController.addProduct(category, name, price, units, this);
        } catch (NumberFormatException e) {
            showMessage("Error: Ingrese valores válidos.",new Color(255,0,0));
        }
    }

    private void modifyProduct() {
        try {
            String category = (String) categoryBox.getSelectedItem();
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int units = Integer.parseInt(unitsField.getText());

            ActionsController.updateProduct(category, name, price, units, this);
        } catch (NumberFormatException e) {
            showMessage("Error: Ingrese valores válidos.",new Color(255,0,0));
        }
    }

    private void deleteProduct() {
        String category = (String) categoryBox.getSelectedItem();
        String name = nameField.getText();

        ActionsController.deleteProduct(category, name, this);
    }

    public void showMessage(String message,Color color) {
        messageLabel.setText(message);
        messageLabel.setForeground(color);
    }

    private void goToProductsView() {
        dispose();
        SwingUtilities.invokeLater(() -> new ProductsView());
    }
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
        private void styleRedButton(JButton button){
            button.setBackground(new Color(211, 47, 47)); 
            button.setForeground(Color.WHITE); // Color de texto (blanco)
            button.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente
            button.setFocusPainted(false); // Eliminar el borde de enfoque
            button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Agregar padding
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setBackground(new Color(183, 28, 28)); // Color de fondo más claro al pasar el ratón
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    button.setBackground(new Color(211, 47, 47)); // Restaurar el color original
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    UIManager.put("Button.select",new Color(136, 14, 14));
                    button.revalidate(); // Cambiar el color cuando se hace clic
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (button.getBackground() != new Color(136, 14, 14)) {
                        //button.setBackground(hoverColor);
                        UIManager.put("Button.select",new Color(183, 28, 28));
                        } else {
                        //button.setBackground(backgroundColor);
                        UIManager.put("Button.select",new Color(211, 47, 47));
                        }
            
                    } // Restaurar el color original al soltar el clic
            
                });
            
        }

}