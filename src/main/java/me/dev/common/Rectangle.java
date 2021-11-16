package me.dev.common;

public class Rectangle {
    int x;
    int y;
    int width;
    int height;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean inRectangle(int x1, int y1) {
        return x1 >= x && x <= (x + width) && y1 >= y && y1 <= (y + height);
    }
}
