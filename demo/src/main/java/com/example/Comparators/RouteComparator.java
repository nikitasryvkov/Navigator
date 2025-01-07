package com.example.Comparators;

import java.util.Comparator;

import com.example.Route;

public class RouteComparator implements Comparator<Route> {
    private String destinationPoint;

    public RouteComparator(String destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    @Override
    public int compare(Route r1, Route r2) {
        int indexR1 = r1.getLocationPoints().indexOf(destinationPoint);
        int indexR2 = r2.getLocationPoints().indexOf(destinationPoint);

        if (indexR1 > 0 && indexR2 > 0) {
            int distance1L = r1.getLogicalOrderDistance();
            int distance2L = r2.getLogicalOrderDistance();

            if (distance1L != distance2L) {
                return Integer.compare(distance1L, distance2L);
            } else {
                int popularity1 = r1.getPopularity();
                int popularity2 = r2.getPopularity();

                return Integer.compare(popularity2, popularity1);
            }
        } else {
            double distance1 = r1.getDistance();
            double distance2 = r2.getDistance();

            if (distance1 != distance2) {
                return Double.compare(distance1, distance2);
            } else {
                int popularity1 = r1.getPopularity();
                int popularity2 = r2.getPopularity();

                return Integer.compare(popularity2, popularity1);
            }
        }
    }
}
