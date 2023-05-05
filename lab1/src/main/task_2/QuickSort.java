package main.task_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;

public class QuickSort implements Consumer<ArrayList<ComparableUnit>> {

    @Override
    public void accept(ArrayList<ComparableUnit> array) {
        quickSort(array, 0, array.size()-1);
    }

    /* Comparable units with the priority less than pivots go to the left side each iteration */
    private void quickSort(ArrayList<ComparableUnit> array, int a, int b) {
        if (a >= b) {
            return;
        }
        ComparableUnit pivot = array.get(b);
        int ptr = a - 1;

        for (int i = a; i < b; i++) {
            if (array.get(i).compareTo(pivot) < 0) {
                ptr++;
                Collections.swap(array, i, ptr);
            }
        }
        Collections.swap(array, b, ptr + 1);

        quickSort(array, a, ptr);
        quickSort(array, ptr + 2, b);
    }
}
