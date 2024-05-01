package com.example.privacy.Controller;


import com.example.privacy.Helper.ChromosomeService;
import com.example.privacy.Model.Chromosome;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Controller
public class ChromosomeController {
    private final ChromosomeService chromosomeService;

    public ChromosomeController(ChromosomeService chromosomeService)
    {
        this.chromosomeService = chromosomeService;
    }



    @GetMapping("/chromosome/blocked/{file}/{chromosomeNum}")
    public String getNum1(@PathVariable String file, @PathVariable String chromosomeNum, Model model) {
        int[] forbidden = {11, 15, 16, 17, 18};
        boolean isForbidden = false;
        for (int j : forbidden)
        {
            if (j == Integer.parseInt(chromosomeNum))
            {
                isForbidden = true;
                break;
            }
        }
        List<Chromosome> chromosomes = chromosomeService.getAllByFileAndNum(file,chromosomeNum);
        if (chromosomes.isEmpty()) {
            return "noChromosomesFoundView";

        }else if (isForbidden){
            model.addAttribute("file", file);
            model.addAttribute("page", Integer.parseInt(chromosomeNum));
            model.addAttribute("version", "blocked");
            return "forbidden";
        }
        else {
            model.addAttribute("file", file);
            model.addAttribute("page", Integer.parseInt(chromosomeNum));
            model.addAttribute("version", "blocked");
            model.addAttribute("chromosomes", chromosomes);
            return "chromosomesView";
        }
    }
    @GetMapping("/chromosome/partial/{file}/{chromosomeNum}")
    public String getNum2(@PathVariable String file, @PathVariable String chromosomeNum, Model model) {
        List<Chromosome> chromosomes = chromosomeService.getAllByFileAndNum(file,chromosomeNum);
        Random rand = new Random();
        double probability = 0.3;
        for (Chromosome chromosome : chromosomes) {
            double randomValue = rand.nextDouble();
            if (randomValue <= probability) {
                chromosome.setAllale("--");
            }
        }
        if (chromosomes.isEmpty()) {
            return "noChromosomesFoundView";

        }
        else {
            model.addAttribute("file", file);
            model.addAttribute("page", Integer.parseInt(chromosomeNum));
            model.addAttribute("version", "partial");
            model.addAttribute("chromosomes", chromosomes);
            return "chromosomesView";
        }
    }
    @GetMapping("/chromosome/normal/{file}/{chromosomeNum}")
    public String getNum3(@PathVariable String file, @PathVariable String chromosomeNum, Model model) {
        List<Chromosome> chromosomes = chromosomeService.getAllByFileAndNum(file,chromosomeNum);
        if (chromosomes.isEmpty()) {
            return "noChromosomesFoundView";

        }
        else {
            model.addAttribute("file", file);
            model.addAttribute("page", Integer.parseInt(chromosomeNum));
            model.addAttribute("version", "normal");
            model.addAttribute("chromosomes", chromosomes);
            return "chromosomesView";
        }
    }
}
