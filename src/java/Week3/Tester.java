package src.java.Week3;

/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import sun.rmi.runtime.Log;

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
        //testMostNumberVisitsByIP();
        //testIpsMostVisits();
        //testiPsForDays();
        //testDayWithMostIPVisits();
        testIPsWithMostVisitsOnDay(); //should return 61.15.121.171 and 177.4.40.87, FAIL
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

    public static void testIpsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        HashMap<String, Integer> ipCounts = la.countVisitsPerIp();
        System.out.println(la.ipsMostVisits(ipCounts));
    }

    public static void testiPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        HashMap<String, ArrayList<String>> results = new HashMap<String, ArrayList<String>>();
        results = la.iPsForDays();
        for (String date : results.keySet()) {
            System.out.println(date);
            for (String ip : results.get(date)) {
                System.out.println("\t" + ip);
            }
        }
    }

    public static void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        HashMap<String, ArrayList<String>> ipsByDate = new HashMap<String, ArrayList<String>>();
        ipsByDate = la.iPsForDays();
        String result = la.dayWithMostIPVisits(ipsByDate);
        System.out.println(result);
    }

    public static void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        System.out.println(la.iPsWithMostVisitsOnDay(la.iPsForDays(), "Sep 30"));
    }
}
