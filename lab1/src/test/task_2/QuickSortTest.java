package test.task_2;

import main.task_1.Cosine;
import main.task_2.ComparableUnit;
import main.task_2.QuickSort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class QuickSortTest {
    ArrayList<ComparableUnit> array;
    ArrayList<ComparableUnit> sorted_array;

    @BeforeEach
    public void initArrays() {
        array = new ArrayList<>();
        sorted_array = new ArrayList<>();
    }

    @Test
    @DisplayName("Sorting array")
    void sortArray() {
        for (int i = 0; i < 10; i++) {
            array.add(new ComparableUnit(i));
        }

        sorted_array.addAll(array);
        Collections.reverse(sorted_array);

        new QuickSort().accept(sorted_array);

        assertArrayEquals(array.toArray(), sorted_array.toArray());
    }

//    @Test
//    @DisplayName("Sorting already sorted array")
//    void sortSorted() {
//        for (int i = 0; i < 10; i++) {
//            array.add(new ComparableUnit(i));
//            sorted_array.add(new ComparableUnit(i));
//        }
//        new QuickSort().accept(sorted_array);
//        assertArrayEquals(array.toArray(), sorted_array.toArray());
//    }

    @Test
    @DisplayName("Sorting empty sorted array")
    void sortEmpty() {
        new QuickSort().accept(sorted_array);
        assertArrayEquals(array.toArray(), sorted_array.toArray());
    }

    @Test
    @DisplayName("Sorting reversed array with duplicates")
    void sort() {
        for (int i = 10; i > 0; i--) {
            sorted_array.add(new ComparableUnit("CU[" + i / 2 + "]", i / 2));
        }
        new QuickSort().accept(sorted_array);


        array.add(0, new ComparableUnit("CU[0]", 0));
        array.add(1, new ComparableUnit("CU[1]", 1));
        array.add(2, new ComparableUnit("CU[1]", 1));
        array.add(3, new ComparableUnit("CU[2]", 2));
        array.add(4, new ComparableUnit("CU[2]", 2));
        array.add(5, new ComparableUnit("CU[3]", 3));
        array.add(6, new ComparableUnit("CU[3]", 3));
        array.add(7, new ComparableUnit("CU[4]", 4));
        array.add(8, new ComparableUnit("CU[4]", 4));
        array.add(9, new ComparableUnit("CU[5]", 5));

        assertArrayEquals(array.toArray(), sorted_array.toArray());
    }

    @Test
    @DisplayName("Sorting array of duplicates")
    void sortDuplicates() {

        for (int i = 0; i < 10; i++) {
            sorted_array.add(new ComparableUnit("CU[" + 407 + "]", 47));
        }

        array.add(0, new ComparableUnit("CU[407]", 47));
        array.add(1, new ComparableUnit("CU[407]", 47));
        array.add(2, new ComparableUnit("CU[407]", 47));
        array.add(3, new ComparableUnit("CU[407]", 47));
        array.add(4, new ComparableUnit("CU[407]", 47));
        array.add(5, new ComparableUnit("CU[407]", 47));
        array.add(6, new ComparableUnit("CU[407]", 47));
        array.add(7, new ComparableUnit("CU[407]", 47));
        array.add(8, new ComparableUnit("CU[407]", 47));
        array.add(9, new ComparableUnit("CU[407]", 47));

        assertArrayEquals(array.toArray(), sorted_array.toArray());
    }
}