package gr.netmechanics.jmix.gplace.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.netmechanics.jmix.gplace.data.GooglePlace;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Panos Bariamis (pbaris)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtil {

    public static String toJson(final GooglePlace gpr) {
        try {
            return new ObjectMapper().writeValueAsString(gpr);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Cannot convert to Json", e);
        }
    }

    public static GooglePlace fromJson(final String json) {
        try {
            return  new ObjectMapper().readValue(json, GooglePlace.class);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Cannot convert from Json", e);
        }
    }
}
