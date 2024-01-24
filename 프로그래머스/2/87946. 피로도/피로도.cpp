#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int solution(int k, vector<vector<int>> dungeons) {
    int answer = -1;
    
    vector<int> test;
    for(int i = 0; i<dungeons.size(); i++)
        test.push_back(i);
    
    do {
        int val = k;
        int cnt = 0;
        for(int i = 0; i<dungeons.size(); i++) {
            if(val >= dungeons[test[i]][0]) {
                val -= dungeons[test[i]][1];
                cnt++;
            }
            answer = max(answer, cnt);
        }
    } while(next_permutation(test.begin(), test.end()));
            
    
    return answer;
}