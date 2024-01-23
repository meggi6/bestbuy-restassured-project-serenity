package com.bestbuy.steps;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

public class ProductSteps extends ProductPojo{
   // static int productID;

    @Step("Creating product with name: {0}, type : {1}, price : {2}, shipping : {3}, upc : {4}, description: {5}, manufacturer : {6}, model : {7}, url : {8} and image : {9} ")
    public ValidatableResponse createProduct(String name, String type, int price, int shipping, String upc,
                                             String description, String manufacturer, String model, String url, String image) {

        ProductPojo productPojo = ProductPojo.getProductPojo(name, type, price, shipping, upc, description,
                manufacturer, model, url, image);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(productPojo)
                .post()
                .then().log().all();
    }

    @Step("Getting the product information with productID: {0}")
    public ValidatableResponse getProductInfoByproductID(int productID) {

        return SerenityRest.given().log().all()
                .header("Accept", "application/json")
                .pathParam("productID", productID)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then().log().all();
    }

    @Step("Updating product information with productID : {0}, name: {1}, type : {2}")
    public ValidatableResponse updateProduct(int productID, String name, String type, int price, int shipping,
                                             String upc, String description, String manufacturer, String model, String url, String image) {

        ProductPojo productPojo = ProductPojo.getProductPojo(name, type, price, shipping, upc, description,
                manufacturer, model, url, image);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .pathParam("productID", productID)
                .when()
                .body(productPojo)
                .put(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then().log().all();
    }

    @Step("Deleting product information with productID : {0}")
    public ValidatableResponse deleteProduct(int productID) {

        return SerenityRest.given().log().all()
                .header("Accept", "application/json")
                .pathParam("productID", productID)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then().log().all();
    }
}
