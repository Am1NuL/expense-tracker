package com.thesis.expensetracker.model;

import static com.thesis.expensetracker.model.Wallet.ENTITY_NAME;
import static com.thesis.expensetracker.model.Wallet.TABLE_NAME;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = ENTITY_NAME)
@Table(name = TABLE_NAME)
@Getter
@Setter
public class Wallet {

    public static final String ENTITY_NAME = "Wallet";
    public static final String TABLE_NAME = "wallets";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "total_wealth")
    private double totalWealth;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wallet", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Transaction> transactions = new HashSet<>();
}
