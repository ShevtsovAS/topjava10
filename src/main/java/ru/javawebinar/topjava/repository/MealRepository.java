package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public interface MealRepository {
    Meal save(Meal Meal);

    // false if not found
    boolean update(Meal meal, int userId);

    // false if not found
    boolean delete(int userId, int id);

    // null if not found
    Meal get(int userId, int id);


    List<Meal> getAllForUser(int userId);

    List<Meal> getFiltered(int userId, LocalDate startDate, LocalDate endDate);
}
