package by.bsuir.karamach.model.figure.impl;

import by.bsuir.karamach.model.figure.Printable;
import by.bsuir.karamach.model.figure.basic.Point;

import java.awt.*;
import java.util.Objects;
import java.util.StringJoiner;

public class Ellipse implements Printable {
    private static final long serialVersionUID = 7276418754703032726L;

    private Point center;
    private int width;
    private int height;

    public Ellipse() {
    }

    public Ellipse(Point center, int width, int height) {
        this.center = center;
        this.width = width;
        this.height = height;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Ellipse ellipse = (Ellipse) o;
        return getWidth() == ellipse.getWidth() &&
                getHeight() == ellipse.getHeight() &&
                Objects.equals(getCenter(), ellipse.getCenter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCenter(), getWidth(), getHeight());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Ellipse.class.getSimpleName() + "[", "]")
                .add("center=" + center)
                .add("width=" + width)
                .add("height=" + height)
                .toString();
    }

    @Override
    public void print(Graphics2D graphics2D) {
        graphics2D.drawOval(center.getX() - width, center.getY() - height, width * 2, height * 2);
    }
}
