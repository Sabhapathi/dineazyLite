package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    public static RequestSpecification req;
    public static ResponseSpecification resp;

    public RequestSpecification RequestSpecification() throws IOException {

        if(req==null) {
            PrintStream log = new PrintStream(new FileOutputStream("output.log"));
            req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return (req);
        }
        return (req);
    }

    public ResponseSpecification ResponseSpecification() throws IOException {

            resp = new ResponseSpecBuilder().expectStatusCode(201)
//                    .addFilter(RequestLoggingFilter.logRequestTo(log))
//                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .expectContentType(ContentType.JSON).build();
            return (resp);

    }




    public static String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src//test//java//resources//global.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }

    public String getJsonPathValue(Response response, String key)
    {
        String resp=response.asString();
        JsonPath js = new JsonPath(resp);
        return(js.get(key).toString());
    }
}
