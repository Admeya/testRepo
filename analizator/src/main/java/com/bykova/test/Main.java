package com.bykova.test;

import com.bykova.test.bl.ReportImpl;
import com.bykova.test.bli.Report;

/**
 * Основной класс для запуска программы
 */
public class Main {
    /**
     * Точка входа в программу
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        Report report = new ReportImpl(args[0], args[1], args[2]);
        report.printAllReports();
    }
}
