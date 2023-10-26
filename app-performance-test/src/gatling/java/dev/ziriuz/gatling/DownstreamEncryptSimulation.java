package dev.ziriuz.gatling;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class DownstreamEncryptSimulation extends Simulation {

    private final Iterator<Map<String, Object>> feeder =
            Stream.generate((Supplier<Map<String, Object>>) () -> Collections.singletonMap("request_id", UUID.randomUUID().toString().replace("-",""))
            ).iterator();

    String bodyJsonEncryptRequest = """
            {"requestId":"#{request_id}",
             "content":"Demo-action-#{request_id}"}
            """;
    ChainBuilder encryptApiCall =
        // let's try at max 2 times
        tryMax(2)
            .on(feed(feeder)
                    .exec(
                            http("EncryptRequest")
                                    .post("/encrypt")
                                    .asJson()
                                    .body(StringBody(bodyJsonEncryptRequest))
                                    .check(
                                            status().is(session -> 200)
                                    )
                                    .check(
                                            jsonPath("$.requestId").is(session -> session.get("request_id"))
                                    )
                    )
            )
            // if the chain didn't finally succeed, have the user exit the whole scenario
            .exitHereIfFailed();

    HttpProtocolBuilder httpProtocol =
        http.baseUrl("http://localhost:9000");

    ScenarioBuilder actionApi = scenario("Encrypt request API test").exec(encryptApiCall);

    {
        setUp(
                actionApi.injectOpen(
                        constantUsersPerSec(500).during(Duration.ofSeconds(10)).randomized()
                )
        ).protocols(httpProtocol);
    }
}