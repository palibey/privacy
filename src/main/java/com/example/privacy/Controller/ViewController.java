package com.example.privacy.Controller;
import com.example.privacy.Helper.ChromosomeParser;
import com.example.privacy.Helper.ChromosomeService;
import com.example.privacy.Helper.GeneFileService;
import com.example.privacy.Model.GeneFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ViewController {

    private final String folderPath = "C:\\Users\\erend\\Desktop\\Spring2023-2024\\Privacy\\dataset"; // Replace with your folder path

    private final ChromosomeParser chromosomeParser;
    private final GeneFileService geneFileService;

    public ViewController(ChromosomeParser chromosomeParser, GeneFileService geneFileService)
    {
        this.chromosomeParser = chromosomeParser;
        this.geneFileService = geneFileService;
    }

    @GetMapping("/")
    public String index(Model model) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        List<String> fileNames = new ArrayList<>();
        List<String> displayNames = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (file.getName().contains("23andme")){
                    String filteredName = file.getName().substring(0, file.getName().indexOf('_'));
                    displayNames.add(filteredName);
                    fileNames.add(file.getName());
                }
            }
        }
        model.addAttribute("fileNames", fileNames);
        model.addAttribute("displayName", displayNames);
        return "fileSelector";
    }


    @GetMapping("/viewFile")
    public String viewFile(@RequestParam("payload") String payload,@RequestParam("fileName") String fileName, Model model) throws IOException
    {
        if (!geneFileService.doesFileExist(fileName)){
            String filePath = folderPath + "\\" + fileName;
            chromosomeParser.parseFromFile(filePath, fileName);
            geneFileService.save(new GeneFile(fileName));
        }
        return "redirect:/chromosome/" + payload + "/" + fileName + "/1";

    }
}
