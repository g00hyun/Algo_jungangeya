#include <string>
#include <vector>
#include <algorithm>
#include <iostream>
#include <set>
#include <climits>

using namespace std;

set<string> s;
int mini = INT_MAX;

bool solution(vector<string> phone_book) {
    bool answer = true;
    
    sort(phone_book.begin(), phone_book.end());
    
    for(auto p : phone_book) {
        for(int i = mini; i<p.size(); i++) {
            string st = p.substr(0,i);
            if(s.find(st) != s.end()) return false;
        }
        
        s.insert(p);
        mini = p.size();
    }
    
    return true;
}