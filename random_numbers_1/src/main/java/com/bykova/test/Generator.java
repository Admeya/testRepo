package com.bykova.test;


import java.io.*;
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
    private static final double MIN_OPERATION_SUM = 10_000.00;
    private static final double MAX_OPERATION_SUM = 100_000.00;
    private static final int FIRST_DAY_OF_MONTH = 1;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.YYYY");
    private static final String MSG_ERROR_ALL = "Укажите путь к файлу с точками продаж, " +
            "количество операций и название выходного файла";
    private static final String MSG_ERROR_NUMBER_PARAMETERS = "Укажите правильное число параметров";

    Logger log = Logger.getLogger(Generator.class.getName());

    /**
     * Генерация тестовых данных в файл
     *
     * @param args параметры: файл с точками продаж, количеством операций, названием файла выходных данных
     */
    public void generateTestDataToFile(String[] args) {
        if (args.length == 0) {
            log.log(Level.SEVERE, MSG_ERROR_ALL);
        } else if (args.length != 3) {
            System.out.println(MSG_ERROR_NUMBER_PARAMETERS);
            throw new RuntimeException();
        }
        String inputFileName = args[0];
        int numberOperation = getNumberOperations(args[1]);
        String outputFileName = args[2];

        List<String> offices = getOfficesFromFile(inputFileName);
        List<OperationParameters> operationParametersList = getOperations(offices, numberOperation);
        writeToFile(operationParametersList, outputFileName);
    }

    private int getNumberOperations(String srcNumberInString) {
        int numberOperation;
        try {
            numberOperation = Integer.valueOf(srcNumberInString);
        } catch (NumberFormatException e) {
            log.log(Level.SEVERE, "Введите количество операций цифрами");
            throw new RuntimeException();
        }
        return numberOperation;
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
            for (OperationParameters parameter : operationParametersList) {
                writer.write(parameter.toString() + "\n");
            }
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    private String getDateFromLastYear() {
        Integer year = LocalDateTime.now().minusYears(1).getYear();

        LocalDate localDateMin = LocalDate.of(year, Month.JANUARY, FIRST_DAY_OF_MONTH);
        int minimumDate = (int) localDateMin.toEpochDay();

        LocalDate localDateMax = LocalDate.of(LocalDateTime.now().getYear(), Month.JANUARY, FIRST_DAY_OF_MONTH);
        int maximumDate = (int) localDateMax.toEpochDay();

        long randomDay = getRandomNumber(minimumDate, maximumDate);

        return LocalDate.ofEpochDay(randomDay).format(DATE_FORMATTER);
    }

    private String getRandomTime() {
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
            log.log(Level.SEVERE, "Путь к входному файлу указан неверно");
            throw new RuntimeException();
        }
        return offices;
    }
}
