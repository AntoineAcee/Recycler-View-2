package com.example.antoinelefevre.recyclerviewtwo;

import android.media.Image;

public class User {
    private String name;
    private Image image;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

    public int getImageRessource() {
        return R.drawable.ic_user;
    }
}
