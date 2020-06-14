package com.mrdon.anagram.generator;

import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;

@Component
public class AnagramGenerator {

    private Set<String> anagrams;

    private void swap(char[] ch, int i, int j)
    {
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
    }

    // Recursive function to generate all permutations of a String
    private  void permutations(char[] ch, int currentIndex)
    {
        if (currentIndex == ch.length - 1) {
            anagrams.add(String.valueOf(ch));
        }

        for (int i = currentIndex; i < ch.length; i++)
        {
            swap(ch, currentIndex, i);
            permutations(ch, currentIndex + 1);
            swap(ch, currentIndex, i);
        }
    }

    public Set<String> getAnagrams(String source) {
        anagrams = new HashSet<>();
        permutations(source.toCharArray(), 0);
        return  anagrams;
    }


}
