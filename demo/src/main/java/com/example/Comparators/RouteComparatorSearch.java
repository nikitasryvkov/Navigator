package com.example.Comparators;

import java.util.Comparator;

import com.example.Route;
import com.example.DataStructure.MyList;
import com.example.DataStructure.MyMap;

public class RouteComparatorSearch implements Comparator<Route> {
    private MyMap<String, Route> map;
    private String startPoint;
    private String endPoint;

    public RouteComparatorSearch(String startPoint, String endPoint, MyMap<String, Route> map) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.map = map;
    }

    @Override
    public int compare(Route r1, Route r2) {
        if (r1.isFavorite() != r2.isFavorite()) {
            return r1.isFavorite() ? -1 : 1;
        }
        int pointSpacingFirst = countDistance(r1.getLocationPoints());
        int pointSpacingSec = countDistance(r2.getLocationPoints());
        if (pointSpacingFirst != pointSpacingSec) {
            return pointSpacingFirst - pointSpacingSec;
        }
        if (r1.getPopularity() != r2.getPopularity()) {
            return r2.getPopularity() - r1.getPopularity();
        }
        return map.getOrder(r2.getId()) - map.getOrder(r1.getId());
    }

    private int countDistance(MyList<String> list) {
        int indexOfStart = list.indexOf(startPoint);
        int indexOfEnd = list.indexOf(endPoint);
        return indexOfEnd - indexOfStart - 1;
    }
}
