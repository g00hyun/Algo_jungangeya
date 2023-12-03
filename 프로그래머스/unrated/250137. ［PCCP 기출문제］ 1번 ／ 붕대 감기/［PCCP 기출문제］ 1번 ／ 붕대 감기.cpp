#include <string>
#include <vector>
#include <iostream>

using namespace std;

int times;
int continuity;
int fullHealth;

bool isCharge(int health) {
    return health < fullHealth;
}

bool isBonus(int t) {
    return continuity == t;
}

int solution(vector<int> bandage, int health, vector<vector<int>> attacks) {
    int answer = 0;
    
    fullHealth = health;
    times = attacks[attacks.size() - 1][0];
    continuity = 0;
    
    for(int i = 1; i<=times; i++) {
        if(health <= 0) return -1;
        
        int damage = 0;
        for(int attack = 0; attack<attacks.size(); attack++)
            if(attacks[attack][0] == i)
                damage = attacks[attack][1];
        
        if(damage != 0) {
            continuity = 0;
            health -= damage;
        }
        
        if(damage == 0)
            continuity++;
        
        if((damage == 0) && isCharge(health))
            for(int i = 0; i<bandage[1]; i++)
                if(isCharge(health))
                    health++;
        
        if((damage == 0) && isBonus(bandage[0])) {
            for(int i = 0; i<bandage[2]; i++)
                if(isCharge(health))
                    health++;
            continuity = 0;
        }
        
        
        
        
        
        cout << "i : " << i << ", now health : " << health << endl;
    }
    
    if(health <= 0) answer = -1;
    else answer = health;
    
    return answer;
}