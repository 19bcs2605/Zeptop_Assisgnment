package com.example.csvwebapp.repository;

import com.example.csvwebapp.model.CsvRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CsvRepository extends JpaRepository<CsvRecord, Long> {
    // Additional query methods can be defined here if needed
}