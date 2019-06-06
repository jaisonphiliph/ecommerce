package servlets;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

    @WebServlet(urlPatterns = "/testsession")
    public class SimpleSessionServlet extends HttpServlet {

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
            // Context Object
            ServletContext ctx = request.getServletContext();
            if(ctx.getAttribute("count")!=null){
                int count = (Integer) ctx.getAttribute("count");
                ctx.setAttribute("count", count+1);
            }
            else {
                ctx.setAttribute("count",1);
            }

            System.out.println("Start ProductsServlets doGet");
            response.setContentType("text/html; charset=utf-8");
            PrintWriter writer = response.getWriter();

            String name = request.getParameter("name");

            // Session Object
            HttpSession session = request.getSession();
            if(name!= null && !name.equals("")){
                session.setAttribute("savedname", name);
            }
            writer.print("<h3> Hello from doPost, request Param name value: "+name );
            writer.print("<h3> Request Param name value: "+name );
            writer.print(" Session attribute name value: "+(String)session.getAttribute("savedname") );
            writer.print(" Context attribute count value: "+(Integer)ctx.getAttribute("count") );
            writer.print("</h3>");

        }


    }

