package com.example.mini_project.repository;

import com.example.mini_project.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Integer> {

    @Query("SELECT p.training.id, COUNT(p) " +
           "FROM Participant p " +
           "GROUP BY p.training.id")
    List<Object[]> countByTraining();

    @Query("SELECT p.training.domain.label, COUNT(p) " +
           "FROM Participant p " +
           "GROUP BY p.training.domain.label")
    List<Object[]> countByDomain();

}
