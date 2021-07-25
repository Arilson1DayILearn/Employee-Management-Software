package com.software.employeemanager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
public class Employee implements Serializable {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, length = 180)
    private String email;

    @Column(nullable = false, length = 180)
    private String jobTitle;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(nullable = false, length = 180)
    private String imageUrl;

    @Column(nullable = false, updatable = false, length = 250)
    private String employeeCode;

    public Employee(String name, String email, String jobTitle, String phone, String imageUrl, String employeeCode) {
        this.name = name;
        this.email = email;
        this.jobTitle = jobTitle;
        this.phone = phone;
        this.imageUrl = imageUrl;
        this.employeeCode = employeeCode;
    }



}
