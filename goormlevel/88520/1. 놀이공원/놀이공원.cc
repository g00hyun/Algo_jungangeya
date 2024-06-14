// #include<cstdio>
// #include<cstdlib>
// #include<memory.h>
#include<iostream>
#include<algorithm>
#include <climits>

using namespace std;

#define MAP_MAX_N 100

int t,n,k;
int MAP[MAP_MAX_N][MAP_MAX_N];

int CountingNuclear(int x, int y) {
	int cnt = 0;
	
	for(int i = x; i<x+k; i++)
		for(int j = y; j<y+k; j++)
			if(MAP[i][j] == 1)
				cnt++;
	
	return cnt;
}

int main() {
	cin >> t;
	
	while(t--) {
		cin >> n >> k;
		for(int i = 0; i<n; i++)
			for(int j = 0; j<n; j++)
				cin >> MAP[i][j];

		int minimum = INT_MAX;
		for(int i = 0; i<n-k+1; i++)
			for(int j = 0; j<n-k+1; j++)
				minimum = min(minimum, CountingNuclear(i,j));

		cout << minimum << endl;
	}
	
	
	
	return 0;
}


// int get_minimum_trashes(int **waste, int N, int K) {
// 	int answer = K * K;


// 	return answer;
// }

// void test_case(int caseIndex) {
// 	int N, K;
// 	scanf("%d %d", &N, &K);

// 	int **wastes = new int *[N];
// 	for (int r = 0; r < N; r += 1) {
// 		wastes[r] = new int[N];
// 		for (int c = 0; c < N; c += 1) {
// 			scanf("%d", &wastes[r][c]);
// 		}
// 	}

// 	int answer = get_minimum_trashes(wastes, N, K);
// 	printf("%d\n", answer);

// 	for (int r = 0; r < N; r += 1) {
// 		delete[] wastes[r];
// 	}
// 	delete[] wastes;
// }

// int main() {
// 	int caseSize;
// 	scanf("%d", &caseSize);

// 	for (int caseIndex = 0; caseIndex < caseSize; caseIndex += 1) {
// 		test_case(caseIndex);
// 	}
// 	return 0;
// }