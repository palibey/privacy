package com.example.privacy.Helper;

import com.example.privacy.Model.Chromosome;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Service
public class ChromosomeParser
{
    private final ChromosomeService chromosomeService;

    public ChromosomeParser(ChromosomeService chromosomeService)
    {
        this.chromosomeService = chromosomeService;
    }

    @Transactional
    public void parseFromFile(String filePath, String fileName) throws IOException
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#")) {
                    continue;
                }
                String[] parts = line.split("\t");
                if (parts.length == 4) {
                    chromosomeService.save(new Chromosome(fileName, parts[0], parts[1], parts[2], parts[3]));
                } else {
                    System.err.println("Invalid data format: " + line);
                }
            }
        }
    }

}