package by.bsuir.karamach.model.figure.impl;

import by.bsuir.karamach.gui.Frame;
import by.bsuir.karamach.model.figure.Plugin;
import by.bsuir.karamach.model.figure.Printable;
import by.bsuir.karamach.model.figure.basic.Point;
import by.bsuir.karamach.util.ParserException;
import by.bsuir.karamach.util.UICoordinatesParser;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class Line extends Plugin implements Printable {
    private static final long serialVersionUID = -1380335813588444298L;

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

    @Override
    public void renderOnFrame(Frame frameToPaint, JFrame frameToRender) {
        JButton line = new JButton(getClass().getSimpleName());
        line.setBounds(10, 230, 100, 50);
        line.setLayout(null);

        line.addActionListener(event -> {
            int amountOfCoordinatesExpected = 4;


            String message = JOptionPane.showInputDialog(
                    "Enter coordinates(" + amountOfCoordinatesExpected + ")");

            if ((message != null) && (!message.isEmpty())) {

                List<Integer> coordinates;
                try {
                    coordinates = UICoordinatesParser.parseToCoordinates(message, amountOfCoordinatesExpected);

                    Point point1 = new Point(coordinates.get(0), coordinates.get(1));
                    Point point2 = new Point(coordinates.get(2), coordinates.get(3));

                    frameToPaint.getDrawPanel().getFigureList().save(new Line(point1, point2));
                    frameToPaint.repaint();
                } catch (ParserException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

            }

        });
        frameToRender.getContentPane().add(line);
    }
}
