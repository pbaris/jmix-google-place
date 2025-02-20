package gr.netmechanics.jmix.gplace.util;

import java.util.Collections;
import java.util.Optional;

import gr.netmechanics.jmix.gplace.data.GooglePlaceBaseRef;
import gr.netmechanics.jmix.gplace.data.GooglePlaceInfoRef;
import gr.netmechanics.jmix.gplace.data.GooglePlaceRatingRef;
import gr.netmechanics.jmix.gplace.data.GooglePlaceRef;
import gr.netmechanics.jmix.gplace.rest.dto.Place;
import gr.netmechanics.jmix.gplace.rest.dto.Review;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Panos Bariamis (pbaris)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaceMapper {

    public static GooglePlaceRef toGooglePlaceRef(final Place place) {
        GooglePlaceRef it = new GooglePlaceRef();
        it.setId(place.id());
        it.setDisplayName(place.displayName().text());
        it.setAddress(place.formattedAddress());

        Optional.ofNullable(place.location()).ifPresent(latLng -> {
            it.setLatitude(latLng.latitude());
            it.setLongitude(latLng.longitude());
        });

        return it;
    }

    public static GooglePlaceRatingRef toGooglePlaceRatingRef(final Place place, final String apiKey, final String languageCode) {
        GooglePlaceRatingRef ref = new GooglePlaceRatingRef();
        ref.setRating(place.rating());
        ref.setRatingCount(place.userRatingCount());
        ref.setMapUrl(place.googleMapsUri());

        mapBase(place, ref, apiKey, languageCode);

        Optional.ofNullable(place.reviews()).ifPresentOrElse(
            list -> ref.setReviews(list.stream().map(Review::toGooglePlaceReviewRef).toList()),
//            list -> ref.setReviews(Collections.emptyList()), //TODO remove this
            () -> ref.setReviews(Collections.emptyList()));

        return ref;
    }

    public static GooglePlaceInfoRef toGooglePlaceInfoRef(final Place place, final String apiKey, final String languageCode) {
        GooglePlaceInfoRef ref = new GooglePlaceInfoRef();
        ref.setAddress(place.formattedAddress());
        ref.setPhoneNumber(place.internationalPhoneNumber());
        ref.setMapUrl(place.googleMapsUri());

        mapBase(place, ref, apiKey, languageCode);

        Optional.ofNullable(place.location()).ifPresent(latLng -> {
            ref.setLatitude(latLng.latitude());
            ref.setLongitude(latLng.longitude());
        });

        Optional.ofNullable(place.regularOpeningHours()).ifPresentOrElse(
            oh -> ref.setOpeningHours(oh.weekdayDescriptions()),
            () -> ref.setOpeningHours(Collections.emptyList()));

        return ref;
    }

    private static void mapBase(final Place place, GooglePlaceBaseRef ref, final String apiKey, final String languageCode) {
        ref.setId(place.id());
        ref.setApiKey(apiKey);
        ref.setLanguageCode(languageCode);
        ref.setDisplayName(place.displayName().text());
        ref.setMapUrl(place.googleMapsUri());
        ref.setIconBackgroundColor(place.iconBackgroundColor());
        ref.setIconMaskBaseUri(place.iconMaskBaseUri());
    }
}
