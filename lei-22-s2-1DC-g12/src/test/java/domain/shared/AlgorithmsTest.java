package domain.shared;

import app.domain.shared.Algorithms;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmsTest {

    @Test
    void bubbleSortDates() {
    }

    @Test
    void quickSort() {
    }

    @Test
    void bruteForceAlgorithm() {
        Algorithms a = new Algorithms();
        int[] example = new int[]{29, -32, -9, -25, 44, 12, -61, 51, -9, 44, 74, 4, -100};
        a.bruteForceAlgorithm(example);


    }

    @Test
    void max() {
        int[] example = new int[]{29, -32, -9, -25, 44, 12, -61, 51, -9, 44, 74, 4, -100};
        int[] result = Algorithms.Max(example);
        System.out.println(result[0]);
        System.out.println(result[1]);
        //System.out.println(Arrays.toString(result)); // should print [51, -9, 44, 74, 4]


    }

    @Test
    void validateVaccinationStatus() {
    }
}