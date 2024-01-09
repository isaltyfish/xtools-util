package net.verytools.util.model;

import java.util.List;

public class DiffR<T, E> {
    private final List<T> newList;
    private final List<T> modList;
    private final List<E> delList;

    public DiffR(List<T> newList, List<T> modList, List<E> delList) {
        this.newList = newList;
        this.modList = modList;
        this.delList = delList;
    }

    public List<T> getN() {
        return newList;
    }

    public List<T> getM() {
        return modList;
    }

    public List<E> getD() {
        return delList;
    }
}
