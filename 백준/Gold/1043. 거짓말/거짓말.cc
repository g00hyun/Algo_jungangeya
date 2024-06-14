#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <queue>

using namespace std;

int n,m;
int k;
bool truth[51];

// 개행문자는 "\n" 사용하기 (endl사용할 시 버퍼와 줄바꿈을 동시에 처리하므로 매우 느림)
vector<int> graph[51];
bool visited[51];
queue<int> q;

void BFS() {
    while(!q.empty()) {
        int node = q.front();
        q.pop();

        for(int i = 0; i<graph[node].size(); i++) {
            int nextNode = graph[node][i];

            if(!visited[nextNode]) {
                visited[nextNode] = true;
                q.push(nextNode);
                truth[nextNode] = true;
            }
        }
    }
}

int main() {
    ios::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);
    cin >> n >> m;
    cin >> k;
    for(int i = 0; i<k; i++) {
        int t;
        cin >> t;
        truth[t] = true;
    }


    vector<vector<int>> party;
    for(int i = 0; i<m; i++) {
        vector<int> tmp;

        int party_n;
        cin >> party_n;

        for(int j = 0; j<party_n; j++) {
            int people;
            cin >> people;

            tmp.push_back(people);
        }
        party.push_back(tmp);

        // cout << "tmp..." << endl;
        // for(int t : tmp)
        //     cout << t << ' ';
        // cout << endl;

        if(tmp.size() == 2)
            graph[tmp[0]].push_back(tmp[1]), graph[tmp[1]].push_back(tmp[0]);
        else if(tmp.size() > 2) {
            for(int p1 = 0; p1<tmp.size()-1; p1++) {
                for(int p2 = p1+1; p2<tmp.size(); p2++){
                    graph[tmp[p1]].push_back(tmp[p2]);
                    graph[tmp[p2]].push_back(tmp[p1]);
                }
            }
        }
    }

    // for(int i = 1; i<=n; i++) {
    //     cout << "party(" << i << ") =>";
    //     for(int j : graph[i])
    //         cout << j << ' ';
    //     cout << endl;
    // }

    // truth 테이블 채우는 작업
    for(int i = 1; i<=n; i++)
        if(truth[i])  {
            q.push(i);
            visited[i] = true;
        }

    BFS();

    // for(int i = 1; i<=n; i++)
    //     if(truth[i])
    //         cout << i << ' ';
    // cout << endl;


    // for(auto it : party) {
    //     bool isTruth = false;
    //     for(int i : it)
    //         if(truth[i]) isTruth = true;
        
    //     if(isTruth)
    //         for(int i : it)
    //             truth[i] = true;
    // }

    // for(int i = party.size()-1; i>=0; i--) {
    //     bool isTruth = false;
    //     for(int j : party[i])
    //         if(truth[j]) isTruth = true;
        
    //     if(isTruth)
    //         for(int j : party[i])
    //             truth[j] = true;

    // }

    // for(auto it : party) {
    //     for(int i : it)
    //         cout << i << ' ';
    //     cout << endl;
    // }
    // cout << party.size();

    int result = 0;
    for(auto it : party) {
        bool canLier = true;
        for(int i : it)
            if(truth[i]) canLier = false;
        
        if(canLier) result++;
    }

    cout << result;
}