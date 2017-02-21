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

     public HashMap<String, ArrayList<String>> iPsForDays() {
         HashMap<String, ArrayList<String>> ipVisitsByDay = new HashMap<String, ArrayList<String>>();

         for (LogEntry entry : records) {
             String date = entry.getAccessTime().toString(); //What format will this be? Include time?
             String ip = entry.getIpAddress();

             if (!ipVisitsByDay.containsKey(date)) {
                 ArrayList<String> ipsOnDate = new ArrayList<String>();
                 ipsOnDate.add(ip);
                 ipVisitsByDay.put(date, ipsOnDate);
             } else if (!ipVisitsByDay.get(date).contains(ip)) {
                 ipVisitsByDay.get(date).add(ip);
             }
         }
         return ipVisitsByDay;
     }
}
