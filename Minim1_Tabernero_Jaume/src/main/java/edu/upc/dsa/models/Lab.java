package edu.upc.dsa.models;

import java.util.LinkedList;
import java.util.Queue;

public class Lab {

    String name;
    int idLab;
    Queue<Sample> colamuestras;


    public Lab( int idLab, String name) {
        this.name=name;
        this.idLab = idLab;
        this.colamuestras = new LinkedList<>();
    }


    public int getid() {
        return idLab;
    }

    public void addSample(Sample muestra) {
        colamuestras.add(muestra);

    }

    public Sample processSample() {
        Sample muestraprocess= colamuestras.poll();
        int valor = (int) (Math.random()*100);

        if(valor<40){
            muestraprocess.setResult("POSITIVO");
            muestraprocess.setComment("LO SIENTO CHAVAL TE TOCA CUARENTENA");

        }
        else{
            muestraprocess.setResult("NEGATIVO");
            muestraprocess.setComment("TE SALVASTE POR LOS PELOS");
        }
        return muestraprocess;






    }
}
