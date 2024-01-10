#include <string>
#include <vector>

using namespace std;

int n;
bool complete[100];

bool isComplete() {
    for(int i = 0; i<n; i++)
        if(!complete[i])
            return false;
    return true;
}

vector<int> solution(vector<int> progresses, vector<int> speeds) {
    vector<int> answer;
    
    n = speeds.size();
    
    int idx = 0;
    int nidx = 0;
    while (!isComplete()) {
        for (int i = 0; i<n; i++) {
            progresses[i] += speeds[i];
            if (progresses[i] >= 100)
                complete[i] = true;
        }
        
        if (complete[idx]) {
            while(complete[nidx])
                nidx++;
            
            int diff = nidx - idx;
            answer.push_back(diff);
            idx = nidx;
        }
    }
    
    return answer;
}