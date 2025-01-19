package com.example.Comparators;

import java.util.Comparator;

import com.example.Route;
import com.example.DataStructure.MyMap;

public class RouteComparator implements Comparator<Route> {
    private MyMap<String, Route> map;

    public RouteComparator(MyMap<String, Route> map) {
        this.map = map;
    }

    @Override
    public int compare(Route r1, Route r2) {
        double distance1 = r1.getDistance();
        double distance2 = r2.getDistance();

        if (distance1 != distance2) {
            return Double.compare(distance1, distance2);
        }
        if (distance1 == distance2) {
            int popularity1 = r1.getPopularity();
            int popularity2 = r2.getPopularity();

            return Integer.compare(popularity2, popularity1);
        }
        return map.getOrder(r2.getId()) - map.getOrder(r1.getId());
    }
}
