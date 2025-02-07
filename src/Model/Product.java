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
    private String name;
    private double price;
    private String category;
    private int units;

    // Constructor
    public Product(String name,double price, String category,int units) {
        this.name = name;
        this.price= price;
        this.category = category;
        this.units=units;
    }
    public Product(){}
    // Getters y Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public int getUnits() {
        return units;
    }
    public void setUnits(int units) {
        this.units = units;
    }

    // Método toString para mostrar el producto
    @Override
    public String toString() {
        return "Producto [nombre=" + name + ", precio=" + price + ", categoria="
                + category;
    }
}
