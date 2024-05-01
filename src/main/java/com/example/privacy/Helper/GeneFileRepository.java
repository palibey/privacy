package com.example.privacy.Helper;

import com.example.privacy.Model.GeneFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneFileRepository extends JpaRepository<GeneFile, Long>
{
    public List<GeneFile> findAllByName(String name);
}
