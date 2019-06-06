package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

@WebFilter(filterName = "authFilter", urlPatterns = "/simple")
public class AuthFilter implements Filter {

    private static Properties prop ;

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("- Executing AuthFilter init()");
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("auth.properties");
            getProp().load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        System.out.println("- Executing LoginFilter doFilter()");
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html; charset=utf-8");
        String reqUserName = request.getParameter("username");
        String userRole = getProp().getProperty("role_"+reqUserName);
        String roleAuth = getProp().getProperty("auth_"+userRole);
        String urlPattern = ((HttpServletRequest)request).getRequestURL().toString();
        String reqUri = urlPattern.substring("http://localhost:8080/ecommerce".length(),urlPattern.length());
        if(!roleAuth.contains(reqUri)){
            writer.println("<h3> You are not authorized to access page !</h3>");
        }
        else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {

    }

    public static Properties getProp() {
        if(prop==null){
            prop = new Properties();
        }
        return prop;
    }
}