package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

    @WebServlet(urlPatterns = "/login")
    public class LoginServlets extends HttpServlet {

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
            System.out.println("3. Executing LoginServlet doPost()");
            response.setContentType("text/html; charset=utf-8");
            PrintWriter writer = response.getWriter();
            String fname = request.getParameter("username");
            writer.println("<h3> Welcome "+ fname+" !</h3>" );
        }

    }
