package com.bykova.test;


import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Генератор тестовых данных
 */
public class Generator {
    public static double MIN_OPERATION_SUM = 10_000.00;
    public static double MAX_OPERATION_SUM = 100_000.00;

    Logger log = Logger.getLogger(Generator.class.getName());

    /**
     * Генерация тестовых данных в файл
     * @param args параметры: файл с точками продаж, количеством операций, названием файла выходных данных
     */
    public void generateTestDataToFile(String[] args) {
        String inputFileName = args[0];
        int numberOperation = Integer.valueOf(args[1]);
        String outputFileName = args[2];
        List<String> offices = getOfficesFromFile(inputFileName);

        List<OperationParameters> operationParametersList = getOperations(offices, numberOperation);
        writeToFile(operationParametersList, outputFileName);
    }

    private List<OperationParameters> getOperations(List<String> offices, int numberOperation) {
        List<OperationParameters> operationParametersList = new ArrayList<>();
        for (int i = 1; i <= numberOperation; i++) {
            String dateOperation = getDateFromLastYear();
            String timeOperation = getRandomTime();
            String officeNumber = getOffice(offices);
            String sumOperation = getRandomSum(MIN_OPERATION_SUM, MAX_OPERATION_SUM);

            operationParametersList.add(new OperationParameters(dateOperation, timeOperation, officeNumber, sumOperation, String.valueOf(i)));
        }
        return operationParametersList;
    }

    private void writeToFile(List<OperationParameters> operationParametersList, String outputFileName) {
        try (FileWriter writer = new FileWriter(outputFileName)) {
            for (OperationParameters parameter: operationParametersList) {
                writer.write(parameter.toString()+"\n");
            }
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
        }

    }

    private String getDateFromLastYear() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");

        Integer year = LocalDateTime.now().minusYears(1).getYear();

        LocalDate localDateMin = LocalDate.of(year, Month.JANUARY, 1);
        int minimumDate = (int) localDateMin.toEpochDay();

        LocalDate localDateMax = LocalDate.of(year, Month.DECEMBER, Month.DECEMBER.maxLength());
        int maximumDate = (int) localDateMax.toEpochDay();

        long randomDay = getRandomNumber(minimumDate, maximumDate);

        return LocalDate.ofEpochDay(randomDay).format(dateFormatter);
    }

    private String getRandomTime(){
        int randomMinutes = getRandomNumber(LocalTime.MIN.getMinute(), LocalTime.MAX.getMinute());
        int randomHours = getRandomNumber(LocalTime.MIN.getHour(), LocalTime.MAX.getHour());

        return LocalTime.of(randomHours, randomMinutes).toString();
    }

    private String getOffice(List<String> offices) {
        int randomIndex = getRandomIndex(offices.size());
        return offices.get(randomIndex);
    }

    private String getRandomSum(double minSum, double maxSum) {
        Random randomizer = new Random();
        double randomNumber = minSum + (maxSum - minSum) * randomizer.nextDouble();
        return String.format("%1$.2f", randomNumber);
    }

    private int getRandomIndex(int length) {
        Random randomizer = new Random();
        return randomizer.nextInt(length);
    }

    private int getRandomNumber(int minRange, int maxRange) {
        Random randomizer = new Random();
        return minRange + randomizer.nextInt(maxRange - minRange);
    }

    private List<String> getOfficesFromFile(String fileName) {
        List<String> offices = new ArrayList<>();
        File inputFile = new File(fileName);
        try (Scanner fileScanner = new Scanner(inputFile)) {
            while (fileScanner.hasNextLine()) {
                offices.add(fileScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            log.log(Level.SEVERE, e.toString());
        }
        return offices;
    }
}
