package com.gridnine.testing;

import com.gridnine.testing.filtering.FlightFilter;
import com.gridnine.testing.filtering.strategies.StrategyExcludeIfDepartureBeforeArrival;
import com.gridnine.testing.filtering.strategies.StrategyExcludeIfDepartureBeforeNow;
import com.gridnine.testing.filtering.strategies.StrategyExcludeIfTimeBetweenArrivalAndNextDepartureMoreThanTimeRangeInHours;

public class Main {
    public static void main(String[] args) {
        System.out.println("Эталон");
        FlightBuilder.createFlights().forEach(System.out::println);
        System.out.println();
        System.out.println("Убрать те, где прилёт раньше вылета");
        FlightFilter flightFilter = new FlightFilter(new StrategyExcludeIfDepartureBeforeArrival());
        flightFilter.applyFiltersToFlights(FlightBuilder.createFlights()).forEach(System.out::println);
        System.out.println();
        flightFilter.clearFilter();
        System.out.println("Убрать те, где вылет раньше текущей даты");
        flightFilter.addFilters(new StrategyExcludeIfDepartureBeforeNow());
        flightFilter.applyFiltersToFlights(FlightBuilder.createFlights()).forEach(System.out::println);
        System.out.println();
        flightFilter.clearFilter();
        System.out.println("Убрать те, где суммарное время ожидания между перелётами больше двух часов");
        flightFilter.addFilters(new StrategyExcludeIfTimeBetweenArrivalAndNextDepartureMoreThanTimeRangeInHours(2));
        flightFilter.applyFiltersToFlights(FlightBuilder.createFlights()).forEach(System.out::println);
        flightFilter.clearFilter();
        System.out.println();
        System.out.println("Все фильтры");
        flightFilter.addFilters(new StrategyExcludeIfDepartureBeforeArrival(),new StrategyExcludeIfDepartureBeforeNow(),new StrategyExcludeIfTimeBetweenArrivalAndNextDepartureMoreThanTimeRangeInHours(2));
        flightFilter.applyFiltersToFlights(FlightBuilder.createFlights()).forEach(System.out::println);
    }
}
