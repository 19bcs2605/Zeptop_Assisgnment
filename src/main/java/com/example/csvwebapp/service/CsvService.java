package com.example.csvwebapp.service;

import com.example.csvwebapp.model.CsvRecord;
import com.example.csvwebapp.repository.CsvRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {

    private final CsvRepository csvRepository;
    private final String uploadDir = "uploads/";

    public CsvService(CsvRepository csvRepository) {
        this.csvRepository = csvRepository;
    }

    public void saveCsvFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("Failed to store empty file.");
        }

        Path path = Path.of(uploadDir + file.getOriginalFilename());
        Files.copy(file.getInputStream(), path);

        List<CsvRecord> records = parseCsvFile(path);
        csvRepository.saveAll(records);
    }

    private List<CsvRecord> parseCsvFile(Path path) throws IOException {
        List<CsvRecord> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(path)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                CsvRecord record = new CsvRecord();
                // Assuming CsvRecord has appropriate setters for each column
                record.setColumn1(values[0]);
                record.setColumn2(values[1]);
                // Add more columns as needed
                records.add(record);
            }
        }
        return records;
    }

    public List<CsvRecord> getAllRecords() {
        return csvRepository.findAll();
    }

    public void updateRecord(CsvRecord record) {
        csvRepository.save(record);
    }

    public void deleteRecord(Long id) {
        csvRepository.deleteById(id);
    }

    public List<CsvRecord> runQuery(String query) {
        
        throw new UnsupportedOperationException("Unimplemented method 'runQuery'");
    }
}