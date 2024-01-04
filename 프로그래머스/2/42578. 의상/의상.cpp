#include <string>
#include <vector>
#include <iostream>
#include <map>

using namespace std;

map<string, int> m;

int solution(vector<vector<string>> clothes) {
    int answer = 1;
    
    for(auto &c : clothes)
        m[c[1]]++;
    
    for (auto i : m)
        answer *= i.second + 1;
    
    
    return answer - 1;
}