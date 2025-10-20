#include <iostream>
#include <algorithm>

using namespace std;

int main(){
	int N;
	int weight[1000];
	int sum = 0;

	cin>>N;

	for(int i = 0; i<N; i++){
		int tmp;
		cin>>tmp;
		weight[i] = tmp;
	}

	sort(weight, weight+N);

	for (int i = 0; i < N; i++) {
		if (weight[i] > sum + 1)
			break;
		sum += weight[i];
	}

	 cout << sum+1;
}
