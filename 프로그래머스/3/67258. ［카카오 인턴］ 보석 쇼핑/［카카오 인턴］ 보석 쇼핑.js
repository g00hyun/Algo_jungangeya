function solution(gems) {
    let answer = [];
    
    let start = 0, end = 0;
    const uniqueGems = new Map()
    
    const UpdateStart = () => {
        let result = 100001;
        uniqueGems.forEach(v => {
            result = Math.min(result, v)
        });
        return result;
    }
    
    for(let i = 0; i<gems.length; i++) {
        if(!uniqueGems.has(gems[i])) {
            uniqueGems.set(gems[i], i);
            end = i;
            answer = [start + 1, end + 1];
        }
        else {
            uniqueGems.set(gems[i], i);
            end = i;
            if(gems[start] === gems[end]) {
                start = UpdateStart();
                if(answer[1] - answer[0] > end - start)
                    answer = [start + 1, end + 1]
            }    
        }
        
    }
    
    return answer;
}