package com.thesis.expensetracker.model;

import static com.thesis.expensetracker.model.Account.ENTITY_NAME;
import static com.thesis.expensetracker.model.Account.TABLE_NAME;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity(name = ENTITY_NAME)
@Table(name = TABLE_NAME)
@Getter
@Setter
public class Account {

    public static final String ENTITY_NAME = "Account";
    public static final String TABLE_NAME = "accounts";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Wallet> wallets = new HashSet<>();

}
