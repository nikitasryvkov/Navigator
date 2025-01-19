package com.example;

import com.example.Comparators.RouteComparator;
import com.example.Comparators.RouteComparatorSearch;
import com.example.Comparators.Top3Comparator;
import com.example.DataStructure.MyArrayList;
import com.example.DataStructure.MyHashTable;
import com.example.DataStructure.MyList;
import com.example.DataStructure.MyMap;

public class NavigatorImpl implements Navigator {
    MyMap<String, Route> map = new MyHashTable<>();

    @Override
    public void addRoute(Route route) {
        for (Route requiredRote : map.values()) {
            if (checkRepeat(requiredRote, route)) {
                System.out.println("Такой маршрут уже есть!");

                return;
            }
        }

        map.put(route.getId(), route);
    }

    @Override
    public void removeRoute(String routeId) {
        map.remove(routeId);
    }

    @Override
    public boolean contains(Route route) {
        for (Route r : map.values()) {
            if (r.equals(route))
                return true;
        }
        return false;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Route getRoute(String routeId) {
        return map.get(routeId);
    }

    @Override
    public void chooseRoute(String routeId) {
        Route route = map.get(routeId);
        if (route != null) {
            route.addPopularity();
            ;
        }
    }

    @Override
    public Iterable<Route> searchRoutes(String startPoint, String endPoint) {
        MyList<Route> result = new MyArrayList<>();

        for (Route route : map.values()) {
            if (route.isLogical(route, startPoint, endPoint)) {
                result.add(route);
            }
        }

        result.sort(new RouteComparatorSearch(startPoint, endPoint, map));
        return result;
    }

    @Override
    public Iterable<Route> getFavoriteRoutes(String destinationPoint) {
        MyList<Route> result = new MyArrayList<>();

        for (Route route : map.values()) {
            if (route.isFavorite()) {
                for (String point : route.getLocationPoints()) {
                    if (point.equals(destinationPoint) &&
                            !route.getLocationPoints().get(0).equals(point)) {
                        result.add(route);
                        break;
                    }
                }
            }
        }

        result.sort(new RouteComparator(map));
        return result;
    }

    @Override
    public Iterable<Route> getTop3Routes() {
        MyList<Route> result = new MyArrayList<>();

        for (Route route : map.values()) {
            result.add(route);
        }

        result.sort(new Top3Comparator(map));

        return result.subList(0, Math.min(5, result.size()));
    }

    @Override
    public void setFavorite(String routeId, boolean isFavorite) {
        Route route = map.get(routeId);
        if (route != null) {
            System.out.println("Изменение статуса маршрута с ID: " + routeId + " на " + isFavorite);
            route.setFavorite(isFavorite);
        } else {
            System.out.println("Маршрут не найден!");
        }
    }

    private boolean checkRepeat(Route requiredRote, Route route) {
        return requiredRote.getId().equals(route.getId()) &&
                Double.compare(requiredRote.getDistance(), route.getDistance()) == 0
                && requiredRote.getLocationPoints().get(0).equals(route.getLocationPoints().get(0))
                && requiredRote.getLocationPoints().get(requiredRote.getLocationPoints().size() - 1)
                        .equals(route.getLocationPoints().get(route.getLocationPoints().size() - 1));
    }
}
