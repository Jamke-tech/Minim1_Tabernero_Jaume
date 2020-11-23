
import edu.upc.dsa.*;
import edu.upc.dsa.models.Sample;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCovid19Manager {

    Covid19Manager manager ;

    @Before
    public void setUp() throws Exception{
        manager = Covid19ManagerImpl.getInstance();
    }

    @After
    public void TearDown() throws Exception{
        manager=null;
    }


    @Test
    public void createUser() throws Exception{
        String iduser= "First";
        manager.addPerson(iduser,"Jaume", "Tabernero", 21,"A");
        manager.addPerson("Second","Manolito", "Gafotas", 14,"D");
        Assert.assertEquals(21,manager.getUser(iduser).getAge());
        Assert.assertEquals(14,manager.getUser("Second").getAge());


    }


    @Test
    public void procesarunamuestra() throws Exception{
        String iduser= "First";
        manager.addPerson(iduser,"Jaume", "Tabernero", 21,"A");

        //Extraemos una sample de Jaume
        manager.extractSample(1,"Manoli",iduser,"23/11/2020",1);

        //Procesamos esa sample

        manager.processSample(1);


    }





}
