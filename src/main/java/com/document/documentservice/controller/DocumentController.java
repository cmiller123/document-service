package com.document.documentservice.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.document.documentservice.Document;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class DocumentController {

    final File dictionaryFile = new File("D:/Projects/dictionary.json");

    @GetMapping("/")
    public String helloWorld() {

        return "hello world";
    }

    @GetMapping("/documents")
    public Map<String, Document> getAllDocuments() {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Document> dictionary = new HashMap<>();
        try {
            dictionary = mapper.readValue(dictionaryFile, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dictionary;
    }
}