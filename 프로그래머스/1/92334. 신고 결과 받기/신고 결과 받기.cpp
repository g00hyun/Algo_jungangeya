#include <string>
#include <vector>
#include <iostream>
#include <set>
#include <unordered_map>

using namespace std;

vector<set<string>> v;
unordered_map<string,int> loc;

vector<int> solution(vector<string> id_list, vector<string> report, int k) {
    vector<int> answer(id_list.size());
    
    for(auto i : id_list)
        v.push_back(set<string>());
    
    for(int i = 0; i<id_list.size(); i++)
        loc[id_list[i]] = i;
    
    for(string s : report) {
        int idx = 0;
        for(idx = 0; idx<s.size(); idx++)
            if(s[idx] == ' ') break;
        
        string s1 = s.substr(0,idx);
        string s2 = s.substr(idx+1);
        
        int saveIdx = loc[s1];
        v[saveIdx].insert(s2);
    }
    
    vector<int> banCnt(id_list.size());
    
    for(auto it : v)
        for(auto i : it)
            banCnt[loc[i]]++;
    
    vector<int> banIdx;
    
    for(int i = 0; i<banCnt.size(); i++) {
        if(banCnt[i] >= k) {
            banIdx.push_back(i);
        }
    }
    
    int idx = 0;
    for(auto it : v) {
        for(int i : banIdx)
            if(it.find(id_list[i]) != it.end())
                answer[idx]++;
        idx++;
    }
    
    return answer;
}