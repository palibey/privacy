package com.example.privacy.Helper;

import com.example.privacy.Model.GeneFile;
import org.springframework.stereotype.Service;

@Service
public class GeneFileService
{
    private final GeneFileRepository geneFileRepository;

    public GeneFileService(GeneFileRepository geneFileRepository)
    {
        this.geneFileRepository = geneFileRepository;
    }

    public boolean doesFileExist(String name){
        if (geneFileRepository.findAllByName(name).size() == 0)
            return false;
        else
            return true;
    }

    public GeneFile save(GeneFile file){
        return geneFileRepository.save(file);
    }
}
