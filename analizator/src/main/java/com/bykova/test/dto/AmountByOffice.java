package com.bykova.test.dto;

public class AmountByOffice {

    private String office;
    private Double sumOperation;

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Double getSumOperation() {
        return sumOperation;
    }

    public void setSumOperation(Double sumOperation) {
        this.sumOperation = sumOperation;
    }

    public AmountByOffice(String dateOperation, Double sumOperation) {
        this.office = dateOperation;
        this.sumOperation = sumOperation;
    }

    public AmountByOffice() {
    }

    @Override
    public String toString() {
        return office + " " + sumOperation;
    }
}
