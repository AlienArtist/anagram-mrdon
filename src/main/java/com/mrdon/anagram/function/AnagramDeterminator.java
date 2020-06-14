package com.mrdon.anagram.function;

import org.springframework.stereotype.Component;

@Component

public class AnagramDeterminator {
    public final Boolean isAnagram(String string1, String string2) {
        return sortStringCaseInsensitive(string1).equalsIgnoreCase(sortStringCaseInsensitive(string2));
    }

    private String sortStringCaseInsensitive(String s) {
        return s.toUpperCase().chars().sorted()
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();

    }
}
