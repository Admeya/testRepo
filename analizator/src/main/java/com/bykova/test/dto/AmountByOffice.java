package com.bykova.test.dto;

/**
 * Вспомогательный объект для хранения сумм и названий офисов
 */
public class AmountByOffice {

    private String office;
    private Double sumOperation;

    /**
     * Конструктор
     *
     * @param dateOperation дата операции
     * @param sumOperation  сумма операции
     */
    public AmountByOffice(String dateOperation, Double sumOperation) {
        this.office = dateOperation;
        this.sumOperation = sumOperation;
    }

    /**
     * Конструктор
     */
    public AmountByOffice() {
    }

    /**
     * Возвращает название офиса
     */
    public String getOffice() {
        return office;
    }

    /**
     * Устанавливает название офиса
     */
    public void setOffice(String office) {
        this.office = office;
    }

    /**
     * Возвращает дату операции
     */
    public Double getSumOperation() {
        return sumOperation;
    }

    /**
     * Устанавливает дату операции
     */
    public void setSumOperation(Double sumOperation) {
        this.sumOperation = sumOperation;
    }

    @Override
    public String toString() {
        return office + " " + String.format("%1$.2f", sumOperation);
    }
}
