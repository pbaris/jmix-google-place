package gr.netmechanics.jmix.gplace.util;

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

        } catch (Exception e) {
            return null;
        }
    }

    public static GooglePlace fromJson(final String json) {
        try {
            return  new ObjectMapper().readValue(json, GooglePlace.class);

        } catch (Exception e) {
            return null;
        }
    }
}
