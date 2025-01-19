package com.example;

import java.util.Scanner;

import com.example.DataStructure.MyArrayList;
import com.example.DataStructure.MyList;

public class Main {
    private static final Navigator navigator = new NavigatorImpl();
    private static final MyList<String> availablePoints = new MyArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        populateRoutes();

        int choice;
        do {
            printMenu();
            System.out.print("Выберете пункт меню: ");
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
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        } while (choice != 11);
    }

    private static void printMenu() {
        System.out.println("===== Система Навигации =====");
        System.out.println("1. Добавить маршрут");
        System.out.println("2. Удалить маршрут");
        System.out.println("3. Проверить наличие маршрута");
        System.out.println("4. Отобразить количество маршрутов");
        System.out.println("5. Отобразить маршрут по ID");
        System.out.println("6. Выбрать маршрут");
        System.out.println("7. Поиск маршрутов");
        System.out.println("8. Отобразить доступные точки");
        System.out.println("9. Получить избранные маршруты по пункту назначения");
        System.out.println("10. Получить топ-3 маршрутов");
        System.out.println("11. Выйти");
    }

    private static void addRoute() {
        displayAvailablePoints();

        MyList<String> routePoints = new MyArrayList<>();
        System.out.print("Введите точки маршрута (через запятую, например, Точка A, Точка B): ");
        String pointsInput = scanner.nextLine();
        String[] pointsArray = pointsInput.split(",");
        for (String point : pointsArray) {
            String trimmedPoint = point.trim();
            routePoints.add(trimmedPoint);
            availablePoints.add(trimmedPoint);
        }

        System.out.print("Введите расстояние: ");
        double distance = scanner.nextDouble();

        System.out.print("Введите популярность маршрута: ");
        int popularity = scanner.nextInt();

        System.out.print("Это избранный маршрут? (true/false): ");
        boolean isFavorite = scanner.nextBoolean();

        Route route = new Route(distance, popularity, isFavorite, routePoints);

        navigator.addRoute(route);

        if (navigator.contains(route)) {
            System.out.println("Маршрут успешно добавлен!");
        } else {
            System.out.println("Во время добавления возникла ошибка!");
        }
    }

    private static void removeRoute() {
        System.out.print("Введите ID маршрута для удаления: ");
        String routeId = scanner.nextLine();

        Route route = navigator.getRoute(routeId);

        if (navigator.contains(route)) {
            navigator.removeRoute(routeId);
            System.out.println("Маршрут успешно удалён: " + route.getId());
        } else {
            System.out.println("Маршрут не найден!");
        }
    }

    private static void checkContains() {
        System.out.print("Введите ID маршрута для проверки: ");
        String routeId = scanner.nextLine();
        Route route = navigator.getRoute(routeId);

        boolean contains = navigator.contains(route);
        System.out.println("Маршрут найден: " + contains);
    }

    private static void displaySize() {
        int size = navigator.size();
        System.out.println("Количество маршрутов: " + size);
    }

    private static void displayRouteById() {
        System.out.print("Введите ID маршрута для отображения: ");
        String routeId = scanner.nextLine();

        Route route = navigator.getRoute(routeId);
        if (route != null) {
            System.out.println("Детали маршрута: " + route.getId() + ", Расстояние: " +
                    route.getDistance() + ", Популярность: " + route.getPopularity());

            displayRoute("Детали маршрута", route);

            System.out.print("Вы хотите изменить статус маршрута на избранный? (да/нет): ");
            String input = scanner.nextLine();

            if ("да".equalsIgnoreCase(input.trim())) {
                System.out.print("Установить маршрут как избранный? (true/false): ");
                boolean isFavorite = scanner.nextBoolean();
                navigator.setFavorite(route.getId(), isFavorite);
                System.out.println("Статус маршрута обновлён.");
            } else {
                System.out.println("Маршрут остался без изменений.");
            }
        } else {
            System.out.println("Маршрут не найден!");
        }
    }

    private static void chooseRoute() {
        System.out.print("Введите ID маршрута для выбора: ");
        String routeId = scanner.nextLine();

        navigator.chooseRoute(routeId);
        System.out.println("Маршрут успешно выбран!");
    }

    private static void searchRoutes() {
        System.out.print("Введите начальную точку: ");
        String startPoint = scanner.nextLine();

        System.out.print("Введите конечную точку: ");
        String endPoint = scanner.nextLine();

        Iterable<Route> routes = navigator.searchRoutes(startPoint, endPoint);
        System.out.println("Результаты поиска:");
        for (Route route : routes) {
            System.out.println("Маршрут: " + route.getId() + ", Расстояние: " +
                    route.getDistance() + ", Популярность: "
                    + route.getPopularity() + ", Точки маршрута: " + route.getLocationPoints() + ", Избранный маршрут: "
                    + route.isFavorite());
        }
    }

    private static void displayAvailablePoints() {
        System.out.println("Доступные точки: " + availablePoints);
    }

    private static void exit() {
        System.out.println("Выход из системы навигации. До свидания!");
    }

    private static void populateRoutes() {
        Route route1 = new Route(100.0, 8, true, new MyArrayList<>("A", "C", "Q"));
        Route route2 = new Route(110.0, 8, true, new MyArrayList<>("A", "B", "C", "W"));
        Route route3 = new Route(120.0, 9, true, new MyArrayList<>("A", "B", "W", "C", "E"));
        Route route4 = new Route(600.0, 10, false, new MyArrayList<>("A", "C", "R"));
        Route route5 = new Route(650.0, 10, false, new MyArrayList<>("A", "B", "C", "T"));
        Route route6 = new Route(700.0, 8, true, new MyArrayList<>("J", "B", "U"));
        Route route7 = new Route(1000.0, 9, false, new MyArrayList<>("A", "B", "C", "I", "Y", "P", "O"));
        Route route8 = new Route(1100.0, 9, true, new MyArrayList<>("A", "B", "P"));
        Route route9 = new Route(1200.0, 11, false, new MyArrayList<>("O", "B", "C", "Y", "F", "H"));
        Route route10 = new Route(3000.0, 11, false, new MyArrayList<>("L", "B", "C"));
        Route route11 = new Route(3000.0, 11, false, new MyArrayList<>("L", "B", "Q"));
        Route route12 = new Route(3000.0, 11, false, new MyArrayList<>("L", "B", "Q", "B", "Q", "H"));
        Route route13 = new Route(1100.0, 15, true, new MyArrayList<>("O", "A", "B"));
        Route route14 = new Route(1100.0, 9, true, new MyArrayList<>("E", "B", "P"));
        Route route15 = new Route(120.0, 10, true, new MyArrayList<>("J", "A", "W", "F", "C"));
        Route route16 = new Route(120.0, 10, true, new MyArrayList<>("M", "A", "W", "U", "C"));
        Route route17 = new Route(650.0, 8, false, new MyArrayList<>("A", "B", "C", "N"));
        Route route18 = new Route(650.0, 8, false, new MyArrayList<>("A", "B", "C", "M"));
        Route route19 = new Route(650.0, 8, false, new MyArrayList<>("A", "B", "C", "X"));
        Route route20 = new Route(120.0, 10, true, new MyArrayList<>("X", "A", "W", "U", "C"));

        navigator.addRoute(route1);
        navigator.addRoute(route2);
        navigator.addRoute(route3);
        navigator.addRoute(route4);
        navigator.addRoute(route5);
        navigator.addRoute(route6);
        navigator.addRoute(route7);
        navigator.addRoute(route8);
        navigator.addRoute(route9);
        navigator.addRoute(route10);
        navigator.addRoute(route11);
        navigator.addRoute(route12);
        navigator.addRoute(route13);
        navigator.addRoute(route14);
        navigator.addRoute(route16);
        navigator.addRoute(route15);
        navigator.addRoute(route17);
        navigator.addRoute(route19);
        navigator.addRoute(route20);
        navigator.addRoute(route18);

        availablePoints
                .addAll(MyList.of("A", "B", "C", "D", "Q", "W", "E", "R", "T", "J", "U", "I", "Y", "O", "P", "F", "H",
                        "L", "N", "M", "X"));
    }

    private static void getFavoriteRoutesByDestination() {
        System.out.print("Введите пункт назначения: ");
        String destinationPoint = scanner.nextLine();

        Iterable<Route> favoriteRoutes = navigator.getFavoriteRoutes(destinationPoint);
        displayRoutes("Любимые маршруты по пунктам назначения", favoriteRoutes);
    }

    private static void getTop3Routes() {
        Iterable<Route> top3Routes = navigator.getTop3Routes();
        displayRoutes("Топ 3 маршрутов", top3Routes);
    }

    private static void displayRoutes(String title, Iterable<Route> routes) {
        System.out.println(title + ":");
        for (Route route : routes) {
            System.out.println("Маршрут: " + route.getId() + ", Дистанция: " +
                    route.getDistance() + ", Популярность: "
                    + route.getPopularity() + ", Точки маршрута: " + route.getLocationPoints() +
                    ", Избранный: "
                    + route.isFavorite());
        }
    }

    private static void displayRoute(String title, Route route) {
        System.out.println("=========================================");
        System.out.println("            " + title.toUpperCase());
        System.out.println("=========================================");

        System.out.println("-----------------------------------------");
        System.out.printf("Маршрут #%s\n", route.getId());
        System.out.printf("ID: %s\n", route.getId());
        System.out.printf("Дистанция: %.2f км\n", route.getDistance());
        System.out.printf("Популярность: %d\n", route.getPopularity());
        System.out.printf("Точки маршрута: %s\n", route.getLocationPoints());
        System.out.printf("Избранный: %s\n", route.isFavorite() ? "Да" : "Нет");

        System.out.println("=========================================");
    }

    // private static void displayRoutes(String title, Iterable<Route> routes) {
    // System.out.println("=========================================");
    // System.out.println(" " + title.toUpperCase());
    // System.out.println("=========================================");

    // int index = 1;
    // for (Route route : routes) {
    // System.out.println("-----------------------------------------");
    // System.out.printf("Маршрут #%d\n", index++);
    // System.out.printf("ID: %s\n", route.getId());
    // System.out.printf("Дистанция: %.2f км\n", route.getDistance());
    // System.out.printf("Популярность: %d\n", route.getPopularity());
    // System.out.printf("Точки маршрута: %s\n", route.getLocationPoints());
    // System.out.printf("Избранный: %s\n", route.isFavorite() ? "Да" : "Нет");
    // }

    // if (index == 1) {
    // System.out.println("Нет доступных маршрутов для отображения.");
    // }

    // System.out.println("=========================================");
    // }
}
