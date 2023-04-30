package com.document.documentservice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DocumentService {

    private final File dictionaryFile = new File("D:/Projects/dictionary.json");

    public List<Document> getAllDocuments() {
        ObjectMapper mapper = new ObjectMapper();
        List<Document> dictionary = new ArrayList<>();
        try {
            dictionary = mapper.readValue(dictionaryFile, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dictionary;
    }
    
    public String getDocument(String documentPath) {
        System.out.println("Getting Document data...");

        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(documentPath)));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return content;
    }
}
