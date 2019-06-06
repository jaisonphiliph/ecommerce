package servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "simpleServlet", urlPatterns = "/simple",
        initParams = @WebInitParam(name = "inituser", value = "Dev"))
public class SimpleServlet extends HttpServlet {

    public void init() throws ServletException {
        System.out.println("- Executing SimpleServlet init()");
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("- Executing SimpleServlet service()");

        String initUser = this.getServletConfig().getInitParameter("inituser");
        PrintWriter writer = resp.getWriter();
        writer.print("<h5>");
        writer.print("Hello from service, init user: "+ initUser + "<br>"  );
        writer.print("</h5>");

        ServletContext ctx = req.getServletContext();
        if(ctx.getAttribute("count")!=null){
            int count = (Integer) ctx.getAttribute("count");
            ctx.setAttribute("count", count+1);
        }
        else {
            ctx.setAttribute("count",1);
        }
        if(req.getMethod().equals("GET")){
            this.doGet(req, resp);
        }
        else if(req.getMethod().equals("POST")){
            this.doPost(req, resp);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Start SimpleServlet doGet");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println("<h3> Hello from doGet "+ request.getParameter("name") +"</h3>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Start SimpleServlet doPost");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        String fname = request.getParameter("name");
        String lname = request.getParameter("lastname");
        String dept = request.getParameter("dept");
        String [] techList = request.getParameterValues("tech");

        writer.println("<h3> Hello from doPost: </h3>" );
        writer.print("<h5>"  );
        writer.print("Name: "+ fname + "<br>"  );
        writer.print("Last Name: "+ lname + "<br>");
        writer.println("Department: "+ dept + "<br>");
        writer.print("Technologies: ");
        String st = null;
        for(String t : techList) {
            st = (st==null)? t : st+", "+ t;

        }
        writer.print( st );
        writer.println("</h5>");

    }

}