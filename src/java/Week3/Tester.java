package src.java.Week3;

/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public static void main(String[] args) {
        //testLogAnalyzer();
        //testUniqueIP();
        //testUniqueIPVisitsOnDay();
        //testCountUniqueIPsInRange();
        //testPrintAllHigherThanNum();
        //testCountVisitsPerIp();
        testMostNumberVisitsByIP();
    }

    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public static void testLogAnalyzer() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile();
        analyzer.printAll();
    }

    public static void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        System.out.println("Number of unique IPs: " + la.countUniqueIps());
    }

    public static void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        System.out.println(la.uniqueIPVisitsOnDay("Mar 24").size());       //should return 2 items
        //System.out.println(la.uniqueIPVisitsOnDay("Sep 30"));       //should return 3 items
    }

    public static void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        //System.out.println(la.countUniqueIPsinRange(200, 299));
        System.out.println(la.countUniqueIPsinRange(300, 399));
    }

    public static void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.printAllHigherThanNum(400);
    }

    public static void testCountVisitsPerIp() {
        LogAnalyzer la = new LogAnalyzer();
        System.out.println(la.countVisitsPerIp());
    }

    public static void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        System.out.println(la.mostNumberVisitsByIP(la.countVisitsPerIp()));
    }
}
