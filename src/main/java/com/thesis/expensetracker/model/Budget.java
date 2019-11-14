package com.thesis.expensetracker.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static com.thesis.expensetracker.model.Budget.ENTITY_NAME;
import static com.thesis.expensetracker.model.Budget.TABLE_NAME;

@Entity(name = ENTITY_NAME)
@Table(name = TABLE_NAME)
@Setter
@Getter
public class Budget {

    public static final String ENTITY_NAME = "Budget";
    public static final String TABLE_NAME = "budgets";

    public Budget() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "name")
    private  String name;

    @Column(name = "amount")
    private double amount;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "recurrence")
    private RecurrenceType recurrence;

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "budget_category_links", joinColumns = {
            @JoinColumn(name = "budget_id", nullable = false, updatable = false) }, inverseJoinColumns = {
            @JoinColumn(name = "category_id", nullable = false, updatable = false)
    })
    private Set<Category> categories = new HashSet<>();
}
