function solution(schedules, timelogs, startday) {
    var answer = 0;
    
    // 토,일 제외하기
    const sat = 7 - startday - 1 >= 0 ? 7 - startday - 1 : 6;
    const sun = 7 - startday;
    timelogs = timelogs.map(v => v.filter((v,i) => !(i === sat || i === sun)))
    
    // 출근 마감시간 설정
    schedules = schedules.map(plus10)
    
    const isSafe = (time, idx) => {
        return schedules[idx] >= time;
    }
    // 모두 출근 잘 했는지 판단하기
    timelogs.forEach((v,i) => v.every(a => isSafe(a,i)) ? answer++ : "")
    
    return answer;
}



const plus10 = (time) => {
    let hour = Math.trunc(time / 100);
    let minute = time % 100;
    
    minute += 10;
    
    if(minute >= 60) {
        minute -= 60;
        hour += 1;
    }
    
    if(hour >= 24) {
        hour = 0;
    }
    
    return hour*100 + minute;
}