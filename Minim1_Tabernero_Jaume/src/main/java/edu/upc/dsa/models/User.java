package edu.upc.dsa.models;

import java.util.LinkedList;

public class User {
    String id;
    String name;
    String surname;
    int edad;
    String salud;
    LinkedList<Sample> listaSamples;

    public User (String id, String name, String surname, int edad, String salud ){
        this.id=id;
        this.name=name;
        this.surname=surname;
        this.edad=edad;
        this.salud=salud;
        listaSamples=new LinkedList<Sample>();
    }

    public LinkedList<Sample> getSamples() {

        return listaSamples;

    }

    public void añadirMuestratest(Sample muestra) {
        listaSamples.add(muestra);

    }

    public int getAge() {
        return edad;
    }

    public String getidPerson() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSalud() {
        return salud;
    }
}
