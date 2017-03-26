package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.dao.MealDAOImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);
    private MealDAO dao;

    public MealServlet() {
        super();
        dao = new MealDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("forward to meals");

        String page = "";
        List<MealWithExceed> mealsWithExceeded;
        int id;
        Meal meal;
        String action = req.getParameter("action");
        if (action == null) action = "list";
        else action = action.toLowerCase();

        switch (action) {
            case "list":
                mealsWithExceeded = MealsUtil.getFilteredWithExceeded(dao.getAllMeals(), null, null, 2000);
                req.setAttribute("meals", mealsWithExceeded);
                page = "/meals.jsp";
                break;
            case "delete":
                id = Integer.parseInt(req.getParameter("id"));
                dao.deleteMeal(id);
                mealsWithExceeded = MealsUtil.getFilteredWithExceeded(dao.getAllMeals(), null, null, 2000);
                req.setAttribute("meals", mealsWithExceeded);
                page = "/meals.jsp";
                break;
            case "edit":
                id = Integer.parseInt(req.getParameter("id"));
                meal = dao.getMeal(id);
                req.setAttribute("meal", meal);
                page = "/mealForm.jsp";
                break;
            case "insert":
                page = "/mealForm.jsp";
                break;
        }
        req.getRequestDispatcher(page).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String id = req.getParameter("id");

        String dt = req.getParameter("dt");
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(dt, DateTimeFormatter.ISO_DATE_TIME);
        } catch (Exception ex) {
            dateTime = LocalDateTime.now();
        }

        String description = req.getParameter("description");

        int calories = Integer.parseInt(req.getParameter("calories"));

        if (id == null || id.isEmpty()) {
            Meal meal = new Meal(dateTime, description, calories);
            dao.createMeal(meal);
        }
        else {
            Meal meal = dao.getMeal(Integer.parseInt(id));
            meal.setDateTime(dateTime);
            meal.setDescription(description);
            meal.setCalories(calories);
        }

        List<MealWithExceed> mealsWithExceeded = MealsUtil.getFilteredWithExceeded(dao.getAllMeals(), null, null, 2000);
        req.setAttribute("meals", mealsWithExceeded);
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}
