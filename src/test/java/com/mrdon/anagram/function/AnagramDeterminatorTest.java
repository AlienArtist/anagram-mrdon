package com.mrdon.anagram.function;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class AnagramDeterminatorTest {
    @Autowired
    AnagramDeterminator determinator;
    @Test
    void isAnagram() {
        Assertions.assertThat(determinator.isAnagram("asdf123","321fdsa")).isTrue();
        Assertions.assertThat(determinator.isAnagram("","321fdsa")).isFalse();
        Assertions.assertThat(determinator.isAnagram("","")).isTrue();
        Assertions.assertThat(determinator.isAnagram("freddy","teddy")).isFalse();
    }
}