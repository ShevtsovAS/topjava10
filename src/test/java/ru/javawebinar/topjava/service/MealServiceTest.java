package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {
    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void get() throws Exception {
        Meal meal = service.get(USER_MEALS.get(0).getId(), USER_ID);
        MATCHER.assertEquals(USER_MEALS.get(0), meal);
        meal = service.get(ADMIN_MEALS.get(0).getId(), ADMIN_ID);
        MATCHER.assertEquals(ADMIN_MEALS.get(0), meal);
    }

    @Test
    public void delete() throws Exception {
        service.delete(USER_MEALS.get(0).getId(), USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(
                USER_MEALS.get(1),
                USER_MEALS.get(2),
                USER_MEALS.get(3),
                USER_MEALS.get(4),
                USER_MEALS.get(5)),
                service.getAll(USER_ID)
        );
    }

    @Test
    public void getBetweenDates() throws Exception {
        LocalDate startDate = LocalDate.of(2015, Month.MAY, 30);
        LocalDate endDate = LocalDate.of(2015, Month.MAY, 30);
        List<Meal> mealsFromDb = service.getBetweenDates(startDate, endDate, USER_ID);
        List<Meal> testMeals = USER_MEALS.stream()
                .filter(meal -> DateTimeUtil.isBetween(meal.getDate(), startDate, endDate))
                .sorted(Comparator.comparing(Meal::getDate).reversed())
                .collect(Collectors.toList());
        MATCHER.assertCollectionEquals(mealsFromDb, testMeals);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        LocalDateTime startTime = LocalDateTime.of(2015, Month.MAY, 31, 7, 0);
        LocalDateTime endTime = LocalDateTime.of(2015, Month.MAY, 31, 15, 0);
        List<Meal> mealsFromDb = service.getBetweenDateTimes(startTime, endTime, USER_ID);
        List<Meal> testMeals = USER_MEALS.stream()
                .filter(meal -> DateTimeUtil.isBetween(meal.getDateTime(), startTime, endTime))
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
        MATCHER.assertCollectionEquals(mealsFromDb, testMeals);
    }

    @Test
    public void getAll() throws Exception {
        MATCHER.assertCollectionEquals(USER_MEALS, service.getAll(USER_ID));
    }

    @Test
    public void update() throws Exception {
        Meal meal = service.get(USER_MEALS.get(0).getId(), USER_ID);
        meal.setDescription("new description");
        service.update(meal, USER_ID);
        MATCHER.assertEquals(meal, service.get(meal.getId(), USER_ID));
    }

    @Test
    public void save() throws Exception {
        Meal newMeal = new Meal(LocalDateTime.now(), "new meal", 1333);
        Meal created = service.save(newMeal, ADMIN_ID);
        newMeal.setId(created.getId());
        List<Meal> newMealList = Arrays.asList(
                ADMIN_MEALS.get(0),
                ADMIN_MEALS.get(1),
                ADMIN_MEALS.get(2),
                newMeal).stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
        MATCHER.assertCollectionEquals(newMealList, service.getAll(ADMIN_ID));
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() throws Exception {
        service.delete(USER_MEALS.get(0).getId(), ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundGet() throws Exception {
        service.get(ADMIN_MEALS.get(0).getId(), USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundUpdate() throws Exception {
        Meal meal = service.get(USER_MEALS.get(0).getId(), USER_ID);
        meal.setDescription("new description");
        service.update(meal, ADMIN_ID);
    }

}