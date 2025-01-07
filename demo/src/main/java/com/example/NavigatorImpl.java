package com.example;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addRoute'");
    }

    @Override
    public Route removeRoute(String routeId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeRoute'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchRoutes'");
    }

    @Override
    public Iterable<Route> getFavoriteRoutes(String destinationPoint) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFavoriteRoutes'");
    }

    @Override
    public Iterable<Route> getTop3Routes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTop3Routes'");
    }

    @Override
    public void setFavorite(String routeId, boolean isFavorite) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFavorite'");
    }

}
