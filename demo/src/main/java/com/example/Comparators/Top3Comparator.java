package com.example.Comparators;

import java.util.Comparator;

import com.example.Route;
import com.example.DataStructure.MyMap;

public class Top3Comparator implements Comparator<Route> {
    private MyMap<String, Route> map;

    public Top3Comparator(MyMap<String, Route> map) {
        this.map = map;
    }

    @Override
    public int compare(Route r1, Route r2) {
        if (r1.getPopularity() != r2.getPopularity()) {
            return Integer.compare(r2.getPopularity(), r1.getPopularity());
        }
        if (r1.getDistance() != r2.getDistance()) {
            return Double.compare(r1.getDistance(), r2.getDistance());
        }
        if (r1.getLocationPoints().size() != r2.getLocationPoints().size()) {
            return Integer.compare(r1.getLocationPoints().size(), r2.getLocationPoints().size());
        }
        return map.getOrder(r2.getId()) - map.getOrder(r1.getId());
    }
}
