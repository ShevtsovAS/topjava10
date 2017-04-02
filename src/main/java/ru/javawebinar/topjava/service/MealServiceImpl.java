package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.*;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public Meal save(Meal meal) {
        checkNew(meal);
        return repository.save(meal);
    }

    @Override
    public void delete(int userId, int mealId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(userId, mealId), mealId);
    }

    @Override
    public Meal get(int userId, int mealId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(userId, mealId), mealId);
    }

    @Override
    public List<Meal> getAllForUser(int userId) {
        return repository.getAllForUser(userId);
    }

    @Override
    public void update(Meal meal, int userId) {
        checkNotFoundWithId(repository.update(meal, userId), userId);
    }
}