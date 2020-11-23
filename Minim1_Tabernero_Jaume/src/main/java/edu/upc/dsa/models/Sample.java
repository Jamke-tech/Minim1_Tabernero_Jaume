package edu.upc.dsa.models;

public class Sample {
    int idSample;
    String idclinic;
    String idperson;
    String date;
    String result;
    String comment;

    public Sample(int idSample,String idclinic,String date,String idperson ){
        this.idSample=idSample;
        this.idclinic=idclinic;
        this.idperson=idperson;
        this.date=date;
        this.result= "NO TEST";
        this.comment=" NO COMMENT ";

    }

    public void setResult(String resultado) {
        this.result=resultado;


    }

    public void setComment(String comment) {
        this.comment=comment;
    }

    public String getIdPerson() {
        return idperson;
    }

    public String getResult() {
        return result;
    }

    public String getIdSample() {
        return String.valueOf(idSample);
    }

    public int getIdSampleint() {
        return idSample;
    }

    public String getClinic() {
        return idclinic;
    }

    public String getDate() {
        return date;
    }

}
