package com.example.enseajava3dtp;

import java.time.LocalDateTime;

public class Flight {
    public String airLineCode;
    public String airLineName;
    public LocalDateTime arrivalTime;
    public LocalDateTime departureTime;
    private final Aeroport departure;
    private final Aeroport arrival;

    private int number;

    public Flight(String airLineCode, String airLineName, LocalDateTime arrivalTime, LocalDateTime departureTime, Aeroport departure, Aeroport arrival, int number) {
        this.airLineCode = airLineCode;
        this.airLineName = airLineName;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.departure = departure;
        this.arrival = arrival;
        this.number = number;
    }

    @Override
    public String toString() {
        return ("Flight [Line Code:" + airLineCode + "\tAirline name:" + airLineName +
                "\naDeparture at:" + departureTime + "\tfrom:" + departure.toString() +
                "\nArrival at:" + arrivalTime + "\tto:" + arrival.toString() + "]");
    }

    public Aeroport getDeparture() {
        return departure;
    }

    public Aeroport getArrival() {
        return arrival;
    }
}
