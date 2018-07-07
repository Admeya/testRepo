package com.bykova.test.dto;

import java.time.LocalDate;

import static com.bykova.test.bli.Report.FORMATTER;

public class AmountByDate {

    private LocalDate dateOperation;
    private Double sumOperation;

    public LocalDate getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(LocalDate dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Double getSumOperation() {
        return sumOperation;
    }

    public void setSumOperation(Double sumOperation) {
        this.sumOperation = sumOperation;
    }

    public AmountByDate(LocalDate dateOperation, Double sumOperation) {
        this.dateOperation = dateOperation;
        this.sumOperation = sumOperation;
    }

    public AmountByDate() {
    }

    @Override
    public String toString() {
        return dateOperation.format(FORMATTER) + " " + sumOperation;
    }
}
