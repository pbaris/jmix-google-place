package gr.netmechanics.jmix.gplace.rest.dto;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import gr.netmechanics.jmix.gplace.data.GooglePlaceInfoRef;
import gr.netmechanics.jmix.gplace.data.GooglePlaceRatingRef;
import gr.netmechanics.jmix.gplace.data.GooglePlaceRef;

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

    @JsonProperty("regularOpeningHours")
    OpeningHours regularOpeningHours,

    @JsonProperty("reviews")
    List<Review> reviews) {

    public GooglePlaceRef toGooglePlaceRef() {
        GooglePlaceRef it = new GooglePlaceRef();
        it.setId(id);
        it.setDisplayName(displayName.text());
        it.setAddress(formattedAddress);

        Optional.ofNullable(location).ifPresent(latLng -> {
            it.setLatitude(latLng.latitude());
            it.setLongitude(latLng.longitude());
        });

        return it;
    }

    public GooglePlaceRatingRef toGooglePlaceRatingRef() {
        GooglePlaceRatingRef it = new GooglePlaceRatingRef();
        it.setId(id);
        it.setDisplayName(displayName.text());
        it.setRating(rating);
        it.setRatingCount(userRatingCount);
        it.setMapUrl(googleMapsUri);

        Optional.ofNullable(reviews).ifPresentOrElse(
            list -> it.setReviews(list.stream().map(Review::toGooglePlaceReviewRef).toList()),
//            list -> it.setReviews(Collections.emptyList()), //TODO remove this
            () -> it.setReviews(Collections.emptyList()));

        return it;
    }

    public GooglePlaceInfoRef toGooglePlaceInfoRef() {
        GooglePlaceInfoRef it = new GooglePlaceInfoRef();
        it.setId(id);
        it.setDisplayName(displayName.text());
        it.setAddress(formattedAddress);
        it.setPhoneNumber(internationalPhoneNumber);
        it.setMapUrl(googleMapsUri);

        Optional.ofNullable(location).ifPresent(latLng -> {
            it.setLatitude(latLng.latitude());
            it.setLongitude(latLng.longitude());
        });

        Optional.ofNullable(regularOpeningHours).ifPresentOrElse(
            oh -> it.setOpeningHours(oh.weekdayDescriptions()),
            () -> it.setOpeningHours(Collections.emptyList()));

        return it;
    }
}
