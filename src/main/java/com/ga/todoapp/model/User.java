package com.ga.todoapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table( name = "users")
@ToString(exclude = {"password", "userProfile", "itemList", "categoryList"})
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName ;

    @Column
    private String emailAddress;

    @Column
    // Password can be sent to the API but will not be returned in JSON responses
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    // Links the user to their profile using the profileId column
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "profileId", referencedColumnName = "id")
    private UserProfile userProfile;

    // One user can have many recipes
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Item> itemList;

    // One user can have many categories
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Category> categoryList;

    // Prevents the password from being included in JSON responses
    @JsonIgnore
    public String getPassword() {
        return password;
    }
}

