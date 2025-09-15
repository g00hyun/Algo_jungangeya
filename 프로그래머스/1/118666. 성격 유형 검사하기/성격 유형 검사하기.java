class Solution {
    public String solution(String[] survey, int[] choices) {
        Person person = new Person();
        
        for(int i = 0; i<survey.length; i++)
            person.calculateSurvey(survey[i], choices[i]);
        
        return person.getCustomMBTI();
    }
}

class Person {
    int[] rt = new int[2];
    int[] cf = new int[2];
    int[] jm = new int[2];
    int[] an = new int[2];
    
    public Person() {
        rt[0] = rt[1] = cf[0] = cf[1] = jm[0] = jm[1] = an[0] = an[1] = 0;
    }
    
    public void calculateSurvey(String survey, int choice) {
        if (choice == 4) return;
        
        int idx = choice / 4;
        int point = choice % 4;
        
        if(idx == 0) point = reversePoint(point);
        
        if(survey.charAt(0) == 'R') rt[idx] += point;
        if(survey.charAt(0) == 'T') rt[1 - idx] += point;
        
        if(survey.charAt(0) == 'C') cf[idx] += point;
        if(survey.charAt(0) == 'F') cf[1 - idx] += point;
        
        if(survey.charAt(0) == 'J') jm[idx] += point;
        if(survey.charAt(0) == 'M') jm[1 - idx] += point;
        
        if(survey.charAt(0) == 'A') an[idx] += point;
        if(survey.charAt(0) == 'N') an[1 - idx] += point;
    }
    
    private int reversePoint(int point) {
        return 4 - point;
    }
    
    public String getCustomMBTI() {
        String result = "";
        
        result += (rt[0] >= rt[1]) ? "R" : "T";
        result += (cf[0] >= cf[1]) ? "C" : "F";
        result += (jm[0] >= jm[1]) ? "J" : "M";
        result += (an[0] >= an[1]) ? "A" : "N";
        
        return result;
    }
    
    public void print() {
        System.out.println("[RT Point] => (" + this.rt[0] + "," + this.rt[1] + ")");
        System.out.println("[CF Point] => (" + this.cf[0] + "," + this.cf[1] + ")");
        System.out.println("[JM Point] => (" + this.jm[0] + "," + this.jm[1] + ")");
        System.out.println("[AN Point] => (" + this.an[0] + "," + this.an[1] + ")");
    }
}