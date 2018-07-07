package com.bykova.test;

import com.bykova.test.bl.ReportImpl;
import com.bykova.test.bli.Report;

public class Main {
    public static void main(String[] args) {
        Report report = new ReportImpl(args[0], args[1], args[2]);
        report.printAllReports();
    }
}
