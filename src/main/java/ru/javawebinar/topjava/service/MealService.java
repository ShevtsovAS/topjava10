package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface MealService {
    Meal save(Meal meal);

    void delete(int userId, int mealId) throws NotFoundException;

    Meal get(int userId, int mealId) throws NotFoundException;

    List<Meal> getAllForUser(int userId);

    List<Meal> getFiltered(int userId, LocalDate startDate, LocalDate endDate);

    void update(Meal meal, int userId) throws NotFoundException;
}