#include <iostream>
#include <map>

using namespace std;

map<char, char> mirror_word;
int n;

void MakeMirrorWord() {
	mirror_word['b'] = 'd';
	mirror_word['d'] = 'b';
	mirror_word['i'] = 'i';
	mirror_word['l'] = 'l';
	mirror_word['m'] = 'm';
	mirror_word['n'] = 'n';
	mirror_word['o'] = 'o';
	mirror_word['p'] = 'q';
	mirror_word['q'] = 'p';
	mirror_word['s'] = 'z';
	mirror_word['z'] = 's';
	mirror_word['u'] = 'u';
	mirror_word['v'] = 'v';
	mirror_word['w'] = 'w';
	mirror_word['x'] = 'x';
}

int main() {
	MakeMirrorWord();
	
	cin >> n;
	
	for(int i = 0; i<n; i++) {
		string s;
		cin >> s;
		
		bool isMirror = true;
		int size = s.size() / 2;
		for(int j = 0; j<size; j++) {
			if(mirror_word.find(s[j]) == mirror_word.end()) {
				isMirror = false;
				break;
			}
			
			
			if(mirror_word[s[j]] != s[s.size() - 1 - j]) {
				isMirror = false;
				break;
			}
		}
		
		if(mirror_word.find(s[size]) == mirror_word.end())
			isMirror = false;
		
		string tmp = "bdpqsz";
		if(s.size()%2 != 0 && (s[size] == 'b' || s[size] == 'd' || s[size] == 'p' || s[size] == 'q' || s[size] == 's' || s[size] == 'z'))
			isMirror = false;
		
		isMirror ? cout << "Mirror" : cout << "Normal";
		cout << endl;
	}
	
	return 0;
}