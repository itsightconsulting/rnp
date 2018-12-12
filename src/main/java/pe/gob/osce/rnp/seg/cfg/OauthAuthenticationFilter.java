package pe.gob.osce.rnp.seg.cfg;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OauthAuthenticationFilter implements AuthenticationEntryPoint
{
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2) throws IOException, ServletException
    {
        /*System.out.println(arg2.getLocalizedMessage());
        System.out.println(arg2.getCause());
        System.out.println("***********************************************");
        for (Enumeration<?> e = request.getHeaderNames(); e.hasMoreElements();) {
            String nextHeaderName = (String) e.nextElement();
            String headerValue = request.getHeader(nextHeaderName);
            System.out.println(">>: "+headerValue);
        }*/

        /*final Map<String, Object> mapBodyException = new HashMap<>();

        mapBodyException.put("error"    , arg2.getLocalizedMessage());
        mapBodyException.put("path"     , request.getServletPath());
        mapBodyException.put("timestamp", (new Date()));

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), mapBodyException);*/
        response.sendRedirect(request.getContextPath()+"/login.html");
    }
}
