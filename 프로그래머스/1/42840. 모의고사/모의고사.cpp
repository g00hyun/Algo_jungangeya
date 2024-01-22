#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

int a[5] = {1,2,3,4,5};
int b[8] = {2,1,2,3,2,4,2,5};
int c[10] = {3,3,1,1,2,2,4,4,5,5};
int cnt[3];

vector<int> solution(vector<int> answers) {
    vector<int> answer;
    
    for(int i = 0; i<answers.size(); i++) {
        if(answers[i] == a[i%5]) cnt[0]++;
        if(answers[i] == b[i%8]) cnt[1]++;
        if(answers[i] == c[i%10]) cnt[2]++;
    }
    
    int m = -1;
    for(int i = 0; i<3; i++)
        m = max(m, cnt[i]);
    
    for(int i = 0; i<3; i++)
        if(m == cnt[i])
            answer.push_back(i+1);
    
    return answer;
}