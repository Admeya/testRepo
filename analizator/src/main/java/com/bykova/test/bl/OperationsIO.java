package com.bykova.test.bl;

import com.bykova.test.Main;
import com.bykova.test.dto.AmountByDate;
import com.bykova.test.dto.OperationParameters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.bykova.test.bli.Report.FORMATTER;

/**
 * Класс для управления операциями ввода-вывода
 * @param <T>
 */
public class OperationsIO<T> {
    Logger log = Logger.getLogger(Main.class.getName());

    /**
     * Запись в файл
     * @param reportData данные для записи в файл
     * @param outputFileName название выходного файла
     */
    public void writeToFile(List<T> reportData, String outputFileName) {
        try (FileWriter writer = new FileWriter(outputFileName)) {
            for (T parameter: reportData) {
                writer.write(parameter.toString()+"\n");
            }
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    /**
     * Чтение из файла
     * @param fileName название файла с входными параметрами
     * @return список объектов параметров операций
     */
    public List<OperationParameters> readFromFile(String fileName) {
        List<OperationParameters> operationParameters = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            String line;
            while (scanner.hasNextLine() && !(line = scanner.nextLine()).isEmpty()) {
                String[] parametersString = line.split("\\s+");

                LocalDate inputDate = converterToDate(parametersString[0]);

                OperationParameters parameter = new OperationParameters(inputDate,
                        parametersString[2], parametersString[3], parametersString[4]);
                operationParameters.add(parameter);
            }
        } catch (FileNotFoundException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        return operationParameters;
    }

    private LocalDate converterToDate(String date) {
        LocalDate lDate;
        try {
            lDate = LocalDate.from(FORMATTER.parse(date));
        } catch (RuntimeException e) {
            throw new RuntimeException("Дата или время указаны в неверном формате: " + e.getMessage());
        }
        return lDate;
    }
}
