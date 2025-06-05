package com.database.project.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name="cellphone", nullable = false)
    private String cellPhone;

}
