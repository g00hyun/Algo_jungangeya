#include <iostream>

using namespace std;

int a, b, cnt = 1;

//2를 나눈다
void cal1()
{
    b /= 2;
}

//가장 오른쪽의 수를 제거한다
void cal2()
{
    b /= 10;
}

int main()
{
    cin >> a >> b;

    while (1)
    {
        if (a == b)
            break;
        else if (b < a)
        {
            cnt = -1;
            break;
        }
        else
        {
            if (b % 2 == 0)
            {
                cnt++;
                cal1();
            }
            else
            {
                if (b % 10 == 1)
                {
                    cnt++;
                    cal2();
                }
                else
                {
                    cnt = -1;
                    break;
                }
            }
        }
    }

    cout << cnt;
}