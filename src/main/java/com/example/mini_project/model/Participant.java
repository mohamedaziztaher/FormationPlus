package com.example.mini_project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    @NotBlank(message = "First Name is required")
    private String firstName;
    
    @Column(nullable = false)
    @NotBlank(message = "Last Name is required")
    private String lastName;
    
    @ManyToOne
    @JoinColumn(name = "structure_id", nullable = false)
    private Structure structure;
    
    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profil profile;
    
    @ManyToOne
    @JoinColumn(name = "training_id", nullable = false)
    private Training training;
    
    @Email(message = "Email should be valid")
    private String email;
    
    private Integer phone;
}
