package com.example.Comparators;

import java.util.Comparator;

import com.example.Route;

public class Top3Comparator implements Comparator<Route> {

    @Override
    public int compare(Route r1, Route r2) {
        // Сравнение популярности (убывание)
        int popularityRoute = Integer.compare(r2.getPopularity(), r1.getPopularity());

        // Сравнение расстояний (возрастание)
        if (popularityRoute == 0) {
            int distanceRoute = Double.compare(r1.getDistance(), r2.getDistance());

            // Сравнение точек местоположения (возрастание)
            if (distanceRoute == 0) {
                return Integer.compare(r1.getLocationPoints().size(), r2.getLocationPoints().size());
            }

            return distanceRoute;
        }

        return popularityRoute;
    }

}
