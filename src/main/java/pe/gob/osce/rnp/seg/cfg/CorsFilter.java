package pe.gob.osce.rnp.seg.cfg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)//Very important to everything works correctly
public class CorsFilter extends OncePerRequestFilter {

    @Value("${spring.profiles.active}")
    private String profileActive;

    @Value("${api.bs.route}")
    private String apiBaseHref;

    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String path = req.getRequestURI();
        if (path.contains(apiBaseHref)) {
            String directionAllowOrigin = profileActive.equals("production") ? "https://apps.osce.gob.pe" : "*";
            res.addHeader("Access-Control-Allow-Origin", directionAllowOrigin);
            res.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            res.addHeader("Access-Control-Max-Age", "3600");
            res.addHeader("Access-Control-Allow-Headers", "X-XSRF-TOKEN,X-PINGOTHER,Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
            if (req.getMethod().equals(HttpMethod.OPTIONS.name())) {
                res.setStatus(HttpServletResponse.SC_OK);
            } else {
                chain.doFilter(req, res);
            }
        } else {
            chain.doFilter(req, res);
        }
    }
}
