#include <string>
#include <vector>
#include <iostream>
#include <set>
#include <algorithm>
#include <map>

using namespace std;

set<int> s;

vector<int> solution(vector<vector<int>> edges) {
    vector<int> answer;
    
    vector<int> graph[1000001];
    
    int last = 0;
    
    for(auto i : edges) {
        graph[i[0]].push_back(i[1]);
        s.insert(i[1]);
        
        last = max({last, i[0], i[1]});
    }
    
    map<int,int> in;
    map<int,int> out;
    
    for(int i = 1; i<=last; i++)
        in[i] = out[i] = 0;
    
    for(auto i : edges)
        in[i[0]]++, out[i[1]]++;
    
    int root, one, two, three;
    one = two = three = 0;
    for(int i = 1; i<=last; i++) {
        if(in[i] >= 2 && out[i] == 0) root = i;
        else if(in[i] == 0 && out[i] >= 1) two++;
        else if(in[i] == 2) three++;
        
    }
    
    one = graph[root].size() - two - three;
    
    answer.push_back(root);
    answer.push_back(one);
    answer.push_back(two);
    answer.push_back(three);
    
    
    return answer;
}