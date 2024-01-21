#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

int solution(vector<int> citations) {
    int answer = 0;
    
    sort(citations.begin(), citations.end(), greater<>());
    
    for(auto i : citations)
        cout << i << ' ';
    
    int maxhidx = 1;
    for(int i = 0; i<citations.size(); i++) {
        
        if(i+1 <= citations[i]) {
            // maxhidx = hidx;
            answer++;
        }
    }
    
    return answer;
}