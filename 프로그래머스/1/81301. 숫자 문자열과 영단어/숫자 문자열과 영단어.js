function solution(s) {
    let answer = [];
    const converter = new Map();
    converter.set('zero', 0).set('one', 1).set('two', 2).set('three',3).set('four',4).set('five',5).set('six',6).set('seven',7).set('eight',8).set('nine',9);
    
    let start = 0, end = 0;
    for(let i = 0; i<s.length; i++) {
        end = i
        if(Number.isInteger(Number(s[end]))) {
            answer.push(Number(s[end]))
            start = end + 1
        }
        else {
            const word = s.slice(start, end + 1)
            if(converter.has(word)) {
                answer.push(converter.get(word))
                start = end + 1
            }
        }
        
    }
    return Number(answer.join(''));
}