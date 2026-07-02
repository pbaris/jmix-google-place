package gr.netmechanics.jmix.gplace.util;

import gr.netmechanics.jmix.gplace.data.GooglePlaceRef;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tools.jackson.databind.json.JsonMapper;

/**
 * @author Panos Bariamis (pbaris)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtil {

    public static String toJson(final GooglePlaceRef gpr) {
        try {
            return new JsonMapper().writeValueAsString(gpr);

        } catch (Exception e) {
            return null;
        }
    }

    public static GooglePlaceRef fromJson(final String json) {
        try {
            return  new JsonMapper().readValue(json, GooglePlaceRef.class);

        } catch (Exception e) {
            return null;
        }
    }
}
