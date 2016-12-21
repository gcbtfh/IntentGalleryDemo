package com.example.tonghung.intentgallerydemo.object;

import android.graphics.Bitmap;

/**
 * Created by tonghung on 21/12/2016.
 */

public class Picture {
    private int id;
    private String name;
    private Bitmap bitmap;

    public Picture() {
    }

    public Picture(int id, String name, Bitmap bitmap) {
        this.id = id;
        this.name = name;
        this.bitmap = bitmap;
    }

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

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
