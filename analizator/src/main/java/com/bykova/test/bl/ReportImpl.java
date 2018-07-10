package com.bykova.test.bl;

import com.bykova.test.bli.Report;
import com.bykova.test.dto.AmountByDate;
import com.bykova.test.dto.AmountByOffice;
import com.bykova.test.dto.OperationParameters;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.summingDouble;

/**
 * Реализация класса построения отчетов
 */
public class ReportImpl implements Report {
    private String outputSumByDayFile;
    private String outputSumByOfficeFile;
    private List<OperationParameters> operationParameters;

    /**
     * Конструктор
     *
     * @param inputFileName         название входного файла с логом по операциям
     * @param outputSumByDayFile    название выходного файла для отчета по дням
     * @param outputSumByOfficeFile название выходного файла для отчета по офисам
     */
    public ReportImpl(String inputFileName, String outputSumByDayFile, String outputSumByOfficeFile) {

        this.outputSumByDayFile = outputSumByDayFile;
        this.outputSumByOfficeFile = outputSumByOfficeFile;
        this.operationParameters = (new OperationsIO<>()).readFromFile(inputFileName);
    }

    @Override
    public void printAllReports() {
        printReportByDate();
        printReportByOffice();
    }

    private void printReportByDate() {
        List<AmountByDate> amounts = getAmountsByDate(operationParameters);
        (new OperationsIO<AmountByDate>()).writeToFile(amounts, outputSumByDayFile);
    }

    private void printReportByOffice() {
        List<AmountByOffice> amounts = getAmountsByOffice(operationParameters);
        (new OperationsIO<AmountByOffice>()).writeToFile(amounts, outputSumByOfficeFile);
    }

    private Comparator<AmountByDate> dateComparator = (param1, param2) -> {
        if (param1.getDateOperation().isBefore(param2.getDateOperation()))
            return -1;
        else if (param1.getDateOperation().isAfter(param2.getDateOperation()))
            return 1;
        else
            return 0;
    };

    private Comparator<AmountByOffice> officeComparator = (param1, param2) -> {
        if (param1.getSumOperation() < (param2.getSumOperation()))
            return 1;
        else if (param1.getSumOperation() > (param2.getSumOperation()))
            return -1;
        else
            return 0;
    };

    private List<AmountByDate> getAmountsByDate(List<OperationParameters> operationParameters) {
        List<AmountByDate> itemsByDate = new ArrayList<>();
        operationParameters
                .stream()
                .map((p1) -> {
                    AmountByDate amounts = new AmountByDate();
                    amounts.setDateOperation(p1.getDateOperation());
                    amounts.setSumOperation(Double.valueOf(p1.getSumOperation().replace(",", ".")));
                    return amounts;
                })
                .collect(Collectors.toList())
                .stream()
                .collect(Collectors.groupingBy(AmountByDate::getDateOperation, summingDouble(AmountByDate::getSumOperation)))
                .forEach((date, sum) -> itemsByDate.add(new AmountByDate(date, sum)));
        return itemsByDate.stream().sorted(dateComparator).collect(Collectors.toList());
    }

    private List<AmountByOffice> getAmountsByOffice(List<OperationParameters> operationParameters) {
        List<AmountByOffice> amountByOffices = new ArrayList<>();
        operationParameters
                .stream()
                .map((p1) -> {
                    AmountByOffice amounts = new AmountByOffice();
                    amounts.setOffice(p1.getOffice());
                    amounts.setSumOperation(Double.valueOf(p1.getSumOperation().replace(",", ".")));
                    return amounts;
                })
                .collect(Collectors.toList())
                .stream()
                .collect(Collectors.groupingBy(AmountByOffice::getOffice, summingDouble(AmountByOffice::getSumOperation)))
                .forEach((office, sum) -> amountByOffices.add(new AmountByOffice(office, sum)));
        return amountByOffices.stream().sorted(officeComparator).collect(Collectors.toList());
    }
}
