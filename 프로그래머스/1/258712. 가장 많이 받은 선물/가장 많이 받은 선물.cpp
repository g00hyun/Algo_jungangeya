#include <string>
#include <vector>
#include <unordered_map>
#include <iostream>
#include <map>
#include <algorithm>

using namespace std;

unordered_map<string, int> un_m;
map<pair<string, string>, int> m;
vector<vector<int>> ffriends;


void CheckGiftPoint(vector<string> gifts) {
    for(int i = 0; i<gifts.size(); i++) {
        string s1, s2;
        
        int idx;
        for(idx = 0; idx<gifts[i].size(); idx++)
            if(gifts[i][idx] == ' ') break;
        
        s1 = gifts[i].substr(0, idx);
        s2 = gifts[i].substr(idx+1);
        
        un_m[s1]++;
        un_m[s2]--;
    }
}

void CheckGiveAndTake(vector<string> friends, vector<string> gifts) {
    for(int i = 0; i<gifts.size(); i++) {
        string s1, s2;
        
        int idx;
        for(idx = 0; idx<gifts[i].size(); idx++)
            if(gifts[i][idx] == ' ') break;
        
        s1 = gifts[i].substr(0, idx);
        s2 = gifts[i].substr(idx+1);
        
        int idx1, idx2;
        for(idx1 = 0; idx1 < friends.size(); idx1++)
            if(friends[idx1] == s1) break;
        
        for(idx2 = 0; idx2 < friends.size(); idx2++)
            if(friends[idx2] == s2) break;
        
        ffriends[idx1][idx2]++;
    }
}


int solution(vector<string> friends, vector<string> gifts) {
    int answer = 0;
    
    for(int i = 0; i<friends.size(); i++)
        un_m[friends[i]] = 0;
    
    for(int i = 0; i<friends.size(); i++) {
        ffriends.push_back(vector<int>());
        for(int j = 0; j<friends.size(); j++)
            ffriends[i].push_back(0);
    }
    
    CheckGiveAndTake(friends, gifts);
    
    CheckGiftPoint(gifts);
    
    for(int i = 0; i<friends.size(); i++) {
        int cnt = 0;
        for(int j = 0; j<friends.size(); j++) {
            if(i == j) continue;
            
            if(ffriends[i][j] == ffriends[j][i]) {
                if(un_m.find(friends[i])->second > un_m.find(friends[j])->second)
                    cnt++;
            }
            else if(ffriends[i][j] > ffriends[j][i])
                cnt++;
        }
        
        answer = max(answer, cnt);
    }
    
    
    return answer;
}