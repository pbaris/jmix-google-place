package gr.netmechanics.jmix.gplace.data;

import java.util.Locale;
import java.util.Optional;

import gr.netmechanics.jmix.gplace.util.JsonUtil;
import io.jmix.core.metamodel.annotation.DatatypeDef;
import io.jmix.core.metamodel.annotation.Ddl;
import io.jmix.core.metamodel.datatype.Datatype;

/**
 * @author Panos Bariamis (pbaris)
 */
@DatatypeDef(id = "googlePlaceRef", javaClass = GooglePlaceRef.class, defaultForClass = true)
@Ddl("CLOB")
public class GooglePlaceRefDatatype implements Datatype<GooglePlaceRef> {

    @Override
    public String format(final Object value) {
        if (value instanceof GooglePlaceRef gpr) {
            return Optional.ofNullable(JsonUtil.toJson(gpr)).orElse("");
        }

        return "";
    }

    @Override
    public String format(final Object value, final Locale locale) {
        return format(value);
    }

    @Override
    public GooglePlaceRef parse(final String value) {
        return JsonUtil.fromJson(value);
    }

    @Override
    public GooglePlaceRef parse(final String value, final Locale locale) {
        return parse(value);
    }
}
