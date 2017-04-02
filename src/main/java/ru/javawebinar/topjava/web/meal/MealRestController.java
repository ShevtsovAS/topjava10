package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MealRestController {
    private static final Logger LOG = LoggerFactory.getLogger(MealRestController.class);

    private MealService service;

    List<MealWithExceed> getAllForUser(int userId) {
        LOG.info("getAllMeals");
        return MealsUtil.getWithExceeded(service.getAllForUser(userId), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    List<MealWithExceed> getFilteredForUser(int userId, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        LOG.info("getFilteredMeals");
        return MealsUtil.getFilteredWithExceeded(service.getAllForUser(userId), startDate, endDate, startTime, endTime, MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    Meal get(int userId, int mealId) {
        LOG.info("get meal " + mealId);
        return service.get(userId, mealId);
    }

    void delete(int userId, int mealId) {
        LOG.info("delete meal " + mealId);
        service.delete(userId, mealId);
    }

    Meal create(Meal meal) {
        LOG.info("create " + meal);
        return service.save(meal);
    }

    void update(Meal meal, int userId) {
        LOG.info("update meal " + meal);
        service.update(meal, userId);
    }
}