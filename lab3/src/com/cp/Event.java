package com.cp;

import java.util.Objects;

public class Event {

    String name;
    int year;
    int month;
    int day;

    public Event(String input) {
        String[] data = input.split(" ");

        setName(data[0]);
        setYear(Integer.parseInt(data[1]));
        setMonth(Integer.parseInt(data[2]));
        setDay(Integer.parseInt(data[3]));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year > 2021)
            throw new RuntimeException("Wrong year value entered!");
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if (month < 0 || month > 12)
            throw new RuntimeException("Wrong month value entered!");
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        if (day < 0 || day > 31)
            throw new RuntimeException("Wrong day value entered!");
        this.day = day;
    }

    public String getDate() {
        return String.valueOf(getDay()) + '.' + getMonth() + '.' + getYear();
    }

    @Override
    public String toString() {
        return "Event{" +
                '\'' + getName() + '\'' +
                " - " + getDay() + '.' + getMonth() + '.' + getYear() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return getYear() == event.getYear() && getMonth() == event.getMonth() && getDay() == event.getDay() && getName().equals(event.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getYear(), getMonth(), getDay());
    }
}
