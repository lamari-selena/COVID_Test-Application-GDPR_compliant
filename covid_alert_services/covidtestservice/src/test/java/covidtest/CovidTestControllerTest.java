package covidtest;

import static covidtest.Utils.JsonString.asJsonString;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import covidtest.controllers.CovidTestController;
import covidtest.models.CovidTest;
import covidtest.services.CovidTestService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.Date;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CovidTestController.class)
public class CovidTestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CovidTestService service;

    /*
    @Test
    @DisplayName("GET /covidtests/0 - Found")
    void testGetCovidTestByIdFound() throws Exception {

        // Set up the mock service
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        CovidTest mockCovidTest = new CovidTest("PCR", "positif", 72, timestamp, "1");
        System.out.println(mockCovidTest);
        doReturn(mockCovidTest).when(service).findCovidTestById(Long.valueOf("0"));

        // Execute the Get Request
        mockMvc.perform(get("/covidtests/{id}",0))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                //Validate the response code and content type
                //.andExpect(header().string(HttpHeaders.ETAG, "\"0\""))
                //.andExpect(header().string(HttpHeaders.LOCATION, "/covidtests/0"))

                //Validate the returned fields
                .andExpect(jsonPath("$.length()", is(5)))
                .andExpect(jsonPath("$.covidtest_type", is("PCR")))
                .andExpect(jsonPath("$.covidtest_result", is("positif")))
                .andExpect(jsonPath("$.covidtest_valid_duration", is(72)))
                .andExpect(jsonPath("$.user_id", is("1")));
    }
    */


//    @Test
//    @DisplayName("GET /covidtests/0 - Not Found")
//    void testGetCovidTestByIdNotFound() throws Exception {
//
//        // Set up the mock service
//        when(service.findCovidTestById(Long.valueOf("0"))).thenReturn(null);
//
//
//        // Execute the Get Request
//        mockMvc.perform(get("/covidtests/{id}",0))
//                .andExpect(status().isNotFound());
//    }

    /*
    @Test
    @DisplayName("Post /covidtests - Success")
    void testCreateCovidTest() throws Exception {

        // Set up the mock service
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        System.out.println("###########");
        System.out.println(timestamp.toString());
        System.out.println("###########");
        CovidTest mockCovidTest = new CovidTest("PCR", "positif", 72, timestamp, "1");
        CovidTest postCovidTest = new CovidTest("PCR", "positif", 72, timestamp, "1");
        doReturn(mockCovidTest).when(service).addCovidTest(any());

        // Execute the Get Request
        mockMvc.perform(post("/covidtests")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(postCovidTest)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Validate the response code and content type
                //.andExpect(header().string(HttpHeaders.ETAG, "\"0\""))
                //.andExpect(header().string(HttpHeaders.LOCATION, "/covidtests/0"))

                //Validate the returned fields
                .andExpect(jsonPath("$.length()", is(5)))
                .andExpect(jsonPath("$.covidtest_type", is("PCR")))
                .andExpect(jsonPath("$.covidtest_result", is("positif")))
                .andExpect(jsonPath("$.covidtest_valid_duration", is(72)))
                .andExpect(jsonPath("$.user_id", is("1")));
    }

     */

//    @Test
//    @DisplayName("Put /covidtests/0 - Success")
//    void testPutCovidTestSuccess() throws Exception {
//
//        // Set up the mock service
//        Date date = new Date();
//        Timestamp timestamp = new Timestamp(date.getTime());
//        //CovidTest putCovidTest = new CovidTest("PCR", "positif", 72, timestamp, "1");
//        CovidTest mockCovidTest = new CovidTest("PCR", "positif", 72, timestamp, "1");
//
//        doReturn(mockCovidTest).when(service).findCovidTestById(Long.valueOf("0"));
//        //doReturn(true).when(service).updateCovidTest(Long.valueOf("0"),any());
//
//        mockCovidTest.setCovidtest_result("négatif");
//
//        // Execute the Get Request
//        mockMvc.perform(put("/covidtests/{id}",0)
//                        //.contentType(MediaType.APPLICATION_JSON)
//                        //.header(HttpHeaders.IF_MATCH, 0)
//                        .content(asJsonString(mockCovidTest)))
//
//                //Validate the response code and content type
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//
//                //Validate the headers
//                //.andExpect(header().string(HttpHeaders.ETAG, "\"0\""))
//                //.andExpect(header().string(HttpHeaders.LOCATION, "/covidtests/0"))
//
//                //Validate the returned fields
//                .andExpect(jsonPath("$.length()", is(5)))
//                .andExpect(jsonPath("$.covidtest_type", is("PCR")))
//                .andExpect(jsonPath("$.covidtest_result", is("négatif")))
//                .andExpect(jsonPath("$.covidtest_valid_duration", is(72)))
//                .andExpect(jsonPath("$.user_id", is("1")));
//    }


}
