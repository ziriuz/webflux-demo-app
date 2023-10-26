package dev.ziriuz.webflux.demo.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.ziriuz.webflux.demo.dto.CustomerDetailsRequest;
import dev.ziriuz.webflux.demo.dto.CustomerDetailsResponse;
import dev.ziriuz.webflux.demo.dto.ErrorResponse;
import dev.ziriuz.webflux.demo.model.ContactDetails;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.Instant;
import java.util.Base64;
import java.util.List;

public class StepDefinitions {

    private Scenario scenario;
    private HttpHeaders headers;
    private Response response;
    private CustomerDetailsRequest customerDetailsRequest;
    private CustomerDetailsResponse customerDetailsResponse;
    private static final String REQUEST_ID = "test-request-id-1";

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${application.url}")
    private String url;

    @Before
    public void setup(Scenario scenario) {
        this.scenario = scenario;
        RestAssured.reset();
        RestAssured.baseURI = url;
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        customerDetailsRequest = new CustomerDetailsRequest(REQUEST_ID, Instant.now().toEpochMilli(), "0");
    }

    @Given("user provided valid request with {string}")
    public void userProvidedValidRequestWith(String customerId) throws JsonProcessingException {
        customerDetailsRequest.setCustomerId(customerId);
        scenario.log("===================== REQUEST ======================\n" +
                objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(customerDetailsRequest));
    }

    @Given("user provided request without customerId")
    public void userProvidedRequestWithoutCustomerId() throws JsonProcessingException {
        customerDetailsRequest.setCustomerId(null);
        scenario.log("===================== REQUEST ======================\n" +
                objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(customerDetailsRequest));
    }

    @When("customer API is called")
    public void customerApiIsCalled() {
        response = RestAssured
                .given().log().uri().log().headers().log().body()
                .headers(headers)
                .body(customerDetailsRequest)
                .when()
                .get("/customer")
                .then().log().ifError().log().status().log().headers()
                .extract().response();
        scenario.log("get " + RestAssured.baseURI + "/customer");
    }

    @Then("customer details returned to user")
    public void customerDetailsReturnedToUser() {
        scenario.log("===================== RESPONSE ======================\n" +
                response.asPrettyString());

        Assertions.assertEquals(HttpStatus.OK.value(),response.getStatusCode());
        customerDetailsResponse = response.andReturn().as(CustomerDetailsResponse.class);
        Assertions.assertNotNull(customerDetailsResponse.getEncryptedContent());
    }
    @Then("error response returned to user")
    public void errorResponseReturnedToUser() {
        scenario.log("===================== RESPONSE ======================\n" +
                response.asPrettyString());

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(),response.getStatusCode());
        var errorResponse = response.andReturn().as(ErrorResponse.class);
        Assertions.assertNotNull(errorResponse.getErrorCode());
    }

    @And("encrypted content contains customer contacts")
    public void encryptedContentContainsCustomerContacts() throws JsonProcessingException {
        var contacts = decode(customerDetailsResponse.getEncryptedContent(), ContactDetails.class);
        scenario.log("===================== Contact details decrypted ======================\n" +
                objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(contacts));
        Assertions.assertNotNull(contacts.getEmail());
        Assertions.assertNotNull(contacts.getAddress());
    }

    private <T> T decode(String encryptedContent, Class<? extends T> clazz ) throws JsonProcessingException {
        return objectMapper.readerFor(clazz).readValue(
                new String(Base64.getDecoder().decode(encryptedContent))
        );
    }



}
