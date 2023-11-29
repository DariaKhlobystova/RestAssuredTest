package testClasses;

import api.GetAllBookingsApi;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

public class ApiTests {

    @Test
    public void testGetAllBookingsApi(){
        GetAllBookingsApi getAllBookingsApi = new GetAllBookingsApi();
        Response response = getAllBookingsApi.sendRequest();
        ValidatableResponse validatableResponse = response.then();
        validatableResponse.statusCode(200);

//        if(response.statusCode() != 200){
//            response.prettyPrint();
//        }
    }
}
