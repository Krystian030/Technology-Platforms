package Jandy.Krystian.repository;

import java.util.*;

public abstract class MemoryRepository <E extends Comparable<E>> {
    private Set<E> set;

    public MemoryRepository(){
        set = new HashSet<>();
    }

    public MemoryRepository(boolean sort) {
        set = sort ? new TreeSet<>() : new HashSet<>();
    }

    public MemoryRepository(Comparator<E> comparator){
        set = new TreeSet<>(comparator);
    }

    public void add(E element) {
        set.add(element);
    }

    public void delete(E element) {
        set.remove(element);
    }

    public List<E> getAll() {
        return new ArrayList<>(set);
    }
}
