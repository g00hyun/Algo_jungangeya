function solution(n, q, ans) {
    var answer = 0;
    
    const isCode = (code) => {
        for(let i = 0; i<q.length; i++) 
            if(q[i].filter(v => code.includes(v)).length !== ans[i])
                return false;
        return true;
    }
    
    // brute force
    for(let a = 1; a <= n; a++)
     for(let b = a+1; b <= n; b++)
      for(let c = b+1; c <= n; c++)
       for(let d = c+1; d <= n; d++)
        for(let e = d+1; e <= n; e++)
            if(isCode([a,b,c,d,e]))
                answer++
    
    return answer;
}