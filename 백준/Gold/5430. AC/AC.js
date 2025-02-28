const Calculating = (cmd, len, arr) => {
    let isReverse = false, fD = 0, bD = 0;
    for(let i = 0; i<cmd.length; i++){
        const c = cmd[i];
        if(c === 'R') {
            isReverse = !isReverse
        }
        else if(c === 'D') {
            if(isReverse) bD++
            else fD++
        }
    }
    
    if(fD + bD > arr.length) return 'error'
    
    arr = arr.slice(fD, len - bD)
    isReverse ? arr.reverse() : ""
    
    return '[' + arr.join(',') + ']'
}

const Solution = (t, input) => {
    for(let i = 0; i<t; i++) {
        const cmd = input[0 + 3*i]
        const len = input[1 + 3*i]
        const arrStr = input[2 + 3*i]
        let arr;
        if(arrStr === '[]')
            arr = []
        else
            arr = arrStr.slice(1,arrStr.length-1).split(',').map(Number)
        
        const result = Calculating(cmd, Number(len), arr)
        console.log(result)
    }
}

const fs = require('fs')
const input = fs.readFileSync(0).toString().trim().split('\n')

const t = Number(input[0])
const cases = input.slice(1)

Solution(t,cases)