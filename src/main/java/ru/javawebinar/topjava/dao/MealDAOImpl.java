package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDAOImpl implements MealDAO {
    private List<Meal> meals;
    private static AtomicInteger id = new AtomicInteger(0);

    public MealDAOImpl() {
        meals = new CopyOnWriteArrayList<>();
        meals.add(new Meal(getId(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        meals.add(new Meal(getId(), LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        meals.add(new Meal(getId(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        meals.add(new Meal(getId(), LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        meals.add(new Meal(getId(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        meals.add(new Meal(getId(), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    public static int getId() { return id.incrementAndGet(); }

    @Override
    public void createMeal(Meal meal) {
        meals.add(meal);
    }

    @Override
    public void updateMeal(Meal meal) {
        //TODO: when we will work with database
    }

    @Override
    public void deleteMeal(int id) {
        meals.remove(getMeal(id));
    }

    @Override
    public List<Meal> getAllMeals() {
        return meals;
    }

    @Override
    public Meal getMeal(int id) {
        return meals.stream()
                .filter(meal -> meal.getId() == id)
                .findAny().orElse(null);
    }
}
