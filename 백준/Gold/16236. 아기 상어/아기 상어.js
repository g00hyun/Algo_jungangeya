class Queue {
    constructor() {
        this.q = []
        this.head = -1
        this.tail = -1
    }
    
    push(e) {
        this.q.push(e);
        this.tail++;
    }
    
    empty() {
        return this.head === this.tail;
    }
    
    pop() {
        if(this.empty())
            throw new Error("Queue is empty!")
        return this.q[++this.head];
    }
    
    size() {
        return this.tail - this.head
    }
    
    front() {
        if(this.empty())
            throw new Error("Queue is empty!")
        return this.q[this.head + 1]
    }
}

const Solution = (n, arr) => {
    const InRange = (x,y) => 0 <= x && x < n && 0 <= y && y < n;

    const CanGo = (x,y,visited) => {
        if(!InRange(x,y)) return false;
        if(arr[x][y] > size) return false;
        if(visited[x][y]) return false;
        return true;
    }
    
    const BFS = (x,y) => {
        const q = new Queue();
        const step = Array(n).fill().map(v => Array(n).fill(0))
        const visited = Array(n).fill().map(v => Array(n).fill(false))

        q.push([x,y]);
        visited[x][y] = true;

        const dx = [-1,0,0,1], dy = [0,-1,1,0]
        
        while(!q.empty()) {
            const [x,y] = q.pop();
            // console.log(`now cur is (${x},${y})`)
            if(x === distX && y === distY) {
                // console.log(step.map(v => v.join(' ')).join('\n'))
                return step[x][y];
            } 
            
            for(let i = 0; i<4; i++) {
                const nx = x + dx[i], ny = y + dy[i];
                if(CanGo(nx, ny,visited)) {
                    q.push([nx,ny])
                    visited[nx][ny] = true;
                    step[nx][ny] = step[x][y] + 1;
                }
            }
        }
        
        return -1;
    }

    let t = 0;
    
    let distX, distY;
    let size = 2;
    let exp = 0;
    const diffs = []
    for(let i = 0; i<n; i++)
        for(let j = 0; j<n; j++)
            if(arr[i][j] === 9)
                distX = i, distY = j;

    arr[distX][distY] = 0;
    
    // O(N^6)
    while(1) {
        for(let i = 0; i<n; i++)
            for(let j = 0; j<n; j++)
                if(0 < arr[i][j] && arr[i][j] < size) {
                    // console.log(i,j)
                    const diff = BFS(i,j);
                    // console.log(diff)

                    if(diff === -1) continue;

                    diffs.push([i,j,diff]);
                }

        if(diffs.length === 0) return t;

        diffs.sort((a,b) => a[2] - b[2]);
        distX = diffs[0][0], distY = diffs[0][1];
        t += diffs[0][2];
        arr[distX][distY] = 0;
        exp++;
        if(exp >= size) {
            exp = 0, size++;
        }
        diffs.splice(0)
    }
}

const fs = require('fs')
const input = fs.readFileSync(0).toString().trim().split('\n')

const n = Number(input[0])
const arr = input.slice(1).map(v => v.split(' ').map(Number))

const answer = Solution(n, arr)

// const answer = Solution(6, [[5,4,3,2,3,4],[4,3,2,3,4,5],[3,2,9,5,6,6],[2,1,2,3,4,5],[3,2,1,6,5,4],[6,6,6,6,6,6]])
// const answer = Solution(4, [[4,3,2,1],[0,0,0,0],[0,0,9,0],[1,2,3,4]])
console.log(answer)