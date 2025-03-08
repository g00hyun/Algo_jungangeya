const Solution = (a,b,r) => {
    const arr = Array(r).fill().map(v => Array(r).fill(0))
    arr[a][b]++
    let t = 0
    
    while(++t) {
        if(a + b + 2 < r)
            a++, b++
        else
            a = Math.trunc(a/2), b = Math.trunc(b/2)
            
        arr[a][b]++
        
        if(arr[a][b] >= 2) break;
    }
    
    console.log(t)
}

const fs = require('fs')
const input = fs.readFileSync(0).toString().trim().split('\n')
const [a,b] = input[0].split(' ').map(Number)
const r = Number(input[1])
Solution(a,b,r)