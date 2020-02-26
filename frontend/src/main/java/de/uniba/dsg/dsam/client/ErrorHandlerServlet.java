package de.uniba.dsg.dsam.client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Leo on 10.12.2019
 * @project group7
 * @email bernhard-leo.poss@stud.uni-bamberg.de
 **/

public class ErrorHandlerServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html; charset=utf-8");
        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<html><head><title>Error description</title>    <!-- Bootstrap -->\n" +
                    "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">\n" +
                    "    <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"></script>\n" +
                    "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\n" +
                    "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"></script></head><body>");
            writer.write("<div class=\"container shadow rounded mt-3 p-3\"><h2>An Error occured!</h2>");
            writer.write("<ul>");
            writer.write("<ul><li>Servlet Name:" + req.getAttribute("javax.servlet.error.servlet_name") + "</li>");
            writer.write("<li>Exception Name:" + req.getAttribute("javax.servlet.error.exception").getClass().getName() + "</li>");
            writer.write("<li>Requested URI:" + req.getAttribute("javax.servlet.error.request_uri") + "</li>");
            Throwable t = (Throwable) req.getAttribute("javax.servlet.error.exception");
            writer.write("<li>Exception Message:" + t.getMessage() + "</li>");
            writer.write("</ul>");
            writer.write("</ul>");
            writer.write("</html></body>");
        }
    }
	
	// Method to handle POST method request.
	   public void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
	      
	      doGet(request, response);
	   }
}
