package com.mrdon.anagram.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrdon.anagram.function.AnagramDeterminator;
import com.mrdon.anagram.generator.AnagramGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;

@RestController

public class FrontEndController {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    AnagramDeterminator anagramDeterminator;
    @Autowired
    AnagramGenerator generator;
    // this is just for the challenge test...
    public static String ARE_ANAGRAMS_TRUE = "{areAnagrams: true}";
    public static String ARE_ANAGRAMS_FALSE = "{areAnagrams: false}";

    @RequestMapping(path="/anagrams/{string1}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAnagrams(@NonNull @PathVariable String string1) throws JsonProcessingException {
        if (!isValid(string1)) {
            return new ResponseEntity<>(
                    "Invalid String format. Only Alphanumeric input allowed. getAnagrams web",
                    HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok("{anagrams: "+ objectMapper.writeValueAsString(generator.getAnagrams(string1))+ "}");
    }

    @RequestMapping(path="/anagrams/{string1}/{string2}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> isAnagramEP(@NonNull @PathVariable String string1, @NonNull @PathVariable String string2) {
        if (!isValid(string1) || !isValid(string2)) {
            return new ResponseEntity<>(
                    "Invalid String format. Only Alphanumeric input allowed. isAnagram web",
                    HttpStatus.BAD_REQUEST);
        }
        if (anagramDeterminator.isAnagram(string1,string2)) {
            return ResponseEntity.ok(FrontEndController.ARE_ANAGRAMS_TRUE);
        }
        return  ResponseEntity.ok(FrontEndController.ARE_ANAGRAMS_FALSE);
    }


    public  boolean isValid(String input) {
        return input.chars()
                .allMatch(s-> Character.isLetterOrDigit(s));

    }
}
