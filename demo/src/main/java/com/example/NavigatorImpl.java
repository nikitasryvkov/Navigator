package com.example;

import java.util.stream.Collectors;

import com.example.Comparators.RouteComparator;
import com.example.Comparators.RouteComparatorSearch;
import com.example.Comparators.Top3Comparator;
import com.example.DataStructure.MyMap;

public class NavigatorImpl implements Navigator {
    private MyMap<String, Route> routes;
    private MyMap<String, Route> favoriteRoutes;

    public NavigatorImpl() {
        this.routes = new MyMap<>();
        this.favoriteRoutes = new MyMap<>();
    }

    @Override
    public void addRoute(Route route) {
        for (Route requiredRote : routes.values()) {
            if (checkRepeat(requiredRote, route)) {
                return;
            }
        }

        routes.put(route.getId(), route);
        if (route.isFavorite()) {
            favoriteRoutes.put(route.getId(), route);
        }
    }

    @Override
    public Route removeRoute(String routeId) {
        Route removeRoute = routes.remove(routeId);

        if (removeRoute.isFavorite()) {
            favoriteRoutes.remove(routeId);
        }

        return removeRoute;
    }

    @Override
    public boolean contains(String route) {
        return routes.contains(route);
    }

    @Override
    public int size() {
        return routes.size();
    }

    @Override
    public Route getRoute(String routeId) {
        return routes.get(routeId);
    }

    @Override
    public void chooseRoute(String routeId) {
        Route route = routes.get(routeId);

        if (route != null) {
            route.addPopularity();
        }
    }

    @Override
    public Iterable<Route> searchRoutes(String startPoint, String endPoint) {
        return routes.values()
                .stream()
                .filter(route -> route.hasLogicalOrder(startPoint, endPoint))
                .sorted(new RouteComparatorSearch(startPoint, endPoint))
                .collect(Collectors.toList());

    }

    @Override
    public Iterable<Route> getFavoriteRoutes(String destinationPoint) {
        return routes.values()
                .stream()
                .filter(route -> route.isFavorite() && route.getLocationPoints().indexOf(destinationPoint) > 0)
                .sorted(new RouteComparator(destinationPoint))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Route> getTop3Routes() {
        return routes.values()
                .stream()
                .sorted(new Top3Comparator())
                .limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public void setFavorite(String routeId, boolean isFavorite) {

    }

    private boolean checkRepeat(Route requiredRote, Route route) {
        return Double.compare(requiredRote.getDistance(), route.getDistance()) == 0
                && requiredRote.getLocationPoints().equals(route.getLocationPoints());
    }
}
