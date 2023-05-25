package com.gridnine.testing.filtering;

import com.gridnine.testing.Flight;
import com.gridnine.testing.filtering.strategies.FilteringStrategy;

import java.util.ArrayList;
import java.util.List;

public class FlightFilter {

    private List<FilteringStrategy> filteringStrategies;
    public FlightFilter(FilteringStrategy... filteringStrategies) {
        this.filteringStrategies = List.of(filteringStrategies);
    }

    public void addFilters(FilteringStrategy... filteringStrategies) {
        this.filteringStrategies.addAll(List.of(filteringStrategies));
    }

    public void clearFilter() {
        this.filteringStrategies =new ArrayList<>();
    }

    public List<Flight> applyFiltersToFlights(List<Flight> flights)
    {
        List<Flight> out = List.copyOf(flights);
        for (FilteringStrategy f: filteringStrategies)
        {
            out = f.filter(out);
        }
        return out;
    }
}


