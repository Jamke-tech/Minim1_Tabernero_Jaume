package edu.upc.dsa;

import edu.upc.dsa.models.*;


import java.util.*;

public interface Covid19Manager {
    public void addPerson(String id, String name, String surname, int edad, String salud );
    public int extractSample (int idSample, String idclinic, String idperson, String date, int idLab);
    public int processSample(int idlab);
    public LinkedList<Sample> listSamples(String idperson);

    public User getUser(String iduser);
}
