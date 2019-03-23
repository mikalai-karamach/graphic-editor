package by.bsuir.karamach.model.store;

public interface FigureRepository<T> {
    void save(T figure);

    void remove(T figure);

    void clear();

}
