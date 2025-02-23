const fs = require('fs')
const input = fs.readFileSync("/dev/stdin").toString().trim().split('\n')
const n = Number(input[0])
const a = input[1].split(' ').map(Number)
const b = input[2].split(' ').map(Number)

const solution = (n, a, b) => {
    let result = 0

    a = a.sort((a, b) => a - b)
    b = b.sort((a, b) => (a - b) * -1)

    for(let i = 0; i<n; i++)
        for(let j = 0; j<n; j++) {
            if(b[j] === 0) continue;
            if(a[i] > b[j]) {
                result += 2
                a[i] = 0
                b[j] = 0
                break;
            }
        }
            
    for(let i = 0; i<n; i++) {
        if(a[i] === 0) continue;
        for(let j = 0; j<n; j++) {
            if(b[j] === 0) continue;
            if(a[i] === b[j]) {
                result += 1
                a[i] = 0
                b[j] = 0
                break;
            }
        }
    }

    return result
}

const result = solution(n, a, b)
console.log(result)