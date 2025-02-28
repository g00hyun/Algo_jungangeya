const sum22 = (x,y,arr) => {
    return arr[x][y] + arr[x+1][y] + arr[x][y+1] + arr[x+1][y+1]
}

const sum23 = (x,y,arr) => {
    const a1 = arr[x][y] + arr[x+1][y] + arr[x][y+1] + arr[x][y+2]
    const a2 = arr[x+1][y] + arr[x+1][y+1] + arr[x][y+2] + arr[x+1][y+2]
    const a3 = arr[x][y] + arr[x+1][y+2] + arr[x][y+1] + arr[x][y+2]
    const a4 = arr[x+1][y] + arr[x+1][y+1] + arr[x][y] + arr[x+1][y+2]
    const b1 = arr[x+1][y] + arr[x][y+1] + arr[x+1][y+1] + arr[x][y+2]
    const b2 = arr[x][y] + arr[x][y+1] + arr[x+1][y+1] + arr[x+1][y+2]
    const c1 = arr[x][y] + arr[x][y+1] + arr[x+1][y+1] + arr[x][y+2]
    const c2 = arr[x+1][y] + arr[x][y+1] + arr[x+1][y+1] + arr[x+1][y+2]
    
    return Math.max(a1,a2,a3,a4,b1,b2,c1,c2)
}

const sum32 = (x,y,arr) => {
    const a1 = arr[x][y] + arr[x+1][y] + arr[x+2][y] + arr[x+2][y+1]
    const a2 = arr[x][y] + arr[x][y+1] + arr[x+1][y+1] + arr[x+2][y+1]
    const a3 = arr[x][y] + arr[x+1][y] + arr[x+2][y] + arr[x][y+1]
    const a4 = arr[x+2][y] + arr[x][y+1] + arr[x+1][y+1] + arr[x+2][y+1]
    const b1 = arr[x][y] + arr[x+1][y] + arr[x+1][y+1] + arr[x+2][y+1]
    const b2 = arr[x][y+1] + arr[x+1][y] + arr[x+1][y+1] + arr[x+2][y]
    const c1 = arr[x+1][y] + arr[x][y+1] + arr[x+1][y+1] + arr[x+2][y+1]
    const c2 = arr[x][y] + arr[x+1][y] + arr[x+2][y] + arr[x+1][y+1]
    
    return Math.max(a1,a2,a3,a4,b1,b2,c1,c2)
}

const sum41 = (x,y,arr) => {
    return arr[x][y] + arr[x+1][y] + arr[x+2][y] + arr[x+3][y]
}

const sum14 = (x,y,arr) => {
    return arr[x][y] + arr[x][y+1] + arr[x][y+2] + arr[x][y+3]
}

const Solution = (n,m,arr) => {
    let result = 0
    // 2*2
    for(let i = 0; i<n-1; i++)
        for(let j = 0; j<m-1; j++)
            result = Math.max(result, sum22(i,j,arr))
    
    // 2*3
    for(let i = 0; i<n-1; i++)
        for(let j = 0; j<m-2; j++)
            result = Math.max(result, sum23(i,j,arr))
    
    // 3*2
    for(let i = 0; i<n-2; i++)
        for(let j = 0; j<m-1; j++)
            result = Math.max(result, sum32(i,j,arr))
    
    // 4*1
    for(let i = 0; i<n-3; i++)
        for(let j = 0; j<m; j++)
            result = Math.max(result, sum41(i,j,arr))
    
    // 1*4
    for(let i = 0; i<n; i++)
        for(let j = 0; j<m-3; j++)
            result = Math.max(result, sum14(i,j,arr))
            
    console.log(result)
}

const fs = require('fs')
const input = fs.readFileSync(0).toString().trim().split('\n')

const [n,m] = input[0].split(' ').map(Number)
const arr = input.slice(1).map(v => v.split(' ').map(Number))

Solution(n,m,arr)