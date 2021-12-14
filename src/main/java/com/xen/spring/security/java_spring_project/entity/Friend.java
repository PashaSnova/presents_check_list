package com.xen.spring.security.java_spring_project.entity;

import javax.persistence.*;

@Table(name = "friends")
@Entity
public class Friend implements Comparable<Friend> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "friend")
    private String friend;

    public Friend() {
    }

    public Friend(String username, String friend) {
        this.username = username;
        this.friend = friend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    @Override
    public int compareTo(Friend o) {
        return friend.compareTo(o.getFriend());
    }
}

