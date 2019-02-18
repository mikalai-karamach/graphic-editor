package by.bsuir.karamach.model.impl;

import by.bsuir.karamach.model.AbstractFigure;
import by.bsuir.karamach.model.basic.Point;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class BrokenLine extends AbstractFigure {
    private static final long serialVersionUID = 1090821929829893663L;

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
}
