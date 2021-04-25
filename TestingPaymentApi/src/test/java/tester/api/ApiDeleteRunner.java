package tester.api;

import com.intuit.karate.junit5.Karate;

public class ApiDeleteRunner {
    @Karate.Test
    Karate testApi() {
        return Karate.run("api_delete").relativeTo(getClass());
    }   
}
