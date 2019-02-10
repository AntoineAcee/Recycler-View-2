package com.example.antoinelefevre.recyclerviewtwo;

import android.media.Image;

public class User {
    private String name;
    private Image image;

    public User(String name, Image image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }
}
