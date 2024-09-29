package com.example.enseajava3dtp;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;

import static java.lang.Math.*;

public class World {
    private ArrayList<Aeroport> list;

    public World(String fileName) {
        String line;
        String[] data;
        Aeroport a;

        list = new ArrayList<Aeroport>();
        try {
            BufferedReader buff = new BufferedReader(new FileReader(fileName));
            buff.readLine();
            while ((line = buff.readLine()) != null)
            {
                data = line.split(",");
                if ((data[2].contains("large_airport") || data[2].contains("medium_airport")) && data.length > 13 && data[13] != null && !data[13].isEmpty())
                {
                    a = new Aeroport(data[13], data[3], data[8], Double.parseDouble(data[4]), Double.parseDouble(data[5]));
                    list.add(a);
                }
            }
        } catch (IOException exception) {
            System.out.println(exception.toString());
            //exception.printStackTrace();
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

    public Aeroport findNearestAirport(double latitude, double longitude)
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
        String fileName = "data/airports.csv";
        World test = new World(fileName);

        Aeroport a = test.findNearestAirport(48.866,2.316);
        System.out.println(a.toString());
        Aeroport b = test.findByCode("YUL");
        System.out.println(b.toString());
        return;
    }
}
