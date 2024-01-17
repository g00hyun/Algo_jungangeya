#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <algorithm>

using namespace std;

int otoi(string s) {
    string str;
    for(int i = 2; i<s.size(); i++)
        str += s[i];
    return stoi(str);
}

vector<int> solution(vector<string> operations) {
    vector<int> answer;
    
    deque<int> d;
    
    for(auto s : operations) {
        // int n = otoi(s);
        
        if(s == "D 1") {
            if(!d.empty())
                d.pop_back();   
        }   
        else if(s == "D -1") {
            if(!d.empty())
                d.pop_front();   
        }
        else {
            d.push_back(stoi(s.substr(2)));
            sort(d.begin(), d.end());
        }
    }
    
    if (d.empty()) {
        answer.push_back(0);
        answer.push_back(0);        
    }
    else {
        answer.push_back(d.back());
        answer.push_back(d.front());
    }
    
    return answer;
}