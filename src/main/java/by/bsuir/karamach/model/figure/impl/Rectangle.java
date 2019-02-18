package by.bsuir.karamach.model.figure.impl;

import by.bsuir.karamach.model.figure.AbstractFigure;
import by.bsuir.karamach.model.figure.basic.Point;

import java.util.Objects;
import java.util.StringJoiner;

public class Rectangle extends AbstractFigure {
    private static final long serialVersionUID = 8363803837365699462L;

    private Point leftTopCorner;
    private Point rightBotCorner;

    public Rectangle() {
    }

    public Rectangle(Point leftTopCorner, Point rightBotCorner) {
        this.leftTopCorner = leftTopCorner;
        this.rightBotCorner = rightBotCorner;
    }

    public Point getLeftTopCorner() {
        return leftTopCorner;
    }

    public void setLeftTopCorner(Point leftTopCorner) {
        this.leftTopCorner = leftTopCorner;
    }

    public Point getRightBotCorner() {
        return rightBotCorner;
    }

    public void setRightBotCorner(Point rightBotCorner) {
        this.rightBotCorner = rightBotCorner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Rectangle rectangle = (Rectangle) o;
        return Objects.equals(getLeftTopCorner(), rectangle.getLeftTopCorner()) &&
                Objects.equals(getRightBotCorner(), rectangle.getRightBotCorner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLeftTopCorner(), getRightBotCorner());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Rectangle.class.getSimpleName() + "[", "]")
                .add("leftTopCorner=" + leftTopCorner)
                .add("rightBotCorner=" + rightBotCorner)
                .toString();
    }
}
