#include <iostream>
#include <vector>
#include <algorithm>
#include <set>

using namespace std;

int n,k;
vector<string> words;
vector<string> v[21];

int main() {
	cin >> n >> k;
	
	for(int i = 0; i<n; i++) {
		string tmp;
		cin >> tmp;
		
		// words.push_back(tmp);
		v[tmp.size()].push_back(tmp);
	}
	
	
	for(int i = 1; i<=20; i++)
		sort(v[i].begin(), v[i].end());
	
	words.insert(words.begin(), v[1].begin(), v[1].end());	
	for(int i = 2; i<=20; i++) {
	words.insert(words.end(), v[i].begin(), v[i].end());
	}
	
	cout << words[k-1];
	
	return 0;
}