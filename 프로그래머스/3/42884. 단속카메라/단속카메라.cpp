#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

bool cmp(vector<int> a, vector<int> b) {
    return a[1] < b[1];
}

int solution(vector<vector<int>> routes) {
    int answer = 0;
    
    sort(routes.begin(), routes.end(), cmp);
    
    for(int i = 0; i<routes.size(); i++) {
        int a,b;
        a = routes[i][0], b = routes[i][1];
        
        while(i+1 < routes.size()) {
            if(b >= routes[i+1][0]) {
                i++;
            }
            else break;
        }
        answer++;
    }
    
    return answer;
}