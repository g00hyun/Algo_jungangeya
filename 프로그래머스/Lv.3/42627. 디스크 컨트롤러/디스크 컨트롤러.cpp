#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int times = 0;
bool visited[500];
int waiting[500];

bool cmp(vector<int> &v1, vector<int> &v2){
  if(v1[1] == v2[1])
    return v1[0]<v2[0];
  else return v1[1]<v2[1];
}

int solution(vector<vector<int>> jobs) {
    int answer = 0;
    
    sort(jobs.begin(), jobs.end(), cmp);
    
    // sjf
    for(int t = 0; t<jobs.size(); t++) {
        times = 0;
        
        for(int i = 0; i<jobs.size(); i++) {
            if(!visited[i] && jobs[i][0] == 0) {
                visited[i] = true;
                times = jobs[i][1];
                break;
            }
        }
        
        if(times == 0) {
            t--;
            for(int i = 0; i<jobs.size(); i++) {                
                if(visited[i]) continue;
                
                jobs[i][0]--;
            }
            continue;
        }
        
        for(int i = 0; i<jobs.size(); i++) {
            if(visited[i]) continue;
            
            waiting[i] += times - jobs[i][0];
            if(waiting[i] < 0) waiting[i] = 0;
            jobs[i][0] -= times;
            if(jobs[i][0] < 0) jobs[i][0] = 0;
        }
        
        // while(times--) {
        //     for(int i = 0; i<jobs.size(); i++) {
        //         if(!visited[i] && jobs[i][0] == 0) {
        //             waiting[i]++;
        //         }
        //         if(!visited[i] && jobs[i][0] != 0) {
        //             jobs[i][0]--;
        //         }
        //     }
        // }
    }
    
    for(int i = 0; i<jobs.size(); i++) {
        answer += jobs[i][1] + waiting[i];
        cout << jobs[i][1] << ' ' << waiting[i] << endl;
    }
    
    answer /= jobs.size();
    
    
    return answer;
}