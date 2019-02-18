package by.bsuir.karamach.model.store;

import java.util.List;

public interface FigureRepository<T> {
    void save(T figure);

    void remove(T figure);

    void clear();

    List<T> getAll();

}
