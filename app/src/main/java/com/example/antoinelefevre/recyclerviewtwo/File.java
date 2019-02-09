package com.example.antoinelefevre.recyclerviewtwo;

import android.graphics.drawable.Drawable;
import android.media.Image;

import java.sql.Timestamp;

public class File {
    public enum FileType {
        PDF, EXCEL, WORD, DEFAULT
    }
    private String name;
    private String author;
    private Timestamp createdAt;
    private FileType type;

    public File(String name, String author, Timestamp createdAt) {
        this.name = name;
        this.author = author;
        this.createdAt = createdAt;
        this.type = FileType.DEFAULT;
    }

    public File(String name, String author, Timestamp createdAt, FileType type) {
        this.name = name;
        this.author = author;
        this.createdAt = createdAt;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public FileType getType() {
        return type;
    }

    public void setType(FileType type) {
        this.type = type;
    }
}
