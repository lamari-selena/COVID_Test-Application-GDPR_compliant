package covidtest.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public abstract class JsonString {

    public static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
