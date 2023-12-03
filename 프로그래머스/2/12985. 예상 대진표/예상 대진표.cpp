#include <iostream>
#include <algorithm>

using namespace std;

int solution(int n, int a, int b)
{
    int answer = 0;
    
    while(1) {
        if((abs(a-b) == 1) && (a/2 != b/2)) {
            answer++;
            break;
        }
        
        answer++;
        a = (a+1)/2;
        b = (b+1)/2;
    }

    return answer;
}