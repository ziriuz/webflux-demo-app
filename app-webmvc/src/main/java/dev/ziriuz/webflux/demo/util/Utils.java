package dev.ziriuz.webflux.demo.util;

import com.github.javafaker.Faker;

public class Utils {
    private final static Faker FAKER = Faker.instance();
    public static Faker faker() {
        return FAKER;
    }
}
