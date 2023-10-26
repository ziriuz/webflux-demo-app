java -jar app-component-test/build/libs/app-component-test-0.0.1-SNAPSHOT.jar \
-p pretty \
-p json:reports/cucumber-bdd-report.json \
-p html:reports/cucumber-bdd-report.html \
-m \
--glue dev.ziriuz.webflux.demo \
classpath:features