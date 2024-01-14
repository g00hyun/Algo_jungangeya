#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <queue>
using namespace std;

int solution(vector<int> scoville, int K) {
    int answer = 0;

    //priority_queue<자료형, 구현체, 비교연산자> pq;
    priority_queue<int, vector<int>, greater<int>> pq;

    for (int i = 0; i < scoville.size(); i++) {
        pq.push(scoville[i]);
    }
    
    if(pq.top() >= K) return 0;

    int k = scoville.size() - 1;
    while (k--) {
        int v1 = pq.top();
        pq.pop();
        int v2 = pq.top();
        pq.pop();

        pq.push(v1 + (v2 * 2));

        answer++;

        if (pq.top() >= K) {
            return answer;
        }
    }
    return -1;
}