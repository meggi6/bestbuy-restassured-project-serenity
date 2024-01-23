package com.bestbuy.crudtest;

import com.bestbuy.steps.ProductSteps;
import com.bestbuy.testbase.ProductTestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class ProductCrudTest extends ProductTestBase {

    static String name = TestUtils.getRandomValue() + "prime";
    static String type = "Battery";
    static int price = 1250;
    static int shipping = 1;
    static String upc = "JAVA";
    static String description = "API";
    static String manufacturer = "nishant manufacturer";
    static String model = "Testing";
    static String url = "prime" + TestUtils.getRandomValue() + "@gmail.com";
    static String image = "prime.jpg";
    static int productID;

    @Steps
    ProductSteps steps;

    @Title("This will create a new product")
    @Test
    public void test001() {
        ValidatableResponse response= steps.createProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image).statusCode(201);
        productID = response.extract().path("id");
    }

    @Title("Verify if the product was added to the application")
    @Test
    public void test002() {
        ValidatableResponse response = steps.getProductInfoByproductID(productID);
        response.statusCode(200);
    }

    @Title("Update the product information and verify the updated information")
    @Test
    public void test003() {
        name = name + "_updated";
        type = type + "_updated";
        steps.updateProduct(productID, name, type, price, shipping, upc, description, manufacturer, model, url, image).statusCode(200);
    }

    @Title("Delete the student and verify if the student is deleted")
    @Test
    public void test004() {
        steps.deleteProduct(productID).statusCode(200);
        steps.getProductInfoByproductID(productID).statusCode(404);
    }
}
    

