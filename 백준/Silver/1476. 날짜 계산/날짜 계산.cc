#include <iostream>

using namespace std;

bool isSame(int e, int s, int m, int e1, int s1, int m1) {
    if(e == e1 && m == m1 && s == s1)
        return true;
    
    return false;
}

int main()
{
    int e,s,m;
    
    cin >> e >> s >> m;
    
    int e1, s1, m1;
    e1 = s1 = m1 = 1;
    
    int cnt = 1;
    int i = 17;
    
    while(!isSame(e,s,m,e1++,s1++,m1++)) {
        cnt++;
        if(e1 >= 16)
            e1 = 1;
        if(s1 >= 29)
            s1 = 1;
        if(m1 >= 20)
            m1 = 1;
    }
    
    cout << cnt;

    return 0;
}
