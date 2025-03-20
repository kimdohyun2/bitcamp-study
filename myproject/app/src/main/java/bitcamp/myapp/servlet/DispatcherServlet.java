package bitcamp.myapp.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@WebServlet("/app/*")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String requestPath = req.getPathInfo();

            req.getRequestDispatcher(requestPath).include(req, resp);
            Exception exception = (Exception) req.getAttribute("exeption");
            String viewUrl = (String) req.getAttribute("viewUrl");

            resp.setContentType("text/html; charset=UTF-8");
            req.getRequestDispatcher(viewUrl).include(req, resp);

        } catch (Exception e) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);

            RequestDispatcher

        }

    }
}
