package com.example;

import java.util.Objects;

import com.example.DataStructure.MyList;

public class Route {
    private String id;
    private double distance;
    private int popularity;
    private boolean isFavorite;
    private MyList<String> locationPoints;

    public Route(Double distance, Integer popularity, Boolean isFavorite, MyList<String> locationPoints) {
        this.id = distance + locationPoints.get(0) + locationPoints.get(locationPoints.size() - 1);
        this.distance = distance;
        this.popularity = popularity;
        this.isFavorite = isFavorite;
        this.locationPoints = locationPoints;
    }

    public String getId() {
        return id;
    }

    public double getDistance() {
        return distance;
    }

    public int getPopularity() {
        return popularity;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public MyList<String> getLocationPoints() {
        return locationPoints;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public void setFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public void setLocationPoints(MyList<String> locationPoints) {
        this.locationPoints = locationPoints;
    }

    public void addPopularity() {
        popularity++;
    }

    public boolean isLogical(Route route, String startPoint, String endPoint) {
        MyList<String> locationPoints = route.getLocationPoints();

        int indexOfStart = locationPoints.indexOf(startPoint);
        int indexOfEnd = locationPoints.indexOf(endPoint);

        return indexOfStart >= 0 && indexOfEnd >= 0 && indexOfStart < indexOfEnd;
    }

    public boolean hasLogicalOrder(String startPoint, String endPoint) {
        if (locationPoints == null || locationPoints.size() < 2) {
            return false;
        }

        int startIndex = locationPoints.indexOf(startPoint);
        int endIndex = locationPoints.indexOf(endPoint);

        return startIndex != -1 && endIndex != -1 && startIndex < endIndex;
    }

    public int getLogicalOrderDistance(String startsPoint, String endPoint) {
        if (locationPoints == null || locationPoints.size() < 2) {
            return 0;
        }

        int indexFirstPoint = locationPoints.indexOf(startsPoint);
        int indexSecondPoint = locationPoints.indexOf(endPoint);

        return indexSecondPoint - indexFirstPoint;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Route route = (Route) obj;
        return Double.compare(route.distance, distance) == 0 &&
                popularity == route.popularity &&
                isFavorite == route.isFavorite &&
                Objects.equals(id, route.id) &&
                Objects.equals(locationPoints, route.locationPoints);
    }

    @Override
    public int hashCode() {
        int result = 1;
        int multiplier = 2025;

        result = multiplier * result + id.hashCode();
        result = multiplier * result + Double.hashCode(distance);
        result = multiplier * result + Integer.hashCode(popularity);
        result = multiplier * result + Boolean.hashCode(isFavorite);
        result = multiplier * result + Objects.hash(locationPoints);
        return result;
    }

    @Override
    public String toString() {
        return "Route{" +
                "Id='" + id + '\'' +
                ", Distance=" + distance +
                ", Popularity=" + popularity +
                ", IsFavorite=" + isFavorite +
                ", LocationPoints=" + locationPoints.toString() +
                '}';
    }
}
