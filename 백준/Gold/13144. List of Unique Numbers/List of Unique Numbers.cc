#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <unordered_set>

using namespace std;

// 개행문자는 "\n" 사용하기 (endl사용할 시 버퍼와 줄바꿈을 동시에 처리하므로 매우 느림)
int n;
int arr[100000];
unordered_set<int> un_s;

int main() {
    ios::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);
    cin >> n;
    for(int i = 0; i<n; i++)
        cin >> arr[i];

    int j = 0;
    long long answer = 0;
    int left = 0;
    for(int i = 0; i<n; i++) {
        while(j < n && un_s.find(arr[j]) == un_s.end()) {
            un_s.insert(arr[j]);
            left++;
            j++;
        }

        int size = j - i;
        
        while(left > 0) {
            answer += size;
            size--, left--;
        }

        un_s.erase(arr[i]);

        
    }
    cout << answer;
}