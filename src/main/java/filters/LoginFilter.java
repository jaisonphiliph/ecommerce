package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;


    @WebFilter(filterName = "loginFilter", urlPatterns = "/simple")
    public class LoginFilter implements Filter {

        private static Properties prop ;

        public void init(FilterConfig filterConfig) throws ServletException {
            System.out.println("- Executing LoginFilter init()");
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
            String reqPass = request.getParameter("password");
            String dbPass = getProp().getProperty(reqUserName);
            if(!dbPass.equals(reqPass)){
                writer.println("<h3> Incorrect username or password</h3>");
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