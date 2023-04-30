package com.document.documentservice;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Dictionary {
    
    final File folder = new File("D:/Projects/documents");
	Map<String, Document> dictionary = new HashMap<>();

    public void initializeDictionary() {
        System.out.println("Initializing Dictionary...");

        for (final File file : folder.listFiles()) {
            System.out.println("file: " + file.getName());

            String fileName = file.getName();
            String version = fileName.substring(fileName.indexOf("_")+1, fileName.indexOf("."));
            long fileLength = file.length();
            String filePath = file.getPath();

            Document document = new Document(fileName, version, fileLength, filePath);

            dictionary.put(file.getName(), document);
        }

        printDictionary();
        writeDictionaryToFile();
    }

    public void printDictionary() {
        System.out.println("Printing Dictionary...");

        String dictionaryString = dictionary.keySet().stream()
            .map(key -> key + "=" + dictionary.get(key).toString())
            .collect(Collectors.joining(", ", "{", "}"));

        System.out.println(dictionaryString);
    }

    private void writeDictionaryToFile() {
        System.out.println("Writing dictionary file...");
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("D:/Projects/dictionary.json"), dictionary.values());
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
