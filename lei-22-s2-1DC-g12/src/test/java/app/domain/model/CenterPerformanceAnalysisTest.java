package app.domain.model;

import app.domain.shared.Algorithms;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CenterPerformanceAnalysisTest {

    @Test
    void setListsOfArrivalsAndDepartures() {
    }

    @Test
    void differenceBetweenArrivalsAndDepartures() {
    }

    @Test
    void countArrivalsOrDeparturesInTimeInterval() {
    }

    @Test
    void processAllDataBeforeShowing() {
        CenterPerformanceAnalysis cpa = new CenterPerformanceAnalysis();
        int[] example = new int[]{29, -32, -9, -25, 44, 12, -61, 51, -9, 44, 74, 4, -100};
        cpa.processAllDataBeforeShowing(example,Algorithms.bruteForceAlgorithm(example),30);
    }

    @Test
    void showData() {
    }
}