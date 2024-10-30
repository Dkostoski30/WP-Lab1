package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import mk.finki.ukim.mk.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "event-booking-servlet", urlPatterns = "/eventBooking")
public class EventBookingServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final EventBookingService service;

    public EventBookingServlet(SpringTemplateEngine templateEngine, EventBookingService service) {
        this.templateEngine = templateEngine;
        this.service = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext webContext = new WebContext(webExchange);
        HttpSession session = req.getSession();
        String eventName = session.getAttribute("event-name").toString();
        String attendeeName = session.getAttribute("attendee-name").toString();
        String attendeeAddress = session.getAttribute("attendee-address").toString();
        String tickets = session.getAttribute("ticket-number").toString();
        EventBooking booking = null;
        try{
            booking = service.placeBooking(eventName, attendeeName, attendeeAddress, Integer.parseInt(tickets));
            //bookings.add(booking);
        }catch (RuntimeException e){
            String errorMessage = e.getMessage();
            session.setAttribute("errorMessage", errorMessage);
            resp.sendRedirect("/list");
        }

        webContext.setVariable("booking", booking);
        templateEngine.process("bookingConfirmation.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
