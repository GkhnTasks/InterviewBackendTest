import models.CreateUserModel;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import services.GoRestService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CreateUserTests {

    static int id;

    @Test
    @Order(1)
    public void Users_CreateUsers_Success(){

        final CreateUserModel createUserModel = new CreateUserModel("Gino Paloma", "Male", "qatest@test1.com", "Active");

          id = GoRestService.createUser(createUserModel)
                .then()
                .statusCode(SC_CREATED)
                .body("data.id", notNullValue())
                .body("data.name", equalTo(createUserModel.getName()))
                .extract().jsonPath().getInt("data.id");


    }

    @Test
    @Order(2)
    public void Users_GetUsers_Success(){

        GoRestService.getUser(id)
                .prettyPeek()
                .then()
                .statusCode(SC_OK)
                .body("data.id",equalTo(id));


    }

    @Test
    @Order(3)
    public void Users_UpdateUsers_Success(){

        Map<String,Object> userMap=new HashMap<>();
        userMap.put("name","Mike");
        userMap.put("gender","female");

         GoRestService.updateUser(id,userMap)
                .then()
                .statusCode(SC_OK)
                 .body("data.name",equalTo(userMap.get("name")))
                 .body("data.gender",equalTo(userMap.get("gender")));

    }


    @Test
    @Order(4)
    public void Users_DeleteUsers_Success(){

        GoRestService.deleteUser(id)
                .then()
                .statusCode(SC_NO_CONTENT);

    }

    @Test
    @Order(5)
    public void Users_DeleteUsers_Fail(){

        GoRestService.getUser(id)
                .then()
                .statusCode(SC_NOT_FOUND);

    }



}
