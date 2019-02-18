package by.bsuir.karamach.model.figure.impl;

import by.bsuir.karamach.model.figure.AbstractFigure;
import by.bsuir.karamach.model.figure.basic.Point;

import java.util.Objects;
import java.util.StringJoiner;

public class Circle extends AbstractFigure {
    private static final long serialVersionUID = 6946080811820833527L;

    private Point center;
    private double radius;

    public Circle() {
    }

    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Circle circle = (Circle) o;
        return Double.compare(circle.getRadius(), getRadius()) == 0 &&
                Objects.equals(getCenter(), circle.getCenter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCenter(), getRadius());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Circle.class.getSimpleName() + "[", "]")
                .add("center=" + center)
                .add("radius=" + radius)
                .toString();
    }
}
