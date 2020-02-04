package com.thesis.expensetracker.model;

import static com.thesis.expensetracker.model.Category.ENTITY_NAME;
import static com.thesis.expensetracker.model.Category.TABLE_NAME;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = ENTITY_NAME)
@Table(name = TABLE_NAME)
@Setter
@Getter
public class Category {

    public static final String ENTITY_NAME = "Category";
    public static final String TABLE_NAME = "categories";

    public Category() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "name")
    public String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    public TransactionType type;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    public Account account;

//    TODO:
//    private byte icon;
}
