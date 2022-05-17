package services;

import io.restassured.response.Response;
import models.CreateUserModel;

import java.util.HashMap;
import java.util.Map;


public class GoRestService extends BaseService {

    public static Response createUser(final CreateUserModel createUserModel){
        return defaultRequestSpecification()
                .body(createUserModel)
                .when()
                .post("/public/v1/users");
    }

    public static Response getUser(int id){
        return defaultRequestSpecification()
                .pathParam("id",id)
                .when()
                .get("/public/v1/users/{id}");
    }


    public static Response updateUser(int id,Map<String,Object> userMap){


        return defaultRequestSpecification()
                .pathParam("id",id)
                .body(userMap)
                .when()
                .patch("/public/v1/users/{id}");
    }


    public static Response deleteUser(int id){

        return defaultRequestSpecification()
                .pathParam("id",id)
                .when()
                .delete("/public/v1/users/{id}");
    }

}
