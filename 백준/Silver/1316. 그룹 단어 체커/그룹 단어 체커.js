const solution = (n, list) => {
    let answer = 0
    list.forEach(v => {
        let isGroupWord = true
        const wordArr = v.split('')
        const uniqueAlpha = new Set(v.split(''))
            
        let prev = wordArr.shift()
        for(let i = 1; i<v.length; i++) {
            const cur = wordArr.shift()
            if(prev === cur) continue
            
            const isDelete = uniqueAlpha.delete(prev)
            isDelete ? "" : isGroupWord = false
            prev = cur;
        }
        
        const isDelete = uniqueAlpha.delete(prev)
        isDelete ? "" : isGroupWord = false
        
        isGroupWord ? answer++ : ""
    })
    
    console.log(answer)
}

const fs = require('fs')
const input = fs.readFileSync('dev/stdin').toString().trim().split('\n')
const n = Number(input[0])
const list = input.slice(1)

solution(n,list)