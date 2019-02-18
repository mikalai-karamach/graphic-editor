package by.bsuir.karamach.model.store;

import by.bsuir.karamach.model.figure.AbstractFigure;

import java.util.ArrayList;
import java.util.List;

public class FigureArrayList implements FigureRepository<AbstractFigure> {

    private List<AbstractFigure> figures = new ArrayList<>();

    public FigureArrayList() {
    }

    @Override
    public void save(AbstractFigure figure) {
        figures.add(figure);
    }

    @Override
    public void remove(AbstractFigure figure) {
        figures.remove(figure);
    }

    @Override
    public void clear() {
        figures.clear();
    }

    @Override
    public List<AbstractFigure> getAll() {
        return figures;
    }
}
