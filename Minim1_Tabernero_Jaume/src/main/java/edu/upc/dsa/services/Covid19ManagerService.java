package edu.upc.dsa.services;

import edu.upc.dsa.Covid19Manager;
import edu.upc.dsa.Covid19ManagerImpl;
import edu.upc.dsa.models.Sample;
import edu.upc.dsa.models.User;
import io.swagger.annotations.Api;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.Path;
import java.util.LinkedList;

@Api(value = "/covid19", description = "Endpoint to Covid Services")
@Path("/covid19")
public class Covid19ManagerService {

    private Covid19Manager mg19;

    public Covid19ManagerService() {
        this.mg19 = Covid19ManagerImpl.getInstance();
        //añadiremos 2 personas

        mg19.addPerson("First","Jaume", "Tabernero", 21,"A");
        mg19.addPerson("Second","Manolito", "Gafotas", 14,"D");

    }
    @POST
    @ApiOperation(value = "Añadir persona", notes = "Funcion para añadir persoana a la BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code=500,message="No valid parameters")
    })
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCase(User user){

        String idperson= user.getidPerson();
        String name = user.getName();
        String surname = user.getSurname();
        int edad=user.getAge();
        String salud = user.getSalud();
        if(idperson ==null || name ==null || surname==null  || salud==null){
            return Response.status(500).build();
        }
        else {
            mg19.addPerson(idperson, name, surname, edad, salud);

            return Response.status(201).build();
        }

    }
    @POST
    @ApiOperation(value = "Añadir Sample", notes = "Funcion para añadir una muestra a una persona")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code=500,message="No valid parameters")
    })
    @Path("/{idLab}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response extractSample (@PathParam("id") int idLab,Sample sample){


        int idSample = sample.getIdSampleint();
        String idclinic =sample.getClinic();
        String idperson = sample.getIdPerson();
        String date= sample.getDate();

        int result=mg19.extractSample(idSample,idclinic,idperson,date,idLab);
        if (result==0)
        {
            return Response.status(201).build();
        }
        else
        {
            return Response.status(500).build();
        }



    }
    @GET
    @ApiOperation(value = "Get all samples from user X", notes = "Nos da samples de usuario que decimos")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful",response = Sample.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "ID user not valid"),
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListSamples(@PathParam("id") String id){

        LinkedList<Sample> listamuestras = mg19.listSamples(id);
        if(listamuestras != null) {
            GenericEntity<LinkedList<Sample>> lista = new GenericEntity<LinkedList<Sample>>(listamuestras) {
            };
            return Response.status(201).entity(lista).build();
        }
        else{
            return Response.status(201).build();
        }


    }
    @PUT
    @ApiOperation(value = "Procesar Muestra de lab", notes = "Nos procesa muestra del lab con id X")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "LAB not found")
    })
    @Path("/{idlab}")
    public Response processSample(@PathParam("idlab") int id){

        int proceso = mg19.processSample(id);
        if (proceso==0) return Response.status(201).build();
        else return Response.status(404).build();

    }









}
