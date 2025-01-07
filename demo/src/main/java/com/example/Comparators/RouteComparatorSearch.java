package com.example.Comparators;

import java.util.Comparator;

import com.example.Route;

public class RouteComparatorSearch implements Comparator<Route> {
    private String startPoint;
    private String endPoint;

    public RouteComparatorSearch(String startPoint, String endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    @Override
    public int compare(Route r1, Route r2) {
        boolean isFavorite1 = r1.isFavorite();
        boolean isFavorite2 = r2.isFavorite();

        if (isFavorite1 && !isFavorite2) {
            return -1;
        } else if (!isFavorite1 && isFavorite2) {
            return 1;
        } else {
            boolean isLogicalOrder1 = r1.hasLogicalOrder(startPoint, endPoint);
            boolean isLogicalOrder2 = r2.hasLogicalOrder(startPoint, endPoint);

            if (isLogicalOrder1 && isLogicalOrder2) {
                int logicalOrderDistance1 = r1.getLogicalOrderDistance();
                int logicalOrderDistance2 = r2.getLogicalOrderDistance();

                if (logicalOrderDistance1 != logicalOrderDistance2) {
                    return Integer.compare(logicalOrderDistance1, logicalOrderDistance2);
                } else {
                    int popularity1 = r1.getPopularity();
                    int popularity2 = r2.getPopularity();

                    return Integer.compare(popularity2, popularity1);
                }
            } else if (isLogicalOrder1) {
                return -1;
            } else if (isLogicalOrder2) {
                return 1;
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
}
