#include <string>
#include <vector>
#include <functional>
#include <iostream>

using namespace std;

// bool cmp(const string& n1, const string& n2) {
//     // n1 > n2 -> 내림차순
//     int idx1 = 0;
//     int idx2 = 0;
    
//     while(true) {
//         if (n1[idx1] == n2[idx2]) {
//             if(idx1 < n1.size() - 1)
//                 idx1++;
//             if(idx2 < n1.size() - 1)
//                 idx2++;
//         }
//         else {
//             return n1[idx1] > n2[idx2];
//         }
        
//         if((idx1 == n1.size() - 1) && (idx2 == n2.size() - 1))
//             break;
//     }
// }

// bool cmp(const string& n1, const string& n2) {
//     int v1 = stoi(n1 + n2);
//     int v2 = stoi(n2 + n1);
    
//     if(v1 > v2) return 1;
//     else if(v1 < v2) return -1;
//     else return 0;
// }

bool cmp(const string& n1, const string& n2) {
    return n1 + n2 > n2 + n1;
}

string solution(vector<int> numbers) {
    string answer = "";
    
    vector<string> strs;
    for(auto i : numbers)
        strs.push_back(to_string(i));
    
    sort(strs.begin(), strs.end(), cmp);
    
    if (strs.at(0) == "0") return "0";
    
    for(auto i : strs) {
        // cout << i << ' ';
        answer += i;
    }
    
    return answer;
}