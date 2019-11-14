package com.thesis.expensetracker.model;

import static com.thesis.expensetracker.model.Transaction.ENTITY_NAME;
import static com.thesis.expensetracker.model.Transaction.TABLE_NAME;
import static com.thesis.expensetracker.model.Transaction.DISCRIMINATOR_COLUMN;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity(name = ENTITY_NAME)
@Table(name = TABLE_NAME)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = DISCRIMINATOR_COLUMN)
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.PROPERTY, property="type")
@Getter
@Setter
public abstract class Transaction {

    public static final String ENTITY_NAME = "Transaction";
    public static final String TABLE_NAME = "transactions";
    public static final String DISCRIMINATOR_COLUMN = "transaction_type";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private double amount;

    @Column(name = "date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

//        TODO:
//    private ??? reccurence;
//    private String place;
//    private byte image;

    protected abstract TransactionType getType();
}

