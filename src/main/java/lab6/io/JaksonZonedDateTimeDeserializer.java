package lab6.io;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.core.JsonParser;

import java.time.ZonedDateTime;
import java.io.IOException;

public class JaksonZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {

    @Override
    public ZonedDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        return ZonedDateTime.parse(jsonParser.getText(), DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }
}
