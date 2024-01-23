package com.bestbuy.steps;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.HashMap;

public class StoreSteps {

    @Step("Creating product with name: {0}, type : {1}, price : {2}, shipping : {3}, upc : {4}, description: {5}, manufacturer : {6}, model : {7}, url : {8} and image : {9} ")
    public ValidatableResponse createStore(String name, String type, String address, String address2, String city,
                                           String state, String zip, int lat, int lng, String hours, HashMap<String, Object> services) {

        StorePojo storePojo = StorePojo.getStorePojo(name, type, address, address2, city, state,
                zip, lat, lng, hours, services);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(storePojo)
                .post()
                .then().log().all();
    }

    @Step("Getting the product information with productID: {0}")
    public ValidatableResponse getStoreInfoByStoreID(int storeID) {

        return SerenityRest.given().log().all()
                .header("Accept", "application/json")
                .pathParam("storeID", storeID)
                .when()
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then().log().all();
    }

    @Step("Updating product information with productID : {0}, name: {1}, type : {2}")
    public ValidatableResponse updateStore(int storeID, String name, String type, String address, String address2, String city, String state, String zip, int lat, int lng, String hours, HashMap<String, Object> services) {
        StorePojo storePojo = StorePojo.getStorePojo(name, type, address, address2, city, state,
                zip, lat, lng, hours, services);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .pathParam("storeID", storeID)
                .when()
                .body(storePojo)
                .put(EndPoints.UPDATE_STORE_BY_ID)
                .then().log().all();
    }

    @Step("Deleting product information with productID : {0}")
    public ValidatableResponse deleteStore(int storeID) {

        return SerenityRest.given().log().all()
                .header("Accept", "application/json")
                .pathParam("storeID", storeID)
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID)
                .then().log().all();
    }
}
