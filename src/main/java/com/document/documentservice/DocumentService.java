package com.document.documentservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DocumentService {

    @Value("${dictionary.path}")
    private String dictionaryPath;

    @Value("${documents.folder.path}")
    private String documentsFolder;

    /**
     * Gets All Documents
     * @return list of documents
     */
    public List<Document> getAllDocuments() {
        ObjectMapper mapper = new ObjectMapper();
        List<Document> dictionary = new ArrayList<>();
        File dictionaryFile = new File(dictionaryPath);
        try {
            dictionary = mapper.readValue(dictionaryFile, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dictionary;
    }
    
    /**
     * Get document
     * @param documentName  document to retrieve
     * @return document content
     */
    public String getDocument(String documentName) {
        System.out.println("Getting Document data...");

        String documentPath = documentsFolder + documentName;
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(documentPath)));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return content;
    }

    /**
     * Uploads a new document and saves it to the dictionary
     * @param multipartFile document file
     */
    public void uploadDocument(MultipartFile multipartFile) {

        Dictionary dictionary = Dictionary.getInstance();
        Map<String, Document> dictionaryMap = dictionary.getDictionary();

        File file;
        String fileName = multipartFile.getOriginalFilename();
        String version = "1";

        dictionary.printDictionary();
        Document document = dictionaryMap.get(fileName);

        if (document != null) {
            // update version
            version = document.getVersion();
            version = String.valueOf(Integer.valueOf(version) + 1);
        }

        String fileNameWithVersion = this.appendVersion(fileName, version);
        file = new File(documentsFolder + fileNameWithVersion);

        // write to file
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(multipartFile.getBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        long fileLength = file.length();
        String filePath = file.getAbsolutePath();
        document = new Document(fileNameWithVersion, version, fileLength, filePath);

        // update dictionary
        dictionaryMap.put(fileName, document);
        dictionary.writeDictionaryToFile();
        dictionary.printDictionary();
    }

    /**
     * Appends version to document name
     * @param fileName  document name
     * @param version   document version
     * @return new document name with version
     */
    private String appendVersion(String fileName, String version) {
        return FilenameUtils.getBaseName(fileName) + "_" + version + "." + FilenameUtils.getExtension(fileName);
    }

}
