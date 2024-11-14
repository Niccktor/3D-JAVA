package com.example.enseajava3dtp;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static java.lang.Math.*;

public class World {
    private ArrayList<Aeroport> list;

    public World(String fileName) {
        String line;
        String[] data;
        Aeroport a;
        int index_IATA;
        int index_name;
        int index_country;
        int index_latitude;
        int index_longitude;
        int index_type;

        list = new ArrayList<Aeroport>();
        try {

            BufferedReader buff = new BufferedReader(new FileReader(fileName));
            line = buff.readLine();
            //System.out.println(line);
            data = line.split(",");

            index_IATA = Arrays.asList(data).indexOf("iata_code");
            index_name = Arrays.asList(data).indexOf("name");
            index_country = Arrays.asList(data).indexOf("iso_country");
            index_latitude = Arrays.asList(data).indexOf("coordinates");
            index_longitude = index_latitude + 1;
            index_type = Arrays.asList(data).indexOf("type");

            if (index_latitude == -1)
            {
                index_latitude = Arrays.asList(data).indexOf("latitude_deg");
                index_longitude = Arrays.asList(data).indexOf("longitude_deg");
            }

            while ((line = buff.readLine()) != null)
            {
                data = line.split(",");

                if (data[index_type].contains("large_airport") &&
                        (!data[index_IATA].isEmpty() && !data[index_name].isEmpty() && !data[index_country].isEmpty() && !data[index_latitude].isEmpty()  && !data[index_longitude].isEmpty()))
                {
                    a = new Aeroport(data[index_IATA], data[index_name], data[index_country], Double.parseDouble(data[index_longitude]), Double.parseDouble(data[index_latitude]));
                    list.add(a);
                }
            }
        } catch (IOException exception) {
            System.out.println(exception.toString());
        }
        System.out.println("end\n");
    }

    public Aeroport findByCode(String IATA)
    {
        for (Aeroport a : list)
        {
            if (a.getIATA().contains(IATA)) {
                return (a);
            }
        }
        return (null);
    }
    public Aeroport findByCode2(String IATA)
    {
        return (list.stream().filter(a -> a.getIATA().contains(IATA)).findFirst().orElse(null));
    }

    public Aeroport findNearestAirport(double longitude, double latitude)
    {
        double distance_nearest = 1000000.0;
        double distance;
        Aeroport nearest = null;

        for (Aeroport a : list)
        {
            distance = a.distance(latitude, longitude);
            if (distance_nearest > distance)
            {
                nearest = a;
                distance_nearest = distance;
            }
        }
        return (nearest);
    }

    public static void main(String[] args) {
        String fileName = "data/airport-codes_no_comma.csv";    // CSV TP
        //String fileName = "data/airports.csv                  // Old CSV

        World test = new World(fileName);
        System.out.println(test.list.size());

        Aeroport a = test.findNearestAirport(2.316, 48.866);
        System.out.println(a.toString());
        Aeroport b = test.findByCode("YUL");
        System.out.println(b.toString());
        Aeroport c = test.findNearestAirport(-73, 45);
        System.out.println(c.toString());
        return;
    }
}
