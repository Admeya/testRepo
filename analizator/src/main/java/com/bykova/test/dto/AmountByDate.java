package com.bykova.test.dto;

import java.time.LocalDate;

import static com.bykova.test.bli.Report.FORMATTER;

/**
 * Вспомогательный объект для хранения сумм и дат операции
 */
public class AmountByDate {

    private LocalDate dateOperation;
    private Double sumOperation;

    /**
     * Конструктор
     *
     * @param dateOperation дата операции
     * @param sumOperation  сумма операции
     */
    public AmountByDate(LocalDate dateOperation, Double sumOperation) {
        this.dateOperation = dateOperation;
        this.sumOperation = sumOperation;
    }

    /**
     * Конструктор без параметров
     */
    public AmountByDate() {
    }

    /**
     * Возвращает дату операции
     */
    public LocalDate getDateOperation() {
        return dateOperation;
    }

    /**
     * Устанавливает дату операции
     */
    public void setDateOperation(LocalDate dateOperation) {
        this.dateOperation = dateOperation;
    }

    /**
     * Возвращает сумму операции
     */
    public Double getSumOperation() {
        return sumOperation;
    }

    /**
     * Устанавливает сумму операции
     */
    public void setSumOperation(Double sumOperation) {
        this.sumOperation = sumOperation;
    }

    @Override
    public String toString() {
        return dateOperation.format(FORMATTER) + " " + String.format("%1$.2f", sumOperation);
    }
}
