package ru.mydroid;

import android.graphics.Bitmap;

/**
 * Created by vasily on 13.05.15.
 */
public class Tile {
    private Bitmap image;
    private int number;
    public Tile(Bitmap image, int number){
        this.image = image;
        this.number = number;
    }
    public Bitmap getImage() {
        return image;
    }

    public int getNumber() {
        return number;
    }
}
