package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name="event-list-servlet", urlPatterns = {"/list", "/browseEvents"})
public class EventListServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final EventService service;

    public EventListServlet(SpringTemplateEngine templateEngine, EventService service) {
        this.templateEngine = templateEngine;
        this.service = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext webContext = new WebContext(webExchange);
        webContext.setVariable("events_list", service.listAll());
        webContext.setVariable("isEmpty", false);
        templateEngine.process("listEvents.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        if(path.equals("/browseEvents")){
            String text = req.getParameter("text");
            int minRating;
            if(!req.getParameter("rating").isEmpty()){
                minRating = Integer.parseInt(req.getParameter("rating"));
            } else {
                minRating = 0;
            }
            IWebExchange webExchange = JakartaServletWebApplication
                    .buildApplication(getServletContext())
                    .buildExchange(req, resp);
            List<Event> events = service.searchEvents(text)
                    .stream()
                    .filter(event -> event.getPopularityScore() > (double) minRating).toList();
            WebContext webContext = new WebContext(webExchange);
            boolean empty = events.isEmpty();
            webContext.setVariable("events_list", events);
            webContext.setVariable("empty", empty);
            templateEngine.process("listEvents.html", webContext, resp.getWriter());
        }else{
            String eventName = req.getParameter("event-name");
            String attendeeName = req.getParameter("attendeeName");
            String attendeeAddress = req.getRemoteAddr();

            Long tickets = Long.parseLong(req.getParameter("numTickets"));


            req.getSession().setAttribute("event-name", eventName);
            req.getSession().setAttribute("attendee-name", attendeeName);
            req.getSession().setAttribute("attendee-address", attendeeAddress);
            req.getSession().setAttribute("ticket-number", tickets);

            resp.sendRedirect("/eventBooking");
        }
    }
}
