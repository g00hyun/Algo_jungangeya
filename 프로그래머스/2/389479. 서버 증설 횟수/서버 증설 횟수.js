function solution(players, m, k) {
    var answer = 0;
    
    // 현재 운용 서버
    let server = 0;
    // 서버 증가 횟수
    let serverCnt = 0;
    // [time, addServerCnt]
    const runningQueue = [];
    
    const Run = () => {
        runningQueue.map(v => v[0]--)
    }
    
    for(let i = 0; i<players.length; i++) {
        
        // 돌아가고 있는 서버 운용시간 -1 && 만약, 서버시간이 종료됐으면 서버의 갯수를 줄여주는 로직
        Run();
        if(runningQueue.length && runningQueue[0][0] === 0) {
            server -= runningQueue[0][1]
            runningQueue.shift()            
        }
        
        const playerCnt = players[i];
        
        const mustAddServer = Math.floor(playerCnt / m)
        
        if(server < mustAddServer) {
            const addServerCnt = mustAddServer - server
            serverCnt += addServerCnt;
            server = mustAddServer;
            runningQueue.push([k, addServerCnt])
        }
        
        // console.log(`now Time is ${i}`)
        // console.log(runningQueue.length ? runningQueue : "")
    }
    
    return serverCnt;
}