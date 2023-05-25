package com.gridnine.testing.filtering.strategies;

import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.filtering.FlightFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilteringTest {

    private List<Flight> dba;
    private List<Flight> dbn;
    private List<Flight> tbaandmtrih;
    private List<Flight> af;
    private List<Flight> testList;

    @BeforeEach
    void before()
    {
        testList = FlightBuilder.createFlights();
        dba = List.of(testList.get(0), testList.get(1), testList.get(2), testList.get(4), testList.get(5));
        dbn = List.of(testList.get(0), testList.get(1), testList.get(3), testList.get(4), testList.get(5));
        tbaandmtrih = List.of(testList.get(0), testList.get(1), testList.get(2), testList.get(3), testList.get(5));
        af = List.of(testList.get(0),testList.get(1),testList.get(5));
    }

    @Test
    void filterIfDepartureBeforeArrivalTest() {
        FlightFilter filter = new FlightFilter(new StrategyExcludeIfDepartureBeforeArrival());
        assertEquals(filter.applyFiltersToFlights(testList), dba);
    }

    @Test
    void filterIfDepartureBeforeNowTest() {
        FlightFilter filter = new FlightFilter(new StrategyExcludeIfDepartureBeforeNow());
        assertEquals(filter.applyFiltersToFlights(testList), dbn);
    }
    @Test
    void filterIfTimeBetweenArrivalAndNextDepartureMoreThanTimeRangeInHoursTest() {
        FlightFilter filter = new FlightFilter(new StrategyExcludeIfTimeBetweenArrivalAndNextDepartureMoreThanTimeRangeInHours(2));
        assertEquals(filter.applyFiltersToFlights(testList), tbaandmtrih);
    }

    @Test
    void allFilterTest() {
        FlightFilter filter = new FlightFilter(
                new StrategyExcludeIfDepartureBeforeArrival(),
                new StrategyExcludeIfDepartureBeforeNow(),
                new StrategyExcludeIfTimeBetweenArrivalAndNextDepartureMoreThanTimeRangeInHours(2));
        assertEquals(filter.applyFiltersToFlights(testList),af);
    }

    @Test
    void noFilterTest() {
        FlightFilter filter = new FlightFilter();
        assertEquals(filter.applyFiltersToFlights(testList),testList);
     }
}
