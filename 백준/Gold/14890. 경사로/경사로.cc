// gpt한테 코드 리팩토링 맡긴 코드

#include <iostream>
#include <vector>

using namespace std;

#define MAP_MAX_N 100

int n, l;
int MAP[MAP_MAX_N][MAP_MAX_N];

bool inRange(int x, int y) {
    return 0 <= x && x < n && 0 <= y && y < n;
}

// 경사로 설치 가능 여부를 확인하는 함수
bool canInstallRamp(int *line, int pos, int len, int direction) {
    for (int i = 0; i < l; ++i) {
        int index = pos + i * direction;
        if (index < 0 || index >= n || line[index] != len) {
            return false;
        }
    }
    return true;
}

// 경사로 설치 상태를 업데이트하는 함수
void installRamp(int *visited, int pos, int direction) {
    for (int i = 0; i < l; ++i) {
        visited[pos + i * direction] = 1;
    }
}

// 가로 및 세로 방향 경로를 확인하는 함수
bool checkPath(int *line) {
    int visited[n] = {0};

    for (int i = 0; i < n - 1; ++i) {
        if (line[i] == line[i + 1]) {
            continue;
        }

        if (line[i] + 1 == line[i + 1]) { // 오르막길
            if (i - l + 1 < 0 || !canInstallRamp(line, i - l + 1, line[i], 1) || visited[i - l + 1] == 1) {
                return false;
            }
            installRamp(visited, i - l + 1, 1);
        } else if (line[i] - 1 == line[i + 1]) { // 내리막길
            if (i + l >= n || !canInstallRamp(line, i + 1, line[i + 1], 1) || visited[i + 1] == 1) {
                return false;
            }
            installRamp(visited, i + 1, 1);
        } else {
            return false;
        }
    }

    return true;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n >> l;
    for (int i = 0; i < n; ++i)
        for (int j = 0; j < n; ++j)
            cin >> MAP[i][j];

    int answer = 0;

    // 가로 경로 확인
    for (int row = 0; row < n; ++row) {
        if (checkPath(MAP[row])) {
            answer++;
        }
    }

    // 세로 경로 확인
    for (int col = 0; col < n; ++col) {
        int columnPath[n];
        for (int row = 0; row < n; ++row) {
            columnPath[row] = MAP[row][col];
        }
        if (checkPath(columnPath)) {
            answer++;
        }
    }

    cout << answer << "\n";
    return 0;
}