package com.angie.inventory.inventory_management.model; //defines the package where it belongs package declaration 

import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int quantity;
    private double price;

    //default constructor
    public Item(){
    }
    //parameterized constructor
    
    public Item(String name, int quantity, double price){
        this.name = name;
        this.quantity =quantity;
        this.price = price;
        }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice (double price) {
        this.price = price;
    }
    
}
