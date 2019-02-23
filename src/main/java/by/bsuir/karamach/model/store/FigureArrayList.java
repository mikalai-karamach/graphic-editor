package by.bsuir.karamach.model.store;

import by.bsuir.karamach.model.figure.Printable;

import java.util.ArrayList;
import java.util.List;

public class FigureArrayList implements FigureRepository<Printable> {

    private List<Printable> figures = new ArrayList<>();

    public FigureArrayList() {
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

    @Override
    public List<Printable> getAll() {
        return figures;
    }
}
