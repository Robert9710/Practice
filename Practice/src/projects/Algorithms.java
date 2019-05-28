package projects;

public class Algorithms {
        
    //Power algorithm
    public static int power(int number, int power){
        int result = 1;
        while(power > 0){
            if(power % 2 == 1){
                result *= number;
            }
            power /= 2;
            number *= number;  
        }
        return result;   
    }
    
    //Euclid algorithm for greatest common divisor
    public static int EuclidGCD(int n1, int n2){
        int n;        
        while(n1%n2 != 0){
            n = n1;
            n1 = n2;
            n2 = n%n2;
        }
        return n2;
    }
    
    //Calculate the factorial of a number
    public static long factorial(int number){
        
        long result=1;
        
        while(number > 0){
            result *= number;
            number -= 1;
        }
        return result;
    }
    
    //Calculate the nth number of the Fibonacci sequence
    public static int fibonacci(int number){
        int a = 0, b = 1;
        
        while(number > 0){
        if(number == 1){
            return a;
        }else if(number == 2){
            return b;
        }
        a += b;
        b += a;
        number -= 2;
        }
        return -1;
    }
    
}
