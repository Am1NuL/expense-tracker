package com.thesis.expensetracker.model;

import static com.thesis.expensetracker.model.Expense.DISCRIMINATOR_VALUE;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@DiscriminatorValue(value = DISCRIMINATOR_VALUE)
public class Expense extends Transaction{

    public static final String DISCRIMINATOR_VALUE = "E";

    public Expense() {

    }

    public Expense(double amount, LocalDate date, Category category, Wallet wallet) {
        this.setAmount(amount);
        this.setDate(LocalDate.now());
        this.setCategory(category);
        this.setWallet(wallet);
    }

    @Override
    protected TransactionType getType() {
        return TransactionType.EXPENSE;
    }
}
