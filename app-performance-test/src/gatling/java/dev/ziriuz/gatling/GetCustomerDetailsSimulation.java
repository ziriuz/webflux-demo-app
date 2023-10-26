package dev.ziriuz.gatling;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class GetCustomerDetailsSimulation extends Simulation {

    private final Iterator<Map<String, Object>> feeder =
            Stream.generate((Supplier<Map<String, Object>>) () -> Collections.singletonMap("request_id", UUID.randomUUID().toString().replace("-",""))
            ).iterator();

    String bodyJsonTemplate = String.format("""
            {"requestId":"REQ-#{request_id}",
             "customerId":"CST-#{request_id}",
             "timestamp":%d
            }             
            """, Instant.now().toEpochMilli());
    ChainBuilder actionApiCall =
        // let's try at max 2 times
        tryMax(2)
            .on(feed(feeder)
                    .exec(
                        http("Get customer details")
                            .get("/customer")
                                .asJson()
                                .body(StringBody(bodyJsonTemplate))
                            .check(
                                    status().is(session -> 200)
                            )
                            .check(
                                    jsonPath("$.requestId").is(session -> "REQ-" + session.get("request_id"))
                            )
                            .check(
                                    jsonPath("$.encryptedContent").notNull()
                            )
                    )
            )
            // if the chain didn't finally succeed, have the user exit the whole scenario
            .exitHereIfFailed();

    HttpProtocolBuilder httpProtocol =
        http.baseUrl("http://localhost:8080");

    ScenarioBuilder actionApi = scenario("get customer details API test").exec(actionApiCall);

    {
        setUp(
                actionApi.injectOpen(
                        rampUsersPerSec(250).to(350).during(Duration.ofSeconds(10)).randomized()
//                        constantUsersPerSec(100).during(Duration.ofSeconds(10)).randomized(),
//                        constantUsersPerSec(150).during(Duration.ofSeconds(10)).randomized(),
//                        constantUsersPerSec(200).during(Duration.ofSeconds(10)).randomized(),
//                        constantUsersPerSec(250).during(Duration.ofSeconds(10)).randomized(),
//                        constantUsersPerSec(300).during(Duration.ofSeconds(10)).randomized(),
//                        constantUsersPerSec(350).during(Duration.ofSeconds(10)).randomized()
                )
        ).protocols(httpProtocol);
    }
}