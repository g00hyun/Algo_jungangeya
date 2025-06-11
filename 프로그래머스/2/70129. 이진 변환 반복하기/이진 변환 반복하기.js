function solution(s) {
    const deleteZero = (s) => {
        return s.split('').filter(v => v==='1').join('');
    }
    
    const convert10to2 = (num) => {
        const result = []
        
        while(num > 0) {
            result.push(num%2);
            num = Math.trunc(num/2);
        }
        
        return result.reverse().join('')
    }
    
    let sum = 0;
    let count = 0;
    while(s !== "1") {
        const oldlen = s.length;
        
        s = deleteZero(s);
        const len = s.length;
        
        const diff = oldlen - len;
        
        const binary = convert10to2(len);
        
        s = binary
        count++;
        sum += diff
    }
    
    return [count, sum];
}