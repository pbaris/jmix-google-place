package gr.netmechanics.jmix.gplace.rest.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Panos Bariamis (pbaris)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record Place(
    @JsonProperty("id")
    String id,

    @JsonProperty("displayName")
    LocalizedText displayName,

    @JsonProperty("formattedAddress")
    String formattedAddress,

    @JsonProperty("location")
    LatLng location,

    @JsonProperty("rating")
    Double rating,

    @JsonProperty("userRatingCount")
    Integer userRatingCount,

    @JsonProperty("googleMapsUri")
    String googleMapsUri,

    @JsonProperty("internationalPhoneNumber")
    String internationalPhoneNumber,

    @JsonProperty("iconMaskBaseUri")
    String iconMaskBaseUri,

    @JsonProperty("iconBackgroundColor")
    String iconBackgroundColor,

    @JsonProperty("regularOpeningHours")
    OpeningHours regularOpeningHours,

    @JsonProperty("reviews")
    List<Review> reviews) {

}
