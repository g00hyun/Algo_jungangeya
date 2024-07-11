#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <map>

using namespace std;

int n,m;
vector<int> v;
int arr[8];
bool visited[8];
map<vector<int>, int> list;

void Backtracking() {
    if(v.size() == m) {
        if(list.find(v) != list.end())
            return;

        list[v] = 1;

        for(auto i : v)
            cout << i << ' ';
        cout << '\n'; // 왜 \n으로 해줘야할까..?

        return;
    }

    for(int i = 0; i<n; i++) {
        if(visited[i]) continue;

        visited[i] = true;
        v.push_back(arr[i]);
        Backtracking();
        v.pop_back();
        visited[i] = false;
    }
}

// 개행문자는 "\n" 사용하기 (endl사용할 시 버퍼와 줄바꿈을 동시에 처리하므로 매우 느림)

int main() {
    ios::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);
    cin >> n >> m;
    for(int i = 0; i<n; i++)
        cin >> arr[i];

    sort(arr, arr+n);

    Backtracking();
}