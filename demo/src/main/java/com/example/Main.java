package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Navigator navigator = new NavigatorImpl();
    private static final List<String> availablePoints = new ArrayList<>();
    // test

    public static void main(String[] args) {

        populateRoutes();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            printMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addRoute();
                    break;
                case 2:
                    removeRoute();
                    break;
                case 3:
                    checkContains();
                    break;
                case 4:
                    displaySize();
                    break;
                case 5:
                    displayRouteById();
                    break;
                case 6:
                    chooseRoute();
                    break;
                case 7:
                    searchRoutes();
                    break;
                case 8:
                    displayAvailablePoints();
                    break;
                case 9:
                    getFavoriteRoutesByDestination();
                    break;
                case 10:
                    getTop3Routes();
                    break;
                case 11:
                    exit();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 11);
    }

    private static void printMenu() {
        System.out.println("===== Navigation System =====");
        System.out.println("1. Add Route");
        System.out.println("2. Remove Route");
        System.out.println("3. Check if Route exists");
        System.out.println("4. Display Number of Routes");
        System.out.println("5. Display Route by ID");
        System.out.println("6. Choose Route");
        System.out.println("7. Search Routes");
        System.out.println("8. Display Available Points");
        System.out.println("9. Get Favorite Routes by Destination");
        System.out.println("10. Get Top 3 Routes");
        System.out.println("11. Exit");
    }

    private static void addRoute() {
        Route route = new Route();

        System.out.print("Enter route ID: ");
        String routeId = new Scanner(System.in).nextLine();
        route.setId(routeId);

        displayAvailablePoints();

        List<String> routePoints = new ArrayList<>();
        System.out.print("Enter route points (comma-separated, e.g., Point A, Point B): ");
        String pointsInput = new Scanner(System.in).nextLine();
        String[] pointsArray = pointsInput.split(",");
        for (String point : pointsArray) {
            String trimmedPoint = point.trim();
            routePoints.add(trimmedPoint);
            availablePoints.add(trimmedPoint);
        }
        System.out.print("Enter distance: ");
        double distance = new Scanner(System.in).nextDouble();
        route.setDistance(distance);

        System.out.print("Enter popularity: ");
        int popularity = new Scanner(System.in).nextInt();
        route.setPopularity(popularity);

        System.out.print("Is it a favorite route? (true/false): ");
        boolean isFavorite = new Scanner(System.in).nextBoolean();
        route.setFavorite(isFavorite);

        route.setLocationPoints(routePoints);

        navigator.addRoute(route);
        System.out.println("Route added successfully!");
    }

    private static void removeRoute() {
        System.out.print("Enter the route ID to remove: ");
        String routeId = new Scanner(System.in).nextLine();

        Route removedRoute = navigator.removeRoute(routeId);
        if (removedRoute != null) {
            System.out.println("Route removed successfully: " + removedRoute.getId());
        } else {
            System.out.println("Route not found!");
        }
    }

    private static void checkContains() {
        System.out.print("Enter the route ID to check: ");
        String routeId = new Scanner(System.in).nextLine();

        boolean contains = navigator.contains(routeId);
        System.out.println("Contains Route: " + contains);
    }

    private static void displaySize() {
        int size = navigator.size();
        System.out.println("Number of Routes: " + size);
    }

    private static void displayRouteById() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the route ID to display: ");
        String routeId = scanner.nextLine();

        Route route = navigator.getRoute(routeId);
        if (route != null) {
            System.out.println("Route Details: " + route.getId() + ", Distance: " + route.getDistance()
                    + ", Popularity: " + route.getPopularity());

            System.out.print("Do you want to change this route as favorite? (yes/no): ");
            String input = scanner.nextLine();

            if ("yes".equalsIgnoreCase(input.trim())) {
                System.out.print("Do you want to set this route as favorite? (true/false): ");

                // Считываем введенное значение как boolean
                boolean isFavorite = scanner.nextBoolean();
                navigator.setFavorite(route.getId(), isFavorite);
                System.out.println("Route set as new position isFavorite.");
            } else {
                System.out.println("Route not changed");
            }
        } else {
            System.out.println("Route not found!");
        }
    }

    private static void chooseRoute() {
        System.out.print("Enter the route ID to choose: ");
        String routeId = new Scanner(System.in).nextLine();

        navigator.chooseRoute(routeId);
        System.out.println("Route chosen successfully!");
    }

    private static void searchRoutes() {
        System.out.print("Enter the starting point: ");
        String startPoint = new Scanner(System.in).nextLine();

        System.out.print("Enter the ending point: ");
        String endPoint = new Scanner(System.in).nextLine();

        Iterable<Route> routes = navigator.searchRoutes(startPoint, endPoint);
        System.out.println("Search Results:");
        for (Route route : routes) {
            System.out.println("Route: " + route.getId() + ", Distance: " + route.getDistance() + ", Popularity: "
                    + route.getPopularity() + ", Location Points: " + route.getLocationPoints());
        }
    }

    private static void displayAvailablePoints() {
        System.out.println("Available Points: " + availablePoints);
    }

    private static void exit() {
        System.out.println("Exiting Navigation System. Goodbye!");
    }

    private static void populateRoutes() {
        Route route1 = new Route();
        route1.setId("R1");
        route1.setDistance(450.0);
        route1.setPopularity(8);
        route1.setFavorite(true);

        Route route2 = new Route();
        route2.setId("R2");
        route2.setDistance(300.0);
        route2.setPopularity(5);
        route2.setFavorite(false);

        Route route3 = new Route();
        route3.setId("R3");
        route3.setDistance(700.0);
        route3.setPopularity(9);
        route3.setFavorite(true);

        Route route4 = new Route();
        route4.setId("R4");
        route4.setDistance(1000.0);
        route4.setPopularity(9);
        route4.setFavorite(false);

        route1.setLocationPoints(Arrays.asList("New York", "Philadelphia", "Washington D.C."));
        route4.setLocationPoints(Arrays.asList("New York", "Washington D.C."));
        route2.setLocationPoints(Arrays.asList("New York", "Boston", "Portland"));
        route3.setLocationPoints(Arrays.asList("Philadelphia", "Washington D.C.", "Richmond"));

        navigator.addRoute(route1);
        navigator.addRoute(route2);
        navigator.addRoute(route3);
        navigator.addRoute(route4);

        availablePoints
                .addAll(Arrays.asList("New York", "Philadelphia", "Washington D.C.", "Boston", "Portland", "Richmond"));
    }

    private static void getFavoriteRoutesByDestination() {
        System.out.print("Enter the destination point: ");
        String destinationPoint = new Scanner(System.in).nextLine();

        Iterable<Route> favoriteRoutes = navigator.getFavoriteRoutes(destinationPoint);
        displayRoutes("Favorite Routes by Destination", favoriteRoutes);
    }

    private static void getTop3Routes() {
        Iterable<Route> top3Routes = navigator.getTop3Routes();
        displayRoutes("Top 3 Routes", top3Routes);
    }

    private static void displayRoutes(String title, Iterable<Route> routes) {
        System.out.println(title + ":");
        for (Route route : routes) {
            System.out.println("Route: " + route.getId() + ", Distance: " + route.getDistance() + ", Popularity: "
                    + route.getPopularity() + ", Location Points: " + route.getLocationPoints());
        }
    }
}
