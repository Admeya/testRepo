package com.bykova.test;

/**
 * Основной класс, точка входа для консольной программы
 */
public class Main {
    /**
     * Вызов генерации тестовых данных
     */
    public static void main(String[] args) {
        Generator generator = new Generator();
        generator.generateTestDataToFile(args);
    }
}
