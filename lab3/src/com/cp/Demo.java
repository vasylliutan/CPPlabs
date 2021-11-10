package com.cp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Demo {

    private static List<Event> events = new ArrayList<>();

    private static final String FILENAME_1 = "file1.txt";
    private static final String FILENAME_2 = "file2.txt";

    public static void main(String[] args) {
        System.out.println("----- Initial list of events -----");
        events = readFile(FILENAME_1);
        display(events);

        while (true) {
            System.out.println("Enter 1-3 to execute corresponding task or 'e' to stop the app: ");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();

            try {
                menu(input);
            } catch (InputMismatchException e) { break; }
            catch (IllegalArgumentException e) {
                e.printStackTrace();
                System.out.println();
            }
        }
    }

    private static void menu(String input) {
        switch (input) {
            case "1":
                display(getYearEvents());
                break;
            case "2":
                display(removeAdjacentEvents(getYearEvents()));
                break;
            case "3":
                List<String> dates = getUniqueDatesFromFirstFile();
                System.out.println("Unique dates from 1st file: ");
                display(dates);
                System.out.println("Events with more than a year date difference: ");
                display(getEventsWithMoreThanYearDifference(dates));
                break;
            case "e":
                throw new InputMismatchException();
            default:
                throw new IllegalArgumentException("Wrong input parameter entered!");
        }
    }

    private static Map<Integer, List<String>> getYearEvents() {
        Map<Integer, List<String>> yearEvents = new HashMap<>();

        // compose map of { year : events }
        for (Event e : events) {
            if (yearEvents.containsKey(e.getYear())) {
                yearEvents.get(e.getYear()).add(e.getName());
            } else {
                List<String> l = new ArrayList<>();
                l.add(e.getName());
                yearEvents.put(e.getYear(), l);
            }
        }
        // sort by date
        sort(yearEvents);

        return yearEvents;
    }

    private static void sort(Map<Integer, List<String>> yearEvents) {
        for (Map.Entry<Integer, List<String>> entry : yearEvents.entrySet()) {
            entry.getValue().sort(Comparator.naturalOrder());
        }
    }

    private static Map<Integer, List<String>> removeAdjacentEvents(
            Map<Integer, List<String>> yearEvents) {
        List<String> toRemove = new ArrayList<>();
        for (int i = 0; i < events.size() - 1; i++) {
            for (int j = 1; j < events.size(); j++) {
                // if difference is 1 day
                if (events.get(i).getYear() - events.get(j).getYear() == 0 &&
                        events.get(i).getMonth() - events.get(j).getMonth() == 0 &&
                        Math.abs(events.get(i).getDay() - events.get(j).getDay()) == 1) {
                    // if not present in list already then add it
                    if (!toRemove.contains(events.get(i).getName())) toRemove.add(events.get(i).getName());
                    if (!toRemove.contains(events.get(j).getName())) toRemove.add(events.get(j).getName());
                }
            }
        }

        for (Map.Entry<Integer, List<String>> entry : yearEvents.entrySet()) {
            entry.getValue().removeAll(toRemove);
        }
        return yearEvents;
    }

    private static List<String> getUniqueDatesFromFirstFile() {
        List<Event> second = readFile(FILENAME_2);
        events.removeAll(second);

        List<String> dates = new ArrayList<>();

        for (Event e : events) {
            dates.add(String.valueOf(e.day) + '.' + e.month + '.' + e.year);
        }
        return dates;
    }

    private static List <String> getEventsWithMoreThanYearDifference(List<String> uniqueDates) {
        List <String> dates = new ArrayList<>();

        for (int i = 0; i < events.size() - 1; i++) {
            for (int j = 1; j < events.size(); j++) {
                if (Math.abs(events.get(i).getYear() - events.get(j).getYear()) >= 1) {
                    dates.add(events.get(i).getDate() + " : " + events.get(j).getDate());
                }
            }
        }
        return dates;
    }

    private static List<Event> readFile(String filename) {
        List<Event> res = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                Event e = new Event(line);
                res.add(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    private static void display(List list) {
        for (Object o : list) {
            System.out.println(o);
        }
        System.out.println();
    }

    private static void display(Map<Integer, List<String>> map) {
        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println();
    }

}
