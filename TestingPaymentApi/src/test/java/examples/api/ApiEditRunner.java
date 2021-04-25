package examples.api;

import com.intuit.karate.junit5.Karate;

public class ApiEditRunner {
    @Karate.Test
    Karate testApi() {
        return Karate.run("api_edit").relativeTo(getClass());
    }   
}
