package examples.api;

import com.intuit.karate.junit5.Karate;

class ApiRunner {
    
    @Karate.Test
    Karate testApi() {
        return Karate.run("api").relativeTo(getClass());
    }    

}
