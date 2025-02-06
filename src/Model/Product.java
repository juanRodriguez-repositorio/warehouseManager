/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author kamus
 */
public class Product {
    private String nombre;
    private double precio;
    private String categoria;

    // Constructor
    public Product(String nombre,double precio, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    // Getters y Setters

    public String getName() {
        return nombre;
    }

    public void setName(String nombre) {
        this.nombre = nombre;
    }


    public double getPrice() {
        return precio;
    }

    public void setPrice(double precio) {
        this.precio = precio;
    }

    public String getCategory() {
        return categoria;
    }

    public void setCategory(String categoria) {
        this.categoria = categoria;
    }

    // Método toString para mostrar el producto
    @Override
    public String toString() {
        return "Producto [nombre=" + nombre + ", precio=" + precio + ", categoria="
                + categoria;
    }
}
