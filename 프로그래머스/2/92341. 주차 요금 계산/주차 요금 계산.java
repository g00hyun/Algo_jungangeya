import java.util.*;

class Solution {
    Map<String, ParkingLog> cars = new TreeMap<>();
    
    public int[] solution(int[] fees, String[] records) {
        for(String r : records) {
            String[] line = r.split(" ");
            
            cars.put(line[1], new ParkingLog());
        }
        
        for(String r : records) {
            String[] line = r.split(" ");
            
            cars.get(line[1]).log.add(line[0]);
        }
        
        cars.forEach((k,v) -> {
            // System.out.println("[" + k + "]");
            // for(String i : v.log)
            //     System.out.print(i + " ");
            // System.out.println();
            
            v.calcTotalParkingTime();
            // System.out.println(v.totalParkingTime);
            v.calcParkingFee(fees);
            
            // System.out.println(v.parkingFee);
        });
        
        return cars.values().stream().mapToInt(v -> v.parkingFee).toArray();
    }
}

class ParkingLog {
    List<String> log;
    int totalParkingTime;
    int parkingFee;
    
    ParkingLog() {
        this.log = new ArrayList<>();
    }
    
    void calcTotalParkingTime() {
        String in = null;
        String out = null;
        
        int idx = 0;
        while(idx+1 < log.size()) {
            in = log.get(idx++);
            out = log.get(idx++);
            
            this.totalParkingTime += this.timeDiff(in, out);
        }
        
        if(idx == log.size()-1) {
            in = log.get(idx++);
            out = "23:59";
            
            this.totalParkingTime += this.timeDiff(in, out);
        }
    }
    
    void calcParkingFee(int[] fees) {
        // [0] == basicMin / [1] == basicFee / [2] == unitMin / [3] == unitFee
        
        this.parkingFee += fees[1];
        
        if(this.totalParkingTime > fees[0]) {
            int unitTime = this.totalParkingTime - fees[0];
            if(unitTime % fees[2] != 0)
                unitTime += fees[2];
            
            this.parkingFee += unitTime / fees[2] * fees[3];
        }
    }
    
    private int timeDiff(String IN, String OUT) {
        int[] in = Arrays.stream(IN.split(":")).mapToInt(Integer::parseInt).toArray();
        int[] out = Arrays.stream(OUT.split(":")).mapToInt(Integer::parseInt).toArray();
        
        int minDiff = out[1] - in[1];
        int hourDiff = out[0] - in[0];
        
        return hourDiff * 60 + minDiff;
    }
    
    
}