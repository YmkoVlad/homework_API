package com.api_project;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FakerData {
    @Test
    public void faker() {
        Faker faker = new Faker();
        System.out.println(faker.animal().name());
        System.out.println(faker.name().firstName());
    }

    @Test
    public void dataTest() {
        Date date = new Date();
        date.getTime();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY H:m:s");
        System.out.println(simpleDateFormat.format(date));
    }

    @Test
    public void calendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, 2);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY H:m:s");
        System.out.println(simpleDateFormat.format(calendar.getTime()));
    }
}
