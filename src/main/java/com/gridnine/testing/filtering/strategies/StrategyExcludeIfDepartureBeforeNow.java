package com.gridnine.testing.filtering.strategies;

import com.gridnine.testing.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class StrategyExcludeIfDepartureBeforeNow implements FilteringStrategy {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream().
                filter(e -> e.getSegments().stream()
                        .anyMatch(f -> f.getDepartureDate().isAfter(LocalDateTime.now())))
                .collect(Collectors.toList());
    }

}
