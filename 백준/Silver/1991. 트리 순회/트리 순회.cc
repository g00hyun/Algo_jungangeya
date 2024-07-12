#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <unordered_map>

using namespace std;

#define LEFT first
#define RIGHT second

// 개행문자는 "\n" 사용하기 (endl사용할 시 버퍼와 줄바꿈을 동시에 처리하므로 매우 느림)

int n;
unordered_map<char, pair<char,char>> tree;

void preorder_traverse(char node) {
    pair<char, char> child = tree[node];

    cout << node;
    if(child.LEFT != '.')
        preorder_traverse(child.LEFT);
    if(child.RIGHT != '.')
        preorder_traverse(child.RIGHT);
}

void inorder_traverse(char node) {
    pair<char, char> child = tree[node];

    if(child.LEFT != '.')
        inorder_traverse(child.LEFT);
    cout << node;
    if(child.RIGHT != '.')
        inorder_traverse(child.RIGHT);
}

void postorder_traverse(char node) {
    pair<char, char> child = tree[node];

    if(child.LEFT != '.')
        postorder_traverse(child.LEFT);
    if(child.RIGHT != '.')
        postorder_traverse(child.RIGHT);
    cout << node;
}

int main() {
    ios::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);
    cin >> n;
    for(int i = 0; i < n; i++) {
        char node, left, right;
        cin >> node >> left >> right;

        tree[node] = {left, right};
    }

    preorder_traverse('A');
    cout << endl;
    inorder_traverse('A');
    cout << endl;
    postorder_traverse('A');
}