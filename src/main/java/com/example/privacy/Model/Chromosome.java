package com.example.privacy.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Chromosome
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    public String file;

    private String rsid;

    private String chromosomeNum;

    private String position;

    private String allale;

    public Chromosome(String file, String rsid, String chromosomeNum, String position, String allale)
    {
        this.file = file;
        this.rsid = rsid;
        this.chromosomeNum = chromosomeNum;
        this.position = position;
        this.allale = allale;
    }
}
