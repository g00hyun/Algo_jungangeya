function solution(s) {
    const converter = new Map();
    converter.set('zero', 0).set('one', 1).set('two', 2).set('three',3).set('four',4).set('five',5).set('six',6).set('seven',7).set('eight',8).set('nine',9);
    
    for(let [k,v] of converter) {
        s = s.replaceAll(k, v)
    }
    return Number(s);
}