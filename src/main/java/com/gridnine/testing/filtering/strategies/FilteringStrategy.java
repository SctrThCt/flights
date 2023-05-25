package com.gridnine.testing.filtering.strategies;

import com.gridnine.testing.Flight;

import java.util.List;

public interface FilteringStrategy {
    List<Flight> filter (List<Flight> flights);
}
