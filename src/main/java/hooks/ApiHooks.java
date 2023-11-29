package hooks;
import io.cucumber.java.*;

public class ApiHooks {

    @BeforeAll
    public static void beforeAll(){
        System.out.println("Before all the scenarios");
    }

    @AfterAll
    public static void afterAll(){
        System.out.println("After all the scenarios");
    }

    @Before("@requires-db-connection")
    public void before(){
        System.out.println("Before each Api test which needs db connection");
    }

    @BeforeStep
    public void beforeStep(){
        System.out.println("Before step");
    }

    @AfterStep
    public void afterStep(){
        System.out.println("After step");
    }

    @After("@requires-db-connection")
    public void after(){
        System.out.println("After each Api test which needs db connection");
    }
}
