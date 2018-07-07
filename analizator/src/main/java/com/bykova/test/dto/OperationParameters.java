package com.bykova.test.dto;

import java.time.LocalDate;

/**
 * Параметры операции
 */
public class OperationParameters {

    private LocalDate dateOperation;
    private String office;
    private String sumOperation;
    private String numberOperation;

    /**
     * Конструктор на все параметры операции
     * @param dateOperation дата и время операции
     * @param office точка продаж
     * @param sumOperation сумма операции
     * @param numberOperation номер операции
     */
    public OperationParameters(LocalDate dateOperation, String office, String sumOperation,
                               String numberOperation) {
        this.dateOperation = dateOperation;
        this.office = office;
        this.sumOperation = sumOperation;
        this.numberOperation = numberOperation;
    }

    /**
     * Возвращает дату и время операции
     */
    public LocalDate getDateOperation() {
        return dateOperation;
    }

    /**
     * Устанавливает дату и время операции
     */
    public void setDateOperation(LocalDate dateOperation) {
        this.dateOperation = dateOperation;
    }

    /**
     * Возвращает название точки продаж
     */
    public String getOffice() {
        return office;
    }

    /**
     * Устанавливает название точки продаж
     */
    public void setOffice(String office) {
        this.office = office;
    }

    /**
     * Возвращает сумму операции
     */
    public String getSumOperation() {
        return sumOperation;
    }

    /**
     * Устанавливает сумму операции
     */
    public void setSumOperation(String sumOperation) {
        this.sumOperation = sumOperation;
    }

    /**
     * Возвращает номер операции
     */
    public String getNumberOperation() {
        return numberOperation;
    }

    /**
     * Устанавливает номер операции
     */
    public void setNumberOperation(String numberOperation) {
        this.numberOperation = numberOperation;
    }

    @Override
    public String toString() {
        return getDateOperation()
                + " " + getSumOperation()
                + " " + getOffice()
                + " " + getNumberOperation()
                + " " + getSumOperation();
    }
}
