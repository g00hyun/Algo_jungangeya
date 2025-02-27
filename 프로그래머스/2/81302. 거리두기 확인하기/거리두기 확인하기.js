class Queue {
    constructor() {
        this.q = []
        this.head = -1
        this.tail = -1
    }
    
    empty() {
        return this.head === this.tail
    }
    
    push(e) {
        this.q.push(e)
        this.tail++
    }
    
    pop() {
        if(this.empty())
            throw new Error("Queue is Empty!")
        return this.q[++this.head]
    }
    
    front() {
        if(this.empty())
            throw new Error("Queue is Empty!")
        return this.q[this.head + 1]
    }
    
    size() {
        return this.tail - this.head
    }
}

function solution(places) {
    let answer = [];
    
    places = places.map(v => v.map(v2 => v2.split('')))
    let step;
    const q = new Queue();
    
    const InRange = (x,y) => 0 <= x && x < 5 && 0 <= y && y < 5;
    const CanGo = (x,y,arr) => {
        if(!InRange(x,y)) return false;
        if(step[x][y] !== 0) return false;
        if(arr[x][y] === 'X') return false;
        return true;
    }
    const BFS = (arr) => {
        const dx = [-1,1,0,0], dy = [0,0,1,-1]
        
        while(!q.empty()) {
            const [x,y] = q.pop();
            
            for(let dir = 0; dir < 4; dir++) {
                const nx = x + dx[dir], ny = y + dy[dir]
                if(CanGo(nx,ny,arr) && step[x][y] < 3) {
                    if(arr[nx][ny] === 'P') return false;
                    q.push([nx,ny])
                    step[nx][ny] = step[x][y] + 1;
                }
            }
        }
        
        return true;
    }
    
    places.forEach(arr => {
        step = Array(5).fill().map(v => Array(5).fill(0))
        
        let isDiv = true
        for(let i = 0; i<5; i++)
            for(let j = 0; j<5; j++)
                if(arr[i][j] === 'P') {
                    q.push([i,j])
                    step[i][j] = 1
                    if(!BFS(arr)) {
                        isDiv = false;
                    }
                }
        
        isDiv ? answer.push(1) : answer.push(0)
    })
        
    return answer;
}