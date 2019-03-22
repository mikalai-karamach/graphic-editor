package by.bsuir.karamach.app;

import by.bsuir.karamach.model.figure.Printable;
import by.bsuir.karamach.model.store.FigureArrayList;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {

    private FigureArrayList arrayList = new FigureArrayList();


    private void doDrawing(Graphics g) {

        var g2d = (Graphics2D) g;


        g2d.setColor(Color.RED);

        for (Printable printable : arrayList.getAll()) {
            printable.print(g2d);
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }


    public FigureArrayList getFigureList() {
        return arrayList;
    }
}
