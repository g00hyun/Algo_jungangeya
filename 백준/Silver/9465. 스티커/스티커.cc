#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

// 개행문자는 "\n" 사용하기 (endl사용할 시 버퍼와 줄바꿈을 동시에 처리하므로 매우 느림)

int main() {
    ios::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);
    int t;
    cin >> t;

    while(t--) {
        int n;
        cin >> n;

        vector<int> v[2];

        for(int idx = 0; idx<2; idx++) {
            for(int i = 0; i<n; i++) {
                int tmp;
                cin >> tmp;

                v[idx].push_back(tmp);
            }
        }

        v[0][1] += v[1][0];
        v[1][1] += v[0][0];
        for(int i = 2; i<n; i++) {
            v[0][i] += max(v[1][i-1], v[1][i-2]);
            v[1][i] += max(v[0][i-1], v[0][i-2]);
        }

        cout << max(v[0][n-1], v[1][n-1]) << endl;
    }
}