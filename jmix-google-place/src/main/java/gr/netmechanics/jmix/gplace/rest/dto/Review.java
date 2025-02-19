package gr.netmechanics.jmix.gplace.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import gr.netmechanics.jmix.gplace.data.GooglePlaceReviewRef;

/**
 * @author Panos Bariamis (pbaris)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record Review(
    @JsonProperty("name")
    String name,

    @JsonProperty("text")
    LocalizedText text,

    @JsonProperty("relativePublishTimeDescription")
    String relativePublishTimeDescription,

    @JsonProperty("rating")
    Double rating,

    @JsonProperty("publishTime")
    String publishTime,

    @JsonProperty("authorAttribution")
    AuthorAttribution authorAttribution,

    @JsonProperty("googleMapsUri")
    String googleMapsUri) {

    public GooglePlaceReviewRef toGooglePlaceReviewRef() {
        GooglePlaceReviewRef it = new GooglePlaceReviewRef();
        it.setName(name);
        it.setText(text.text());
        it.setRating(rating);
        it.setPublishTimeRelative(relativePublishTimeDescription);
        it.setAuthorName(authorAttribution.displayName());
        it.setMapUrl(googleMapsUri);
        return it;
    }
}
