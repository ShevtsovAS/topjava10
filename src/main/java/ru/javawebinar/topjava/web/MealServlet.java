package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    private ConfigurableApplicationContext applicationContext;
    private MealRestController mealRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        applicationContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        mealRestController = applicationContext.getBean(MealRestController.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        if (request.getParameter("filter") != null) {
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");
            LocalDate fromDate = startDate.isEmpty() ? null : LocalDate.parse(startDate);
            LocalDate toDate = endDate.isEmpty() ? null : LocalDate.parse(endDate);
            LocalTime fromTime = startTime.isEmpty() ? null : LocalTime.parse(startTime);
            LocalTime toTime = endTime.isEmpty() ? null : LocalTime.parse(endTime);
            request.setAttribute("meals", mealRestController.getFiltered(AuthorizedUser.id(), fromDate, toDate, fromTime, toTime));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }

        else {
            String id = request.getParameter("id");

            Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                    AuthorizedUser.id(),
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.valueOf(request.getParameter("calories")));

            if (meal.isNew()) mealRestController.create(meal);
            else mealRestController.update(meal, AuthorizedUser.id());
            response.sendRedirect("meals");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                LOG.info("Delete {}", id);
                mealRestController.delete(AuthorizedUser.id(), id);
                response.sendRedirect("meals");
                break;
            case "create":
            case "update":
                final Meal meal = action.equals("create") ?
                        new Meal(AuthorizedUser.id(), LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        mealRestController.get(AuthorizedUser.id(), getId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/meal.jsp").forward(request, response);
                break;
            case "all":
            default:
                LOG.info("getAll");
                request.setAttribute("meals", mealRestController.getAll(AuthorizedUser.id()));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }

    @Override
    public void destroy() {
        super.destroy();
        applicationContext.close();
    }
}