package main
import "fmt"

func main() {
    var num int
    fmt.Scanf("%d", &num)
    
    var cycle int = 0
    var tmp int = num
    
    for {
        cycle += 1
    
        if tmp < 10 {
            tmp = tmp * 10 + tmp
        } else {
            tmp = ( tmp % 10 ) * 10  + (tmp/10 + tmp%10) % 10
        }
        
        if tmp == num {
            break
        }
    }
    fmt.Println(cycle)
}