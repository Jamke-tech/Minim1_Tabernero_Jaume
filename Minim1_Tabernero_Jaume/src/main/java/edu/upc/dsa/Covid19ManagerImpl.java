package edu.upc.dsa;

import org.apache.log4j.Logger;
import edu.upc.dsa.models.*;
import java.util.HashMap;
import java.util.LinkedList;

public class Covid19ManagerImpl implements Covid19Manager {
    private static Covid19Manager instance;

    protected HashMap<String,User> contenedorUsers;
    protected Lab[] Labs;

    final static Logger logger = Logger.getLogger(Covid19ManagerImpl.class);

    private Covid19ManagerImpl() {
        this.contenedorUsers = new HashMap<String, User>();
        this.Labs=  new Lab[3];
        //PONEMOS LOS 3 LABS disponibles
        Labs[0]=new Lab(1,"GermanLABS");
        Labs[1]=new Lab(2,"SpainIsDifferentLABS");
        Labs[2]=new Lab(3,"Pandemics.SA");


    }

    public static Covid19Manager getInstance(){
        if (instance==null) instance = new Covid19ManagerImpl();
        return instance;
    }


    @Override
    public User getUser(String iduser) {
        return contenedorUsers.get(iduser);
    }

    @Override
    public void addPerson(String id, String name, String surname, int edad, String salud) {

        User newuser = new User(id,name,surname,edad,salud);
        logger.info("Hemos creado el user con id"+id);

        contenedorUsers.put(id,newuser);
        logger.info("Hemos añadido el user con id "+id+" a la BBDD");


    }

    @Override
    public int extractSample(int idSample, String idclinic, String idperson, String date, int idLab) {


        try {
            Sample muestra = new Sample(idSample,idclinic,date,idperson);
            logger.info("Hemos creado la muestra con id"+idSample);

            int i = 0;
            int found = 0;
            while (i < Labs.length && found == 0) {
                if (Labs[i].getid() == idLab) {
                    Labs[i].addSample(muestra);
                    found = 1;

                }
                i++;
            }
            if(found==1){
                logger.info("Muestra añadida correctamente");
                return 0;
            }
            else{
                logger.error("Lab con id"+ idLab +" no existe");
                return 1;

            }

        }
        catch( IllegalArgumentException e){
            logger.fatal("No se ha creado la muestra error al processar");
            return 1;
        }

    }

    @Override
    public int processSample(int idLab) {
        try{
            int i=0;
            int found =0;
            Sample muestra = null;
            while (i<Labs.length && found==0 ){
                if(Labs[i].getid()==idLab){
                    logger.info("Laboratorio encontrado i muestra en proceso");
                    muestra = Labs[i].processSample();
                    logger.info("Procesando muestra:" +muestra.getIdSample());

                    String idperson= muestra.getIdPerson();//buscamos de quien es la muestra
                    User usuario = contenedorUsers.get(idperson); // buscamos al user

                    usuario.añadirMuestratest(muestra);


                    found=1;
                    logger.info("El resultado de la muestra ha sido "+muestra.getResult() );
                }
                i++;
            }
            if(found==1){
                logger.info("Proceso con exito");
                return 0;

            }
            else{
                logger.warn("Este laboratorio no existe");
                return 1;
            }

        }
        catch(IllegalArgumentException e){
            logger.fatal("Procces has fail");
            return 1;
        }


    }

    @Override
    public LinkedList<Sample> listSamples(String idperson) {
        User usuario = contenedorUsers.get(idperson);
        if(usuario==null){
            logger.error("Usuario con id"+ idperson+" no encontrado");
            return null;
        }
        else {
            LinkedList<Sample> listaSamples = contenedorUsers.get(idperson).getSamples();
            logger.info("Persona encontrada i mostrando lista de casos");

            return listaSamples;
        }

    }
}
