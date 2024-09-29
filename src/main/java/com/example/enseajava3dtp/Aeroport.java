package com.example.enseajava3dtp;

import static java.lang.Math.asin;
import static java.lang.Math.sqrt;

public class Aeroport {

    private String IATA;
    private String Name;
    private String Country;
    private Double latitude;
    private Double longitude;


    public Aeroport(String IATA, String name, String country, Double latitude, Double longitude) {
        this.IATA = IATA;
        Name = name;
        this.Country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getIATA() {
        return IATA;
    }

    public String getName() {
        return Name;
    }

    public String getCountry() {
        return Country;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    //https://fr.wikipedia.org/wiki/Formule_de_haversine
    public Double distance(double latitude, double longitude)
    {
        double radius = 6371;
        double lat1Rad = Math.toRadians(latitude);
        double lat2Rad = Math.toRadians(this.getLatitude());
        double deltaLat = Math.toRadians(this.getLatitude() - latitude);
        double deltaLon = Math.toRadians(this.getLongitude() - longitude);
        double b = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(sqrt(b), sqrt(1 - b));

        return  (2 * radius * asin(sqrt(b)));
    }

    public double distance (Aeroport a)
    {
        return (this.distance(a.latitude, a.longitude));
    }

    @Override
    public String toString() {
        return ("Aeroport [IATA: " + IATA + "\tName:" + Name + "\tLongitude: " + longitude + "\tLatitude:" + latitude + "\tCountry:" + Country + "]");
    }
}
