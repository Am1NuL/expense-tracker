package com.thesis.expensetracker.model;

import static com.thesis.expensetracker.model.Income.DISCRIMINATOR_VALUE;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue(value = DISCRIMINATOR_VALUE)
public class Income extends Transaction {

    public static final String DISCRIMINATOR_VALUE = "I";

    public Income() {
    }

    @Override
    protected TransactionType getType() {
        return TransactionType.INCOME;
    }
}

