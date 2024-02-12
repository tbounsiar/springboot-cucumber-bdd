package org.tb.bdd.cucumber.acceptance;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.tb.bdd.cucumber.ApplicationTests;
import org.tb.bdd.cucumber.cucumber.parameter_type.DataFile;

import javax.sql.DataSource;

@RequiredArgsConstructor
public class AllCrud extends ApplicationTests {

    private final DataSource dataSource;
    private final TestRestTemplate testRestTemplate;
    private final WebTestClient webClient;

    //    private ResponseEntity<String> responseEntity;
    private WebTestClient.ResponseSpec exchange;

    @Given("The data table {sqlFile} exists")
    public void theDataTableAddressSqlExists(DataFile sqlFile) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("feature/data/" + sqlFile.getPath()));
        resourceDatabasePopulator.execute(dataSource);
    }

    @And("The data table {string} contains data from {csvFile}")
    public void theDataTableContainsDataFromCsvFile(String tableName, DataFile csvFile) {
        StringBuilder insert = new StringBuilder("INSERT INTO ");
        insert.append(tableName);
        insert.append(" SELECT * FROM CSVREAD('" + "classpath:feature/data/" + csvFile.getPath() + "', NULL, 'charset=UTF-8 fieldSeparator=|');");
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ByteArrayResource(insert.toString().getBytes()));
        resourceDatabasePopulator.execute(dataSource);
    }

    @When("Run GET {string} request")
    public void runGETWithRequestUrl(String url) {
        request(url, HttpMethod.GET);
    }

    @Then("Expect the response status code to be: {httpStatus}")
    public void expectTheResponseHttpStatusCodeToBeStatus(HttpStatus httpStatus) {
//            assertThat(responseEntity.getStatusCode()).isEqualTo(httpStatus);
        exchange.expectStatus().isEqualTo(httpStatus);

    }

    @And("Expect the response body to be: {jsonFile}")
    public void expectTheResponseBodyToBe(DataFile jsonFile) {
        String content = jsonFile.getContent();
        exchange.expectBody().json(content);
//        assertJsonEquals(jsonFile.getContent(), this.responseEntity.getBody(), Configuration.empty().when(Option.IGNORING_ARRAY_ORDER));
    }

    @ParameterType(".*")
    public DataFile csvFile(String path) {
        return new DataFile(path, DataFile.Type.JSON);
    }

    @ParameterType(".*")
    public DataFile jsonFile(String path) {
        return new DataFile(path, DataFile.Type.JSON);
    }

    @ParameterType(".*")
    public DataFile sqlFile(String path) {
        return new DataFile(path, DataFile.Type.JSON);
    }

    @ParameterType(".*")
    public Class<?> entity(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }

    @ParameterType(".*")
    public HttpStatus httpStatus(String status) {
        return HttpStatus.resolve(Integer.parseInt(status));
    }

    private void request(String url, HttpMethod httpMethod) {
        request(url, httpMethod, null, null);
    }

    private void request(String url, HttpMethod httpMethod, String jsonBody, MultiValueMap<String, String> headers) {
        // set your httpHeaders
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        if (headers != null) {
            httpHeaders.addAll(headers);
        }
//        HttpEntity requestEntity = new HttpEntity(jsonBody, httpHeaders);
//        responseEntity = testRestTemplate.exchange(url, httpMethod, requestEntity, String.class);

        WebTestClient.RequestBodySpec wr = webClient.method(httpMethod)
                .uri(url)
                .headers(h -> h.addAll(httpHeaders));
        if (jsonBody != null) {
            wr.body(BodyInserters.fromValue(jsonBody));
        }

        exchange = wr.exchange();
    }

}
