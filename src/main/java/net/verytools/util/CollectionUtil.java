package net.verytools.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionUtil {

    /**
     * Join two collections which have one to one relationship.
     *
     * @param main          the first collection
     * @param sub           the second collection to be joined with first collection
     * @param mainKeyMapper the mapper to the first collection to get the join value
     * @param subKeyMapper  the mapper to convert the second collection to a map
     * @param pairHandler   callback method to receive a pair of matched elements
     * @param <T>           the element type of the first collection
     * @param <E>           the element type of the second collection
     * @param <K>           the type of the join key
     */
    public static <T, E, K> void join(Collection<T> main,
                                      Collection<E> sub,
                                      Function<? super T, K> mainKeyMapper,
                                      Function<? super E, K> subKeyMapper,
                                      BiConsumer<? super T, ? super E> pairHandler) {
        Map<K, E> map = sub.stream().collect(Collectors.toMap(subKeyMapper, e -> e, (a, b) -> a));
        for (T t : main) {
            K k = mainKeyMapper.apply(t);
            E e = map.get(k);
            pairHandler.accept(t, e);
        }
    }

    /**
     * Join two collections which have one to many relationship.
     *
     * @param main          the first collection
     * @param sub           the second collection to be joined with first collection
     * @param mainKeyMapper the mapper to the first collection to get the join value
     * @param subKeyMapper  the mapper to convert the second collection to a map
     * @param pairHandler   callback method to receive a pair of matched elements. the second element of each pair will be a List or null.
     * @param <T>           the element type of the first collection
     * @param <E>           the element type of the second collection
     * @param <K>           the type of the join key
     */
    public static <T, E, K> void joinM(Collection<T> main,
                                       Collection<E> sub,
                                       Function<? super T, K> mainKeyMapper,
                                       Function<? super E, K> subKeyMapper,
                                       BiConsumer<? super T, List<? super E>> pairHandler) {
        Map<K, List<E>> map = sub.stream().collect(Collectors.groupingBy(subKeyMapper));
        for (T t : main) {
            K k = mainKeyMapper.apply(t);
            List<E> e = map.get(k);
            pairHandler.accept(t, e);
        }
    }

    public static <T, V> List<V> map(Collection<T> main, Function<? super T, V> mapper) {
        return main.stream().map(mapper).collect(Collectors.toList());
    }

    /**
     * Divide a list into N equal parts。
     *
     * @param main     the collection to be partitioned
     * @param size     partition size
     * @param consumer callback to receive partition part
     * @param <T>      the element type of main
     */
    public static <T> void partition(Collection<T> main, int size, Consumer<Collection<T>> consumer) {
        if (size <= 0) {
            throw new IllegalStateException("size should gt 0");
        }
        List<T> batch = new ArrayList<>(size);
        for (T t : main) {
            batch.add(t);
            if (batch.size() == size) {
                consumer.accept(batch);
                batch = new ArrayList<>(size);
            }
        }
        if (!batch.isEmpty()) {
            consumer.accept(batch);
        }
    }

    public static <T> List<Collection<T>> partition(Collection<T> main, int size) {
        List<Collection<T>> ret = new ArrayList<>();
        partition(main, size, ret::add);
        return ret;
    }

}
