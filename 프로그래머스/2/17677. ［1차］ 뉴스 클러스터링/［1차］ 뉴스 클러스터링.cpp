#include <string>
#include <iostream>
#include <algorithm>
#include <vector>
#include <map>

using namespace std;

vector<string> v1;
vector<string> v2;
map<string, int> m;

int solution(string str1, string str2) {
    int answer = 0;
    
    transform(str1.begin(), str1.end(), str1.begin(), ::toupper);
    transform(str2.begin(), str2.end(), str2.begin(), ::toupper);
    
    for(int i = 0; i<str1.size() - 1; i++)
        if((isalpha(str1[i]) != 0) && (isalpha(str1[i+1])) != 0)
            v1.push_back(str1.substr(i,2));
    
    for(int i = 0; i<str2.size() - 1; i++)
        if((isalpha(str2[i]) != 0) && (isalpha(str2[i+1])) != 0)
            v2.push_back(str2.substr(i,2));
    
    // sort(v1.begin(), v1.end());
    // sort(v2.begin(), v2.end());
    
    for(auto i : v1)
        m[i]++;
    
    
    
    int un, in;
    un = in = 0;
    for(auto i : v2) {
        // m[i]--;
        if(m.find(i) != m.end())
            if(m.find(i)->second > 0)
                in++;
        m[i]--;
    }
    
    un += in;
    
    for(auto i : m)
        if(i.second != 0)
            un += abs(i.second);
    
    cout << in << ' ' << un;
    
    if(un == 0) answer = 1 * 65536;
    else answer = (float)in/un * 65536;
    
    return answer;
}