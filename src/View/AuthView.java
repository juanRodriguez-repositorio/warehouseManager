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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ModelView.AuthController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AuthView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel titleLabel;
    private JLabel messageLabel;
    private Color backgroundColor = new Color(0, 123, 255);
    private Color hoverColor = new Color(0, 105, 217);
    private Color pressedColor = new Color(0, 85, 173);

    public AuthView() {
        // Configuración de la ventana
        setTitle("Autenticarse como Admin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setSize(750, 500);
        getContentPane().setBackground(new Color(255, 240, 220)); // Color piel claro

        // Crear el layout GridBagLayout
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        // Crear un GridBagConstraints con el centro configurado
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Espaciado entre los componentes
        // Crear los componentes
        titleLabel = new JLabel("Autenticarse como Admin", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Iniciar Sesión");
        messageLabel=new JLabel();
        
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(backgroundColor);
        loginButton.setForeground(Color.WHITE);
        loginButton.setPreferredSize(new Dimension(140, 40));
        loginButton.setBorderPainted(false);
        loginButton.setFocusable(false);
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { loginButton.setBackground(hoverColor); }
            @Override
            public void mouseExited(MouseEvent e) { loginButton.setBackground(backgroundColor); }
            @Override
            public void mousePressed(MouseEvent e) { 
                UIManager.put("Button.select",pressedColor);
                loginButton.revalidate();
            }
            @Override
            public void mouseReleased(MouseEvent e) { 
                if (loginButton.getBackground() != pressedColor) {
                     //button.setBackground(hoverColor);
                     UIManager.put("Button.select",hoverColor);
                     } else {
                       //button.setBackground(backgroundColor);
                       UIManager.put("Button.select",backgroundColor);
                     }
            
            }
        });
        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginButton.setEnabled(false);
                AuthController.loginUser(usernameField.getText(), new String(passwordField.getPassword()).trim(), AuthView.this);
            }
        });

        gbc.gridx = 0; gbc.gridy = 0; 
        gbc.gridwidth = 2; 
        add(titleLabel, gbc);
        gbc.gridwidth = 1;
        // Agregar componentes con GridBagLayout
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Usuario:"), gbc);

        gbc.gridx = 1;
        add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Contraseña:"), gbc);

        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        add(loginButton, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        add(messageLabel, gbc);
        

        // Hacer la ventana visible
        setVisible(true);
    }
    public void showErrorMessage(String error) {
        this.messageLabel.setText(error);
        this.messageLabel.setForeground(new Color(200, 20, 0)); // Rojo
        loginButton.setEnabled(true);
    }
    public void showLoadingMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            messageLabel.setText(message);
            messageLabel.setForeground(Color.BLACK);
        });
    }
     
}