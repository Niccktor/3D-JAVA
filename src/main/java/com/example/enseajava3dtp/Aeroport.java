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
        final int R = 6371; // Rayon moyen de la Terre en kilomètres

        // Conversion des degrés en radians
        double latDistance = Math.toRadians(latitude - this.latitude);
        double lonDistance = Math.toRadians(longitude - this.longitude);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(latitude))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Calcul de la distance
        //System.out.println(c*R);
        return (R * c);
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
