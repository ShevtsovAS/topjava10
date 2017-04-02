package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {
    private static final Logger LOG = LoggerFactory.getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public List<MealWithExceed> getAllForUser(int userId) {
        LOG.info("getAllMeals");
        return MealsUtil.getWithExceeded(service.getAllForUser(userId), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public List<MealWithExceed> getFilteredForUser(int userId, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        LOG.info("getFilteredMeals");
        return MealsUtil.getFilteredWithExceeded(service.getAllForUser(userId), startDate, endDate, startTime, endTime, MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public Meal get(int userId, int mealId) {
        LOG.info("get meal " + mealId);
        return service.get(userId, mealId);
    }

    public void delete(int userId, int mealId) {
        LOG.info("delete meal " + mealId);
        service.delete(userId, mealId);
    }

    public Meal create(Meal meal) {
        LOG.info("create " + meal);
        return service.save(meal);
    }

    public void update(Meal meal, int userId) {
        LOG.info("update meal " + meal);
        service.update(meal, userId);
    }
}