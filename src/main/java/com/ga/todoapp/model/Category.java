package com.ga.todoapp.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {
    @Id // primary key
    @Column // each attribute acts as a column in the table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // serial number of ids (sequential)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;
}
