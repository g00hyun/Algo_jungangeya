const Solution = (cmd) => {
    const stack = []
    
    cmd.forEach(v => {
        const [c, n] = v.split(' ')
        
        if(c === 'push') {
            stack.push(n)
        }
        else if(c === 'pop') {
            if(!stack.length) console.log(-1)
            else console.log(stack.pop())
        }
        else if(c === 'size') {
            console.log(stack.length)
        }
        else if(c === 'empty') {
            console.log(stack.length ? 0 : 1)
        }
        else if(c === 'top') {
            if(!stack.length) console.log(-1)
            else console.log(stack[stack.length - 1])
        }
    })
}

const fs = require('fs')
const input = fs.readFileSync(0).toString().trim().split('\n')

Solution(input.slice(1))