package com.laba;


import com.laba.enums.LifeTimeType;
import com.laba.enums.NaturePlantsType;
import com.laba.enums.SeasonType;
import com.laba.manager.NaturePlantsManager;
import com.laba.nature_plants.Bush;
import com.laba.nature_plants.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    private static final NaturePlantsManager manager = new NaturePlantsManager(setupData());
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isQuit = false;
        do {
            printMenu();
            String result = scanner.nextLine();
            switch (result) {
                case "1":
                    NaturePlantsType type = getNaturePlantsType();
                    System.out.println(manager.filterByType(type));
                    break;
                case "2":
                    performSorting();
                    System.out.println(manager.getList());
                    break;
                case "3":
                    isQuit = true;
                    break;
                default:
                    System.out.println("You have entered incorrect value. Try again");
            }
        } while(!isQuit);
        System.out.println("Goodbye!");
    }

    private static void printMenu() {
        System.out.println("Choose one option:");
        System.out.println("1: Filter by NaturePlants type");
        System.out.println("2: Sort data");
        System.out.println("3: Exit");
    }

    private static NaturePlantsType getNaturePlantsType() {
        System.out.println("Choose your nature plants type: ");
        do {
            System.out.println("1 - FRUIT\n2 - CONIFERS\n3 - TROPICAL");
            String result = scanner.nextLine();
            switch (result) {
                case "1":
                    return NaturePlantsType.FRUIT;
                case "2":
                    return NaturePlantsType.CONIFERS;
                case "3":
                    return NaturePlantsType.TROPICAL;
                default:
                    System.out.println("You have entered incorrect value. Try again");
            }
        } while(true);
    }

    private static void performSorting() {
        boolean isQuit = false;
        do {
            printSortingMenu();
            String sortingMethod = scanner.nextLine();
            boolean isDescending = getSortingOrder();

            switch (sortingMethod) {
                case "1":
                    manager.sortByName(isDescending);
                    isQuit = true;
                    break;
                case "2":
                    manager.sortByNaturePlantsType(isDescending);
                    isQuit = true;
                    break;
                case "3":
                    manager.sortByconsumeWater(isDescending);
                    isQuit = true;
                    break;
                case "4":
                    manager.sortByCurentSeason(isDescending);
                    isQuit = true;
                    break;
                case "5":
                    manager.sortByLifeTime(isDescending);
                    isQuit = true;
                default:
                    System.out.println("You have entered incorrect value. Try again");
            }
        } while(!isQuit);
    }

    private static void printSortingMenu() {
        System.out.println("Choose one option:");
        System.out.println("1: Sort by name");
        System.out.println("2: Sort by nature plants type");
        System.out.println("3: Sort by consume water");
        System.out.println("4: Sort by season type");
        System.out.println("5: Sort by lifetime type");
    }

    private static boolean getSortingOrder() {
        do {
            System.out.println("Choose sorting order: ");
            System.out.println("1 - Ascending\n2 - Descending");
            String order = scanner.nextLine();

            switch (order) {
                case "1":
                    return false;
                case "2":
                    return true;
                default:
                    System.out.println("You have entered incorrect value. Try again");
            }
        } while(true);
    }

    private static List<Tree> setupData() {
        List<Tree> list = new ArrayList<>();
        list.add(new Tree("Yablunya", NaturePlantsType.FRUIT,10.1,SeasonType.SUMMER, LifeTimeType.MANY_YEARS));
        list.add(new Tree("Yalynka", NaturePlantsType.CONIFERS, 20.3, SeasonType.WINTER, LifeTimeType.ONE_YEAR));
        list.add(new Tree("Banana", NaturePlantsType.TROPICAL, 12.2, SeasonType.SUMMER, LifeTimeType.TWO_YEARS));

        list.add(new Tree("Slyvka", NaturePlantsType.FRUIT,11.5,SeasonType.SUMMER, LifeTimeType.TWO_YEARS));
        list.add(new Tree("Sosna", NaturePlantsType.CONIFERS, 18.3, SeasonType.WINTER, LifeTimeType.MANY_YEARS));
        list.add(new Tree("Kokos", NaturePlantsType.TROPICAL, 8.9, SeasonType.SUMMER, LifeTimeType.ONE_YEAR));

        list.add(new Tree("Gryshka", NaturePlantsType.FRUIT,9.2,SeasonType.SUMMER, LifeTimeType.ONE_YEAR));
        list.add(new Tree("Modryna", NaturePlantsType.CONIFERS, 13.5, SeasonType.WINTER, LifeTimeType.ONE_YEAR));
        list.add(new Tree("Mango", NaturePlantsType.TROPICAL, 23.1, SeasonType.SUMMER, LifeTimeType.MANY_YEARS));

        list.add(new Bush("Kalyna", 23.1, SeasonType.SUMMER, LifeTimeType.MANY_YEARS,13.4));
        list.add(new Bush("Verba", 23.1, SeasonType.SUMMER, LifeTimeType.MANY_YEARS, 23.1));

        return list;
    }
}