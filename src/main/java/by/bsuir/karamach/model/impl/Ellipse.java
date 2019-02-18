package by.bsuir.karamach.model.impl;

import by.bsuir.karamach.model.AbstractFigure;
import by.bsuir.karamach.model.basic.Point;

import java.util.Objects;
import java.util.StringJoiner;

public class Ellipse extends AbstractFigure {
    private static final long serialVersionUID = -5989983302590673296L;

    private Point leftTopCorner;
    private Point rightBotCorner;

    public Ellipse() {
    }

    public Ellipse(Point leftTopCorner, Point rightBotCorner) {
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

        Ellipse ellipse = (Ellipse) o;
        return Objects.equals(getLeftTopCorner(), ellipse.getLeftTopCorner()) &&
                Objects.equals(getRightBotCorner(), ellipse.getRightBotCorner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLeftTopCorner(), getRightBotCorner());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Ellipse.class.getSimpleName() + "[", "]")
                .add("leftTopCorner=" + leftTopCorner)
                .add("rightBotCorner=" + rightBotCorner)
                .toString();
    }
}
