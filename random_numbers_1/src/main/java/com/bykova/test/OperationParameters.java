package com.bykova.test;

/**
 * Параметры операции
 */
public class OperationParameters {

    private String dateOperation;
    private String timeOperation;
    private String office;
    private String sumOperation;
    private String numberOperation;

    /**
     * Конструктор на все параметры операции
     * @param dateOperation дата операции
     * @param timeOperation время операции
     * @param office точка продаж
     * @param sumOperation сумма операции
     * @param numberOperation номер операции
     */
    public OperationParameters(String dateOperation, String timeOperation, String office, String sumOperation,
                               String numberOperation) {
        this.dateOperation = dateOperation;
        this.timeOperation = timeOperation;
        this.office = office;
        this.sumOperation = sumOperation;
        this.numberOperation = numberOperation;
    }

    /**
     * Возвращает дату операции
     */
    public String getDateOperation() {
        return dateOperation;
    }

    /**
     * Устанавливает дату операции
     */
    public void setDateOperation(String dateTimeOperation) {
        this.dateOperation = dateTimeOperation;
    }

    /**
     * Возвращает дату и время операции
     */
    public String getTimeOperation() {
        return timeOperation;
    }

    /**
     * Устанавливает дату и время операции
     */
    public void setTimeOperation(String dateTimeOperation) {
        this.timeOperation = dateTimeOperation;
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
                + " " + getTimeOperation()
                + " " + getOffice()
                + " " + getSumOperation()
                + " " + getNumberOperation();

    }
}
