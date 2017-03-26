package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDAO {
    void createMeal(Meal meal);
    void updateMeal(Meal meal);
    void deleteMeal(int id);
    List<Meal> getAllMeals();
    Meal getMeal(int id);
}
