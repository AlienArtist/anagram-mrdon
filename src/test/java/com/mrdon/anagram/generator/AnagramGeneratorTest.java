package com.mrdon.anagram.generator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class AnagramGeneratorTest {
    public long factorial(int n) {
        return LongStream.rangeClosed(1, n)
                .reduce(1, (long x, long y) -> x * y);
    }

    @Autowired
    private AnagramGenerator anagramGenerator;
    @Test
    void getAnagrams() {
        // For an input string of length n that has no repeating characters the output size should be n! inclusive AND
        // all entries must be unique (we could also test that each output string contains characters only from the
        // input string)
        String input = "abcde";
        Collection<String> result = anagramGenerator.getAnagrams(input);
        // ..checking size
        Assertions.assertThat(result.size()).isEqualTo(factorial(input.length()));
        // .. checking uniqueness -- put all elements in a set
        HashSet<String> aSet = new HashSet<>(result);
        Assertions.assertThat(aSet.size()).isEqualTo(factorial(input.length()));

        // For an input string of length n that has repeating characters  the output size will be less than n! and all
        // entries unique

    }
}