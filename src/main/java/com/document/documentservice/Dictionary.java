package com.document.documentservice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Dictionary {

    private String dictionaryPath = "./dictionary.json";
    private String documentsFolder = "./documents";
    private static Dictionary dictionaryInstance = null;
	private Map<String, Document> dictionary = new HashMap<>();

    /**
     * Initializes dictionary
     */
    public void initializeDictionary() {
        System.out.println("Initializing Dictionary...");

        checkDirectory();
        File folder = new File(documentsFolder);
        for (final File file : folder.listFiles()) {
            String fileName = FilenameUtils.getBaseName(file.getName());
            String ext = FilenameUtils.getExtension(file.getName());
            String name = fileName.substring(0, fileName.indexOf("_")) + "." + ext;
            String version = fileName.substring(fileName.indexOf("_")+1);
            long fileLength = file.length();
            String filePath = file.getAbsolutePath();

            Document document = new Document(file.getName(), version, fileLength, filePath);

            dictionary.put(name, document);
        }
        this.printDictionary();
        this.writeDictionaryToFile();
    }

    /**
     * Prints the dictionary to console
     */
    public void printDictionary() {
        System.out.println("Printing Dictionary...");

        String dictionaryString = dictionary.keySet().stream()
            .map(key -> key + "=" + dictionary.get(key).toString())
            .collect(Collectors.joining(", ", "{", "}"));

        System.out.println(dictionaryString);
    }

    /**
     * Writes dictionary to file
     */
    public void writeDictionaryToFile() {
        System.out.println("Writing dictionary file...");
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(dictionaryPath), dictionary.values());
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Get dictionary
     * @return dictionary map
     */
    public Map<String, Document> getDictionary() {
        return dictionary;
    }

    /**
     * Set dictionary
     * @param dictionary dictionary map
     */
    public void setDictionary(Map<String, Document> dictionary) {
        this.dictionary = dictionary;
        this.writeDictionaryToFile();
    }

    /**
     * Dictionary Singleton instance
     * @return Dictionary
     */
    public static synchronized Dictionary getInstance()
    {
        if (dictionaryInstance == null)
        dictionaryInstance = new Dictionary();
  
        return dictionaryInstance;
    }

    private void checkDirectory() {
        if (!Files.exists(Paths.get(documentsFolder))) {
            try {
                Files.createDirectories(Paths.get(documentsFolder));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
