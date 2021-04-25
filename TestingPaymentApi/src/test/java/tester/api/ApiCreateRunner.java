package tester.api;

import com.intuit.karate.junit5.Karate;

public class ApiCreateRunner {
    @Karate.Test
    Karate testApi() {
        return Karate.run("api_create").relativeTo(getClass());
    }   
}
