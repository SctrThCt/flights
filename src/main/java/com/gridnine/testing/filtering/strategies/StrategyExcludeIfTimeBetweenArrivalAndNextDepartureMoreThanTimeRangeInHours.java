package com.gridnine.testing.filtering.strategies;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StrategyExcludeIfTimeBetweenArrivalAndNextDepartureMoreThanTimeRangeInHours implements FilteringStrategy {

    private final int maxRange;

    public StrategyExcludeIfTimeBetweenArrivalAndNextDepartureMoreThanTimeRangeInHours(int maxRange){
        this.maxRange = maxRange;
    }
    @Override
    public List<Flight> filter(List<Flight> flights) {
        List<Flight> newFlightList = new ArrayList<>();
        for (Flight f : flights) {
            List<Segment> segments = f.getSegments(); //для каждого полёта получаем список сегментов
            if (segments.size() == 1) {
                newFlightList.add(f);
            } else {//если один элемент то нам точно подходит
                int groundTime = 0; //счётчик часов на земле
                for (int i = 1; i < segments.size(); i++) {
                    LocalDateTime start = segments.get(i - 1).getArrivalDate(); //чтобы не городить кучу текста в Duration
                    LocalDateTime end = segments.get(i).getDepartureDate();
                    groundTime += Duration.between(start, end).toHoursPart();
                    if (groundTime > maxRange) { //проверяем счётчик если он больше нужного то сразу заканчиваем цикл и добавляем полет, чтобы не тратить время
                        break;
                    }
                    newFlightList.add(f);
                }
            }
        }
        return newFlightList;
    }
}
