package pe.gob.osce.rnp.seg.cfg;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({
    "classpath*:rnp-security-htmlview.xml",
    "classpath*:rnp-security.xml"})
public class XmlConfiguration {

}
