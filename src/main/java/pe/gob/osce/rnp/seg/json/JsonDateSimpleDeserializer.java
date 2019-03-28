package pe.gob.osce.rnp.seg.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonDateSimpleDeserializer extends JsonDeserializer<Date> {

    public static final Logger LOGGER = LogManager.getLogger(JsonDateSimpleDeserializer.class);

    private SimpleDateFormat formatterFull =
            new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private SimpleDateFormat formatterOnlyDate =
            new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String dateAsString = p.getText();
        try {
            if (!StringUtils.hasText(dateAsString)) {
                return null;
            }
            if (dateAsString.length() > 10) {
                return formatterFull.parse(dateAsString);
            }
            return formatterOnlyDate.parse(dateAsString);
        } catch (ParseException pe) {
            LOGGER.info(pe.getMessage());
            return null;
        }
    }

}
