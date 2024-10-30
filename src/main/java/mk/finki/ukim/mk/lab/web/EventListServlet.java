package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name="event-list-servlet", urlPatterns = {"/list"})
public class EventListServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final EventService service;

    public EventListServlet(SpringTemplateEngine templateEngine, EventService service) {
        this.templateEngine = templateEngine;
        this.service = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext webContext = new WebContext(webExchange);
        //dopolnitelno
        String username = "";
        if(session.getAttribute("username") == null){
            resp.sendRedirect("/login");
        }else{
            username = session.getAttribute("username").toString();
        }
        //dopolnitelno

        webContext.setVariable("events_list", service.listAll());
        webContext.setVariable("isEmpty", false);
        String errorMessage = (session.getAttribute("errorMessage") != null) ? session.getAttribute("errorMessage").toString() : "";

        if(errorMessage.isEmpty()){
            webContext.setVariable("hasError", false);
        }else{
            webContext.setVariable("hasError", true);
        }
        session.removeAttribute("errorMessage");
        webContext.setVariable("errorMessage", errorMessage);
        templateEngine.process("listEvents.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getQueryString();
        HttpSession session = req.getSession();
        if(query.contains("form=2")){
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
            webContext.setVariable("events_list", events);
            templateEngine.process("listEvents.html", webContext, resp.getWriter());
        }else{
            String eventName = req.getParameter("event-name") == null ? "" : req.getParameter("event-name");
            String attendeeName = session.getAttribute("username").toString();
            String attendeeAddress = req.getRemoteAddr();
            String ticketsParam = req.getParameter("numTickets");
            long tickets = 0L;
            if (ticketsParam != null && !ticketsParam.isEmpty()) {
                tickets = Long.parseLong(ticketsParam);
            }
            req.getSession().setAttribute("event-name", eventName);
            req.getSession().setAttribute("attendee-name", attendeeName);
            req.getSession().setAttribute("attendee-address", attendeeAddress);
            req.getSession().setAttribute("ticket-number", tickets);
            resp.sendRedirect("/eventBooking");
        }
    }
}
