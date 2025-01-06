package com.example;

public interface Navigator {
    void addRoute(Route route);

    Route removeRoute(String routeId);

    boolean contains(String route);

    int size();

    Route getRoute(String routeId);

    void chooseRoute(String routeId);

    Iterable<Route> searchRoutes(String startPoint, String endPoint);

    Iterable<Route> getFavoriteRoutes(String destinationPoint);

    Iterable<Route> getTop3Routes();

    void setFavorite(String routeId, boolean isFavorite);
}
