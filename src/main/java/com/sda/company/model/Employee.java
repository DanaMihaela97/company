package com.sda.company.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique=true)
    private String name;
    @Column (unique = true)
    private Long cnp;
    @Column
    private String email;
    @Column
    private String address;
    @Column
    private String phoneNumber;
    @Column
    private String job_title;
    @Column
    private Long salary;
    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;






}
