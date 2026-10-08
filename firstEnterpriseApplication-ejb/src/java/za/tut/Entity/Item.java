/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.tut.Entity;

import jakarta.persistence.Entity;
import java.io.Serializable;

/**
 *
 * @author CodeWithLufuno
 */
@Entity
public class Item implements Serializable {
    private int id;
    private String name;
    private double price;

    //default constructor
    public Item() {
    }
        
    public Item(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "item{" + "id=" + id + ", name=" + name + ", price=" + price + '}';
    }
    
    
}
