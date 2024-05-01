package com.example.privacy.Helper;

import com.example.privacy.Model.Chromosome;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChromosomeService {
    private final ChromosomeRepository chromosomeRepository;


    public ChromosomeService(ChromosomeRepository chromosomeRepository)
    {
        this.chromosomeRepository = chromosomeRepository;
    }

    public List<Chromosome> getAllByNum(String num){
        return chromosomeRepository.findAllByChromosomeNum(num);
    }

    public List<Chromosome> getAllByFileAndNum(String file, String num)
    {
        return chromosomeRepository.findAllByFileAndChromosomeNum(file, num);
    }

    public Chromosome save(Chromosome chromosome){
        return chromosomeRepository.save(chromosome);
    }
}
