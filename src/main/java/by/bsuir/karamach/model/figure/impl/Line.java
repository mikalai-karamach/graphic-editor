package by.bsuir.karamach.model.figure.impl;

import by.bsuir.karamach.model.figure.Printable;
import by.bsuir.karamach.model.figure.basic.Point;

import java.awt.*;
import java.util.Objects;

public class Line implements Printable {
    private static final long serialVersionUID = -8655047447318746914L;

    private Point start;
    private Point end;

    public Line() {
    }

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Line line = (Line) o;
        return Objects.equals(getStart(), line.getStart()) &&
                Objects.equals(getEnd(), line.getEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStart(), getEnd());
    }

    @Override
    public void print(Graphics2D graphics2D) {
        graphics2D.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
    }
}
