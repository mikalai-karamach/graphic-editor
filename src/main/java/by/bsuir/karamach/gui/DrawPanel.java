package by.bsuir.karamach.gui;

import by.bsuir.karamach.model.figure.Printable;
import by.bsuir.karamach.model.store.FigureList;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {

    private FigureList arrayList = new FigureList();

    public void setArrayList(FigureList arrayList) {
        this.arrayList = arrayList;
    }

    private void doDrawing(Graphics g) {

        var g2d = (Graphics2D) g;


        g2d.setColor(Color.RED);

        for (Printable printable : arrayList.getFigures()) {
            printable.print(g2d);
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }


    public FigureList getFigureList() {
        return arrayList;
    }
}
