package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {
    public static final List<User> USERS = Arrays.asList(
            new User(null, "Alexandr", "mr.alexandr.shevtsov@gmail.com", "12345", Role.ROLE_ADMIN, Role.ROLE_USER),
            new User(null, "Petr", "mr.petr.pupkin@gmail.com", "qwerty", Role.ROLE_USER),
            new User(null, "Vasily", "mr.vasya.pupkin@gmail.com", "qwerty123", Role.ROLE_USER)
    );
}
