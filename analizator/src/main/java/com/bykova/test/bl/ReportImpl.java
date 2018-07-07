package com.bykova.test.bl;

import com.bykova.test.bli.Report;
import com.bykova.test.dto.AmountByDate;
import com.bykova.test.dto.AmountByOffice;
import com.bykova.test.dto.OperationParameters;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.summingDouble;

public class ReportImpl implements Report {
    private String outputSumByDayFile;
    private String outputSumByOfficeFile;
    private List<OperationParameters> operationParameters;

    public ReportImpl(String inputFileName, String outputSumByDayFile, String outputSumByOfficeFile) {
        this.outputSumByDayFile = outputSumByDayFile;
        this.outputSumByOfficeFile = outputSumByOfficeFile;

        this.operationParameters = (new OperationsIO<>()).readFromFile(inputFileName);
    }

    @Override
    public void printAllReports(){
        printReportByDate();
        printReportByOffice();
    }

    private void printReportByDate(){
        List<AmountByDate> amounts = getAmountsByDate(operationParameters);
        (new OperationsIO<AmountByDate>()).writeToFile(amounts, outputSumByDayFile);
    }

    private void printReportByOffice(){
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
        if (param1.getSumOperation()<(param2.getSumOperation()))
            return 1;
        else if (param1.getSumOperation()>(param2.getSumOperation()))
            return -1;
        else
            return 0;
    };

    private List<AmountByDate> getAmountsByDate(List<OperationParameters> operationParameters) {
        Map<LocalDate, Double> amountsByDate =
                operationParameters
                        .stream()
                        .map((p1)->{
                            AmountByDate amounts = new AmountByDate();
                            amounts.setDateOperation(p1.getDateOperation());
                            amounts.setSumOperation(Double.valueOf(p1.getSumOperation().replace(",",".")));
                            return amounts;
                        } )
                        .collect(Collectors.toList())
                        .stream()
                        .collect(Collectors.groupingBy(AmountByDate::getDateOperation, summingDouble(AmountByDate::getSumOperation)));

        List<AmountByDate> itemsByDate = new ArrayList<>();
        for (Map.Entry<LocalDate, Double> item: amountsByDate.entrySet()) {
            itemsByDate.add(new AmountByDate(item.getKey(), item.getValue()));
        }
        return itemsByDate.stream().sorted(dateComparator).collect(Collectors.toList());
    }

    private List<AmountByOffice> getAmountsByOffice(List<OperationParameters> operationParameters) {
        Map<String, Double> amountsByOffice =
                operationParameters
                        .stream()
                        .map((p1)->{
                            AmountByOffice amounts = new AmountByOffice();
                            amounts.setOffice(p1.getOffice());
                            amounts.setSumOperation(Double.valueOf(p1.getSumOperation().replace(",",".")));
                            return amounts;
                        } )
                        .collect(Collectors.toList())
                        .stream()
                        .collect(Collectors.groupingBy(AmountByOffice::getOffice, summingDouble(AmountByOffice::getSumOperation)));

        List<AmountByOffice> itemsByDate = new ArrayList<>();
        for (Map.Entry<String, Double> item: amountsByOffice.entrySet()) {
            itemsByDate.add(new AmountByOffice(item.getKey(), item.getValue()));
        }
        return itemsByDate.stream().sorted(officeComparator).collect(Collectors.toList());
    }
}
