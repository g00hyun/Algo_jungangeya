#include <string>
#include <vector>
#include <algorithm>
#include <iostream>
#include <queue>
#include <string.h>

using namespace std;

// vector<int> map[100];
int unionfind[100];

bool cmp(vector<int> a, vector<int> b) {
    return a[2] < b[2];
}

int solution(int n, vector<vector<int>> costs) {
    int answer = 0;
    
    sort(costs.begin(), costs.end(), cmp);
    
    for(int i = 0; i<n; i++)
        unionfind[i] = i;
    
    for(auto i : costs) {
        if(unionfind[i[0]] == unionfind[i[1]]) continue;
        
        // map[i[0]].push_back(i[1]);
        // map[i[1]].push_back(i[0]);
        answer += i[2];

        int root = min(unionfind[i[0]], unionfind[i[1]]);
        int other = max(unionfind[i[0]], unionfind[i[1]]);

        for(int i = 0; i<n; i++)
            if(unionfind[i] == other)
                unionfind[i] = root;
    }
    
    return answer;
}