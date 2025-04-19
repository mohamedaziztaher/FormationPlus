package com.example.mini_project.repository;

import com.example.mini_project.model.Training;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    @Query("SELECT COUNT(t) FROM Training t WHERE t.year = :year")
    long countByYear(@Param("year") int year);

    @Query("SELECT t.domain.label, COUNT(t) FROM Training t GROUP BY t.domain.label")
    List<Object[]> countByDomain();

    @Query("SELECT t.structure.label, COUNT(t) FROM Training t GROUP BY t.structure.label")
    List<Object[]> countByStructure();

    @Query("SELECT t FROM Training t WHERE t.domain.id = :domainId")
    List<Training> findByDomainId(@Param("domainId") Long domainId);
    Page<Training> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    List<Training> findByTrainerId(int trainer_id);
}
