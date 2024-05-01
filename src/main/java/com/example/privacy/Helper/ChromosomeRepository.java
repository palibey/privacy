package com.example.privacy.Helper;

import com.example.privacy.Model.Chromosome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChromosomeRepository extends JpaRepository<Chromosome, Long>
{
    public List<Chromosome> findAllByChromosomeNum(String num);

    public List<Chromosome> findAllByFileAndChromosomeNum(String file, String num);
}
