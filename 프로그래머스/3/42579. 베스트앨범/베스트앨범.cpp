#include <string>
#include <vector>
#include <iostream>
#include <map>
#include <algorithm>
#include <functional>

using namespace std;

map<string, int> playsOnGenre;
vector<pair<int,pair<string, int>>> pairs;

bool compare(const pair<int,pair<string, int>> &a, const pair<int,pair<string, int>> &b) {
    if (playsOnGenre[a.second.first] == playsOnGenre[b.second.first]) {
        if (a.second.second == b.second.second) {
            return a < b;
        }
        return a.second.second > b.second.second;
    }
    return playsOnGenre[a.second.first] > playsOnGenre[b.second.first];
        
}

vector<int> solution(vector<string> genres, vector<int> plays) {
    vector<int> answer;
    
    for(int i = 0; i<genres.size(); i++) {
        playsOnGenre[genres[i]] += plays[i];
        pairs.push_back(make_pair(i,make_pair(genres[i], plays[i])));
    }
    
    sort(pairs.begin(), pairs.end(), compare);
    
    for(auto& i : pairs)
        cout << i.first << ' ' << i.second.first << ' ' << i.second.second << endl;
    
    string s = "";
    int cnt = 0;
    for(auto& i : pairs) {
        if (s.compare(i.second.first) == 0) {
            cnt++;
        }
        else {            
            s = i.second.first, cnt = 0;
        }
        
        if (cnt < 2)
            answer.push_back(i.first);
    }
    
    
    
    return answer;
}