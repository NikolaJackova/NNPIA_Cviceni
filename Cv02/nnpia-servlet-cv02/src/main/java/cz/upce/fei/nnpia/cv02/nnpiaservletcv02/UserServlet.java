package cz.upce.fei.nnpia.cv02.nnpiaservletcv02;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "userServlet", value = "/user-servlet")
public class UserServlet extends HttpServlet {
    private String name;

    public void init() {
        name = "";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        if (request.getParameter("name") != null) {
            name = request.getParameter("name");
        }
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + name + "</h1>");
        out.println("<h2>Form</h2>");
        out.println("<form action=\"user-servlet\" method=\"post\">");
        out.println(" <label for=\"name\">Name:</label><br>");
        out.println("<input type=\"text\" id=\"name\" name=\"name\" value=\"" + name + "\"></br></br>");
        out.println("<input type=\"submit\" value=\"Submit\">");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        name = request.getParameter("name");
        response.getWriter().println("<h1>Hello " + name + "!</h1>");
    }

    public void destroy() {
    }
}
