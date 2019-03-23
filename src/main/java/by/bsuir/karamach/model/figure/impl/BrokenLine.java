package by.bsuir.karamach.model.figure.impl;

import by.bsuir.karamach.model.figure.Printable;
import by.bsuir.karamach.model.figure.basic.Point;

import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class BrokenLine implements Printable {
    private static final long serialVersionUID = -7515622801408129698L;

    private List<Point> points;

    public BrokenLine() {
    }

    public BrokenLine(List<Point> points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BrokenLine that = (BrokenLine) o;
        return Objects.equals(getPoints(), that.getPoints());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPoints());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BrokenLine.class.getSimpleName() + "[", "]")
                .add("points=" + points)
                .toString();
    }

    @Override
    public void print(Graphics2D graphics2D) {
        for (int i = 0; i < points.size() - 1; i++) {
            Point pointFrom = points.get(i);
            Point pointTo = points.get(i + 1);

            graphics2D.drawLine(pointFrom.getX(), pointFrom.getY(), pointTo.getX(), pointTo.getY());

        }

    }

}
