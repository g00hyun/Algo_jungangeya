#include <iostream>
#include <cstring>

using namespace std;

// 개행문자는 "\n" 사용하기 (endl사용할 시 버퍼와 줄바꿈을 동시에 처리하므로 매우 느림)

int arr[9];
bool check[9];
int sum;

int main() {
    ios::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);

    for (int i = 0; i < 9; i++)
        cin >> arr[i];

    for(int a = 0; a<9; a++)
    for(int b = a+1; b<9; b++)
    for(int c = b+1; c<9; c++)
    for(int d = c+1; d<9; d++)
    for(int e = d+1; e<9; e++)
    for(int f = e+1; f<9; f++)
    for(int g = f+1; g<9; g++)
        if(arr[a] + arr[b] + arr[c] + arr[d] + arr[e] + arr[f] + arr[g] == 100) {
            printf("%d\n%d\n%d\n%d\n%d\n%d\n%d\n", arr[a], arr[b], arr[c], arr[d], arr[e], arr[f], arr[g]);
            return 0;
        }
    
}