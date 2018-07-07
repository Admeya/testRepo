package com.bykova.test.bli;

import java.time.format.DateTimeFormatter;

/**
 * Построение отчетов для вывода в файл
 */
public interface Report {
    DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /**
     * Печать набора отчетов
     */
    void printAllReports();
}
