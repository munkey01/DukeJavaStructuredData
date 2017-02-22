package src.java.Week3;

import java.util.*;
import edu.duke.FileResource;

public class LogAnalyzer
{
     private List<LogEntry> records = new ArrayList<>();
     
     public LogAnalyzer() {
         readFile();
     }
        
     public void readFile() {
         FileResource logFile = new FileResource();
         for (String line : logFile.lines()) {
             LogEntry entry = WebLogParser.parseEntry(line);
             records.add(entry);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }

     public int countUniqueIps() {
         List<String> uniqueIps = new ArrayList<>();
         for (LogEntry entry : records) {
             String currIp = entry.getIpAddress();
             if (!uniqueIps.contains(currIp)) {
                 uniqueIps.add(currIp);
             }
         }
         return uniqueIps.size();
     }

     public void printAllHigherThanNum(int num) {
         for (LogEntry entry : records) {
             int currStatusCode = entry.getStatusCode();
             if (currStatusCode > num) {
                 System.out.println(currStatusCode);
             }
         }
     }

     /* day is expected to be in format MMM DD where MMM is first three letters of month name */
     public ArrayList<String> uniqueIPVisitsOnDay(String day) {
         ArrayList<String> uniqueIps = new ArrayList<>();
         for (LogEntry entry : records) {
             String currDateOfAccess = entry.getAccessTime().toString();
             String currIp = entry.getIpAddress();
             if (currDateOfAccess.contains(day) && !uniqueIps.contains(currIp)) {
                 uniqueIps.add(currIp);
             }
         }
         return uniqueIps;
     }

     public int countUniqueIPsinRange(int low, int high) {
         ArrayList<String> uniqueIps = new ArrayList<>();
         for (LogEntry entry : records) {
             int currStatusCode = entry.getStatusCode();
             String currIp = entry.getIpAddress();
             if (currStatusCode >= low && currStatusCode <= high && !uniqueIps.contains(currIp)) {
                 uniqueIps.add(currIp);
             }
         }
         return uniqueIps.size();
     }
     
     public HashMap<String, Integer> countVisitsPerIp() {
         HashMap<String, Integer> visitsByIp = new HashMap<>();
         for (LogEntry entry : records) {
             String currIp = entry.getIpAddress();
             if (visitsByIp.containsKey(currIp)) {
                 int numVisits = visitsByIp.get(currIp);
                 visitsByIp.put(currIp, ++numVisits);
             } else {
                 visitsByIp.put(currIp, 1);
             }
         }
         return visitsByIp;
     }

     public int mostNumberVisitsByIP(HashMap<String, Integer> visitsByIp) {
         int max = 0;
         for (int visitCount: visitsByIp.values()) {
             if (visitCount > max) {
                 max = visitCount;
             }
         }
         return max;
     }

     public ArrayList<String> ipsMostVisits(HashMap<String, Integer> ipCounts) {
         int max = 0;
         ArrayList<String> mostFrequentIps = new ArrayList<>();
         for (String ip : ipCounts.keySet()) {
             if (ipCounts.get(ip) >= max) {
                 max = ipCounts.get(ip);
                 mostFrequentIps.add(ip);
             }
         }
         return mostFrequentIps;
     }

     private String parseDay(String dateStringFromLogEntry) {
         //Mon Sep 21 07:59:14 EDT 2015
         return dateStringFromLogEntry.substring(4,10);
     }

     public HashMap<String, ArrayList<String>> iPsForDays() {
         HashMap<String, ArrayList<String>> ipsByDay = new HashMap<String, ArrayList<String>>();
         for (LogEntry entry : records) {
             String dateFromLogEntry = parseDay(entry.getAccessTime().toString());
             String ipFromLogEntry = entry.getIpAddress();
             if (ipsByDay.containsKey(dateFromLogEntry) && !(ipsByDay.get(dateFromLogEntry).contains(ipFromLogEntry))) {
                 ipsByDay.get(dateFromLogEntry).add(ipFromLogEntry);
             } else if (!ipsByDay.containsKey(dateFromLogEntry)) {
                 ArrayList<String> ipsOnCurrDate = new ArrayList<>();
                 ipsOnCurrDate.add(ipFromLogEntry);
                 ipsByDay.put(dateFromLogEntry, ipsOnCurrDate);
             }
         }
         return ipsByDay;
     }
}
