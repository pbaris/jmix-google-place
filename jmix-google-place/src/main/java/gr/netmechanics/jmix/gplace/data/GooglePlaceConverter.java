package gr.netmechanics.jmix.gplace.data;

import gr.netmechanics.jmix.gplace.util.JsonUtil;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author Panos Bariamis (pbaris)
 */
@Converter(autoApply = true)
public class GooglePlaceConverter implements AttributeConverter<GooglePlace, String> {

    @Override
    public String convertToDatabaseColumn(final GooglePlace gpr) {
        return gpr != null ? JsonUtil.toJson(gpr) : null;
    }

    @Override
    public GooglePlace convertToEntityAttribute(final String dbData) {
        return dbData != null ? JsonUtil.fromJson(dbData) : null;
    }
}
