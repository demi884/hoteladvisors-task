package org.wildfly.sample;

import org.wildfly.sample.dto.Address;
import org.wildfly.sample.dto.Contact;

import java.util.Arrays;
import java.util.List;
import java.util.SplittableRandom;
import java.util.UUID;

public final class Utils {
    private Utils() {
    }

    public static List<String> SAMPLE_FIRST_NAMES = Arrays.asList("Ivan", "Petr", "Leo", "Andrey", "Alisa");
    public static List<String> SAMPLE_LAST_NAMES = Arrays.asList("Ivanov", "Petrov", "Leonidze", "Andreyko", "Alisevich");

    public static List<String> SAMPLE_CITY = Arrays.asList("Saint-Petersburg", "Moscow", "New-York", "Kazan", "Novgorod");
    public static List<String> SAMPLE_STREET = Arrays.asList("Pushkina", "Necskaya", "Leonidze", "HoShiMina", "Volinskiy");


    public static String randomId() {
        return UUID.randomUUID().toString().toUpperCase();
    }

    public static String randomOf(List<String> list) {
        return list.get(new SplittableRandom().nextInt(list.size()));
    }




    public static Contact generateRandomContact() {
        return Contact.builder()
                .firstName(randomOf(SAMPLE_FIRST_NAMES))
                .lastName(randomOf(SAMPLE_LAST_NAMES))
                .middleName(new SplittableRandom().nextBoolean() ? "Andreevich" : null)
                .phoneNumber(new SplittableRandom().nextBoolean() ? randomPhoneNumber() : null)
                .build();
    }

    private static String randomPhoneNumber() {
        return String.valueOf(new SplittableRandom().nextInt(100000, 1000000));
    }

    public static Address generateRandomAddress(String contactId) {
        return Address.builder().contactId(contactId).city(randomOf(SAMPLE_CITY)).street(randomOf(SAMPLE_STREET)).house(String.valueOf(new SplittableRandom().nextInt(100))).build();
    }
}
