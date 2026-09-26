package com.ga.todoapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    // one category can have many items
    // fetch: When I get a Category, also get its Items immediately.
    // mapped: in items the field is called category (private Category category). Therefore, mappedBy = "category"
    // orphan: if an Item is removed from this category's itemList, Hibernate can also remove that orphaned item from the database.
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category", orphanRemoval = true)
    private List<Item> itemList;
}
