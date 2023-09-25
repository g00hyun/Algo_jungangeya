#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

// 개행문자는 "\n" 사용하기 (endl사용할 시 버퍼와 줄바꿈을 동시에 처리하므로 매우 느림)
int n, m;
vector<int> v;
bool check[9];

void recursive() {
    if (v.size() == m){
        for (auto &&i : v)
            cout << i << ' ';
        cout << '\n';
        return ;
    }

    for (int i = 1; i<=n; i++){
        if(check[i] == true) continue;
        
        v.push_back(i);
        check[i] = true;
        recursive();
        v.pop_back();
        check[i] = false;
    }
}

int main() {
    ios::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);

    cin >> n >> m;

    recursive();
    
}