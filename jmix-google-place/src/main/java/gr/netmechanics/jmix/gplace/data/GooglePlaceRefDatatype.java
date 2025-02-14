package gr.netmechanics.jmix.gplace.data;

import java.text.ParseException;
import java.util.Locale;

import io.jmix.core.metamodel.annotation.DatatypeDef;
import io.jmix.core.metamodel.annotation.Ddl;
import io.jmix.core.metamodel.datatype.Datatype;
import org.springframework.lang.NonNull;

/**
 * @author Panos Bariamis (pbaris)
 */
@DatatypeDef(id = "googlePlaceRef", javaClass = GooglePlaceRef.class, defaultForClass = true)
@Ddl("CLOB")
public class GooglePlaceRefDatatype implements Datatype<GooglePlaceRef> {

    @NonNull
    @Override
    public String format(final Object value) {
        if (value instanceof GooglePlaceRef gpr) {
            return GooglePlaceRef.toJson(gpr);
        }

        return "";
    }

    @NonNull
    @Override
    public String format(final Object value, @NonNull final Locale locale) {
        return format(value);
    }

    @Override
    public GooglePlaceRef parse(final String value) throws ParseException {
        return GooglePlaceRef.fromJson(value);
    }

    @Override
    public GooglePlaceRef parse(final String value, @NonNull final Locale locale) throws ParseException {
        return parse(value);
    }
}
