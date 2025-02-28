class Simulation {
    constructor(n,w,l,trucks) {
        this.totalTrucks = n
        this.bridgeLength = w
        this.maxWeight = l
        
        this.waitingQueue = [...trucks]
        this.finishedQueue = []
        // elemType => [weight, time]
        this.runningQueue = []
    }
    
    getRunningTruck() {
        return this.runningQueue.length
    }
    
    getTotalWeight_of_RunningTruck() {
        return this.runningQueue
                .map(v => v[0])
                .reduce((total, cur) => total+cur, 0)
    }
    
    CanGo() {
        if(this.getRunningTruck() >= this.bridgeLength) return false;
        if(this.getTotalWeight_of_RunningTruck() + this.waitingQueue[0] > this.maxWeight) return false;
        return true;
    }
    
    addTruck() {
        if(this.waitingQueue.length === 0) return;
        
        if(this.CanGo()) {
            const nowTruck = this.waitingQueue.shift()
            this.runningQueue.push([nowTruck,0])
        }
    }
    
    finishTruck() {
        if(this.runningQueue.length === 0) return;
        
        if(this.runningQueue[0][1] === this.bridgeLength) {
            const finishedTruck = this.runningQueue.shift()[0]
            this.finishedQueue.push(finishedTruck)
        }
    }
    
    running() {
        this.runningQueue.forEach(v => v[1]++);
        this.finishTruck();
        this.addTruck();
    }
    
    isFinish() {
        return this.totalTrucks === this.finishedQueue.length
    }
}

const Solution = (n, w, l, trucks) => {
    const simulation = new Simulation(n,w,l,trucks)
    
    let answer = 0
    while(!simulation.isFinish()) {
        simulation.running()
        answer++
    }
    
    console.log(answer)
}

const fs = require('fs')
const input = fs.readFileSync(0).toString().trim().split('\n')

const [n,w,l] = input[0].split(' ').map(Number)
const trucks = input[1].split(' ').map(Number)

Solution(n,w,l,trucks)