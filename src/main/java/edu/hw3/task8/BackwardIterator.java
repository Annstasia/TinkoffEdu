package edu.hw3.task8;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class BackwardIterator<T> implements Iterator<T> {

    ListIterator<T> listIterator;

    public BackwardIterator(List<T> list) {
        listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            listIterator.next();
        }
    }

    @Override
    public boolean hasNext() {
        return listIterator.hasPrevious();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            return null;
        }
        return listIterator.previous();
    }

    @Override
    public void remove() {
        listIterator.remove();
    }
}
