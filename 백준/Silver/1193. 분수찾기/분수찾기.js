const Solution = (n) => {
    let num = 0, total = 0
    while(n > total) {
        total += ++num
    }
    
    let diff = total - n
    
    if(num % 2 === 1) {
        let u = 1, d = num
        while(diff--)
            u++, d--
        console.log(`${u}/${d}`)
    }
    else {
        let u = num, d = 1
        while(diff--)
            u--, d++
        console.log(`${u}/${d}`)
    }
}

const fs = require('fs')
const input = fs.readFileSync(0).toString().trim()
const n = Number(input)
Solution(n)