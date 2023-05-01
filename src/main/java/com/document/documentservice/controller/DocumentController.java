package com.document.documentservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.document.documentservice.Document;
import com.document.documentservice.DocumentService;

@RestController
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    /**
     * HTTP GET request to retrive all documents
     * @return list of documents
     */
    @GetMapping("/documents")
    public List<Document> getAllDocuments() {
        return documentService.getAllDocuments();
    }

    /**
     * HTTP GET request to retrieve a single document
     * @param documentName document name
     * @return document string content
     */
    @GetMapping("/document")
    public String getDocument(@RequestParam(value = "name") String documentName) {
        return documentService.getDocument(documentName);
    }

    /**
     * HTTP POST request to upload a new document
     * @param document document file
     */
    @PostMapping("/document")
    public void uploadDocument(@RequestBody MultipartFile document) {
        documentService.uploadDocument(document);
    }

}