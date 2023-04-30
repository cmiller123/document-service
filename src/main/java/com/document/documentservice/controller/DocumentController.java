package com.document.documentservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.document.documentservice.Document;
import com.document.documentservice.DocumentService;

@RestController
public class DocumentController {

    private final DocumentService documentService = new DocumentService();

    @GetMapping("/documents")
    public List<Document> getAllDocuments() {
        return documentService.getAllDocuments();
    }

    @GetMapping("/document")
    public String getDocument(@RequestParam(value = "path") String documentPath) {
        return documentService.getDocument(documentPath);
    }
}