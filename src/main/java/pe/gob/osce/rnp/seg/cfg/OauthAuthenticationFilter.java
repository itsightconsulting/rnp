package pe.gob.osce.rnp.seg.cfg;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class OauthAuthenticationFilter implements AuthenticationEntryPoint
{
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2) throws IOException, ServletException
    {

        final Map<String, Object> mapBodyException = new HashMap<>();

        mapBodyException.put("error"    , arg2.getLocalizedMessage());
        mapBodyException.put("path"     , request.getServletPath());
        mapBodyException.put("timestamp", LocalDateTime.now());

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), mapBodyException);
    }
}
