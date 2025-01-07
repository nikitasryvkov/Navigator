package com.example;

import java.util.List;
import java.util.Objects;

public class Route {
    private String id;
    private double distance;
    private int popularity;
    private boolean isFavorite;
    private List<String> locationPoints;

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

    public List<String> getLocationPoints() {
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

    public void setLocationPoints(List<String> locationPoints) {
        this.locationPoints = locationPoints;
    }

    public void addPopularity() {
        popularity++;
    }

    public boolean hasLogicalOrder(String startPoint, String endPoint) {
        if (locationPoints == null || locationPoints.size() < 2) {
            return false;
        }

        int startIndex = locationPoints.indexOf(startPoint);
        int endIndex = locationPoints.indexOf(endPoint);

        return startIndex != -1 && endIndex != -1 && startIndex < endIndex;
    }

    public int getLogicalOrderDistance() {
        if (locationPoints == null || locationPoints.size() < 2) {
            return 0;
        }

        // int indexFirstPoint = locationPoints.indexOf(locationPoints.indexOf(0));
        // int indexSecondPoint = locationPoints.indexOf(locationPoints.indexOf(1));

        int indexFirstPoint = 0;
        int indexSecondPoint = locationPoints.size() - 1;

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
