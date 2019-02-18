package by.bsuir.karamach.model.figure.impl;

import by.bsuir.karamach.model.figure.AbstractFigure;
import by.bsuir.karamach.model.figure.basic.Point;

import java.util.Objects;
import java.util.StringJoiner;

public class Square extends AbstractFigure {
    private static final long serialVersionUID = -5682594565139550253L;

    private Point leftTopPoint;
    private Point rightBotPoint;

    public Square() {
    }

    public Square(Point leftTopPoint, Point rightBotPoint) {
        this.leftTopPoint = leftTopPoint;
        this.rightBotPoint = rightBotPoint;
    }

    public Point getLeftTopPoint() {
        return leftTopPoint;
    }

    public void setLeftTopPoint(Point leftTopPoint) {
        this.leftTopPoint = leftTopPoint;
    }

    public Point getRightBotPoint() {
        return rightBotPoint;
    }

    public void setRightBotPoint(Point rightBotPoint) {
        this.rightBotPoint = rightBotPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Square square = (Square) o;
        return Objects.equals(getLeftTopPoint(), square.getLeftTopPoint()) &&
                Objects.equals(getRightBotPoint(), square.getRightBotPoint());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLeftTopPoint(), getRightBotPoint());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Square.class.getSimpleName() + "[", "]")
                .add("leftTopPoint=" + leftTopPoint)
                .add("rightBotPoint=" + rightBotPoint)
                .toString();
    }
}
