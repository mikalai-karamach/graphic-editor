package by.bsuir.karamach.model.store;

import by.bsuir.karamach.model.figure.Printable;

import java.util.ArrayList;
import java.util.List;

public class FigureList implements FigureRepository<Printable> {

    private List<Printable> figures = new ArrayList<>();

    public FigureList() {
    }

    @Override
    public void save(Printable figure) {
        figures.add(figure);
    }

    @Override
    public void remove(Printable figure) {
        figures.remove(figure);
    }

    @Override
    public void clear() {
        figures.clear();
    }

    public List<Printable> getFigures() {
        return figures;
    }

    public void setFigures(List<Printable> figures) {
        this.figures = figures;
    }

}
