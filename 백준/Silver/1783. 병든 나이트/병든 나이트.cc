#include <iostream>

using namespace std;

int main() {
	int N, M;
	int move = 0;
	
	cin >> N >> M;
	
	if(N==1) {
		move = 1;
	}
	else if(N==2) {
		move = 1 + (M-1)/2;
		if(move > 4)
			move = 4;
	}
	else {
		if(M<=4)
			move = M;
		else if(5<=M && M<=6)
			move = 4;
		else
			move = M-2;
	}
	
	cout << move << endl;
}