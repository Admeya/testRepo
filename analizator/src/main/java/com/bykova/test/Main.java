package com.bykova.test;

import com.bykova.test.bl.ReportImpl;
import com.bykova.test.bli.Report;

/**
 * Основной класс для запуска программы
 */
public class Main {
    private static final String MSG_ERROR_ALL = "Укажите путь к файлу с операциями, " +
            "название файла для отчета по датам, название файла для отчета по точкам продаж";
    private static final String MSG_ERROR_NUMBER_PARAMETERS = "Укажите правильное число параметров";

    /**
     * Точка входа в программу
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println(MSG_ERROR_ALL);
            throw new RuntimeException();
        } else if (args.length != 3) {
            System.out.println(MSG_ERROR_NUMBER_PARAMETERS);
            throw new RuntimeException();
        }
        Report report = new ReportImpl(args[0], args[1], args[2]);
        report.printAllReports();
    }
}
