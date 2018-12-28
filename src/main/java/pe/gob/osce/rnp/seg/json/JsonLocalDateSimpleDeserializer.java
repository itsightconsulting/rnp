import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JsonLocalDateSimpleDeserializer extends JsonDeserializer<LocalDateTime> {

    private DateTimeFormatter formatterFull = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private DateTimeFormatter formatterOnlyDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        String dateAsString = p.getText();
        try {
            if (!StringUtils.hasText(dateAsString)) {
                return null;
            }
            if(dateAsString.length()>10){
                return LocalDateTime.parse(dateAsString, formatterFull);
            }
            return LocalDateTime.parse(dateAsString, formatterOnlyDate);
        }catch (Exception pe){
            throw new RuntimeException(pe);
        }
    }
}