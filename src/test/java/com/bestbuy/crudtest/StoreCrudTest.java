package com.bestbuy.crudtest;

import com.bestbuy.steps.StoreSteps;
import com.bestbuy.testbase.StoreTestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

@RunWith(SerenityRunner.class)
public class StoreCrudTest extends StoreTestBase {
    static String name = TestUtils.getRandomValue() + "prime";
    static String type = "Battery";
    static String address = "lewes road";
    static String address2 = "barnet";
    static String city = "london";
    static String state = "england";
    static String zip = "n129nh";
    static int lat = 453637;
    static int lng = 871888;
    static String hours = "9";
    static int storeID;

    @Steps
    StoreSteps steps;

    @Title("This will create a new store")
    @Test
    public void test001() {
        HashMap<String, Object> services = new HashMap<>();
        services.put("ABC", "2 Hours");
        services.put("XYZ", "2 Hours");

        ValidatableResponse response = steps.createStore(name, type, address, address2, city, state, zip, lat, lng, hours, services).statusCode(201);
        storeID = response.extract().path("id");
    }

    @Title("Verify if the store was added to the application")
    @Test
    public void test002() {
        ValidatableResponse response = steps.getStoreInfoByStoreID(storeID);
        response.statusCode(200);
    }

    @Title("Update the store information and verify the updated information")
    @Test
    public void test003() {
        HashMap<String, Object> services = new HashMap<>();
        services.put("ABC", "2 Hours");
        services.put("XYZ", "2 Hours");

        name = name + "_updated";
        type = type + "_updated";
        steps.updateStore(storeID, name, type, address, address2, city, state, zip, lat, lng, hours, services).statusCode(200);
    }

    @Title("Delete the store and verify if the store is deleted")
    @Test
    public void test004() {
        steps.deleteStore(storeID).statusCode(200);
        steps.getStoreInfoByStoreID(storeID).statusCode(404);
    }
}
