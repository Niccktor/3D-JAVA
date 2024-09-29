package com.example.enseajava3dtp;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Earth extends Group {
    public Sphere sph;
    public ArrayList<Sphere> yellowSphere;
    public Rotate ry;
    public PhongMaterial material;

    public Earth() throws FileNotFoundException {
        sph = new Sphere(300);
        material = new PhongMaterial();
        FileInputStream inputstream = new FileInputStream("data/earth.jpg");
        material.setDiffuseMap(new Image(inputstream));
        sph.setMaterial(material);
        this.getChildren().add(sph);


    }
}
