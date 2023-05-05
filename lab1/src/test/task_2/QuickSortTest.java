package test.task_2;

import main.task_2.ComparableUnit;
import main.task_2.QuickSort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class QuickSortTest {

    @Test
    @DisplayName("Sorting already sorted array")
    void sortSorted() {
        ArrayList<ComparableUnit> array = new ArrayList<>();
        ArrayList<ComparableUnit> sorted_array = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            array.add(new ComparableUnit("CU[" + i + "]", i));
            sorted_array.add(new ComparableUnit("CU[" + i + "]", i));
        }
        new QuickSort().accept(sorted_array);
        assertArrayEquals(array.toArray(), sorted_array.toArray());
    }

    @Test
    @DisplayName("Sorting empty sorted array")
    void sortEmpty() {
        ArrayList<ComparableUnit> array = new ArrayList<>();
        ArrayList<ComparableUnit> sorted_array = new ArrayList<>();
        new QuickSort().accept(sorted_array);
        assertArrayEquals(array.toArray(), sorted_array.toArray());
    }

    @Test
    @DisplayName("Sorting reversed array with duplicates")
    void sort() {
        ArrayList<ComparableUnit> sorted_array = new ArrayList<>();
        for (int i = 10; i > 0; i--) {
            sorted_array.add(new ComparableUnit("CU[" + i / 2 + "]", i / 2));
        }
        new QuickSort().accept(sorted_array);

        ArrayList<ComparableUnit> array = new ArrayList<>();
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
        ArrayList<ComparableUnit> sorted_array = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            sorted_array.add(new ComparableUnit("CU[" + 407 + "]", 47));
        }
        ArrayList<ComparableUnit> array = new ArrayList<>();
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