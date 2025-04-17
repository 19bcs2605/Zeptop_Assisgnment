package com.example.csvwebapp.controller;

import com.example.csvwebapp.model.CsvRecord;
import com.example.csvwebapp.service.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.io.IOException;

@RestController
@RequestMapping("/api/csv")
public class CsvController {

    @Autowired
    private CsvService csvService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCsv(@RequestParam MultipartFile file) {
        try {
            csvService.saveCsvFile(file);
            return ResponseEntity.ok("CSV file uploaded successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload CSV file: " + e.getMessage());
        }
    }

    @GetMapping("/records")
    public ResponseEntity<List<CsvRecord>> getAllRecords() {
        List<CsvRecord> records = csvService.getAllRecords();
        return ResponseEntity.ok(records);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateRecord(@RequestBody CsvRecord record) {
        csvService.updateRecord(record);
        return ResponseEntity.ok("Record updated successfully.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable Long id) {
        csvService.deleteRecord(id);
        return ResponseEntity.ok("Record deleted successfully.");
    }

    @PostMapping("/query")
    public ResponseEntity<List<CsvRecord>> runQuery(@RequestBody String query) {
        List<CsvRecord> results = csvService.runQuery(query);
        return ResponseEntity.ok(results);
    }
}