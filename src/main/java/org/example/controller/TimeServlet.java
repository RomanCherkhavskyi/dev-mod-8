package org.example.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String timezone = req.getParameter("timezone");
        LocalDateTime currentTime ;

        if (timezone != null && !timezone.isEmpty()) {
            if (timezone.contains(" ")){
                timezone = timezone.replace(" ","+");
            }
            ZoneId zone = ZoneId.of(timezone);
            currentTime = LocalDateTime.now(zone);
        } else {
            currentTime = LocalDateTime.now();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);

        out.println("<html><head><title>Current Time</title></head>");
        out.println("<body><h1>Current Time</h1>");
        out.println("<p>Current Time: " + formattedTime + " " + timezone + "</p>");
        out.println("</body></html>");
    }
}