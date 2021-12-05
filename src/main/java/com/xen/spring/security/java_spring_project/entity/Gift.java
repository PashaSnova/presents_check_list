package com.xen.spring.security.java_spring_project.entity;

import javax.persistence.*;

@Table(name = "check_list")
@Entity
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "gift")
    private String gift;

    @Column(name = "price")
    private String price;

    public Gift() { }

    public Gift(String username, String price) {
        this.username = username;
        this.price = price;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }
}
