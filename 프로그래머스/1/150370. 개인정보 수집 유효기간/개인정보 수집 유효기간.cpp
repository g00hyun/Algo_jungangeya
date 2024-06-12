#include <string>
#include <vector>
#include <unordered_map>
#include <iostream>

using namespace std;

unordered_map<char, int> un_m;

bool isDelete(string today, string dday) {
    int today_year = stoi(today.substr(0, 4));
    int dday_year = stoi(dday.substr(0,4));
    
    int today_month = stoi(today.substr(5,2));
    int dday_month = stoi(dday.substr(5,2));
    
    int today_date = stoi(today.substr(8,2));
    int dday_date = stoi(dday.substr(8,2));
    
    if(today_year == dday_year) {
        if(today_month == dday_month) {
            if(today_date >= dday_date) return true;
            else return false;
        }
        else if(today_month > dday_month)
            return true;
        else
            return false;
    }
    else if(today_year > dday_year)
        return true;
    else
        return false;
}

vector<int> solution(string today, vector<string> terms, vector<string> privacies) {
    vector<int> answer;
    
    for(string s : terms) {
        char c = s[0];
        
        int month;
        string tmp;
        tmp.append(s.substr(2));
        
        un_m[c] = stoi(tmp);
    }
    
    int idx = 1;
    for(string s : privacies) {
        int year = stoi(s.substr(0, 4));
        int month = stoi(s.substr(5,2));
        
        int term = un_m[s[11]];
        
        month += term;
        
        if(month > 12) {
            int cnt = month / 12;
            month %= 12;
            year += cnt;
            
            if(month == 0)
                month = 12, year--;
        }
        
        string newMonth;
        if(month < 10)
            newMonth.push_back('0');
        
        newMonth.append(to_string(month));
        
        s.replace(0,4,to_string(year));
        s.replace(5,2,newMonth);
        
        if(isDelete(today, s.substr(0,10)))
            answer.push_back(idx);
        
        idx++;
    }
    
    return answer;
}