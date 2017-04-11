package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;
public class MealTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final List<Meal> USER_MEALS = Stream.of(
            new Meal(START_SEQ+2, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new Meal(START_SEQ+3, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(START_SEQ+4, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(START_SEQ+5, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(START_SEQ+6, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(START_SEQ+7, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
    ).sorted(Comparator.comparing(Meal::getDateTime).reversed()).collect(Collectors.toList());

    public static final List<Meal> ADMIN_MEALS = Stream.of(
            new Meal(START_SEQ+8, LocalDateTime.of(2015, Month.JUNE, 1, 10, 0), "Завтрак", 500),
            new Meal(START_SEQ+9, LocalDateTime.of(2015, Month.JUNE, 1, 13, 0), "Обед", 600),
            new Meal(START_SEQ+10, LocalDateTime.of(2015, Month.JUNE, 1, 20, 0), "Ужин", 800)
    ).sorted(Comparator.comparing(Meal::getDateTime).reversed()).collect(Collectors.toList());

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>(
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getCalories(), actual.getCalories())
                            && Objects.equals(expected.getDate(), actual.getDate())
                            && Objects.equals(expected.getDateTime(), actual.getDateTime())
                            && Objects.equals(expected.getDescription(), actual.getDescription())
                            && Objects.equals(expected.getTime(), actual.getTime())
                            && Objects.equals(expected.getId(), actual.getId())
                    )
    );
}
