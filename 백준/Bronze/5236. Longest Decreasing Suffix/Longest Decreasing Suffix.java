import java.util.Scanner;

class Main {
    private static String findLongestDecreasigSuffix(String s) {
        int n = s.length();
        if (n == 0)
            return ""; // 빈 문자열 처리
            
        String tmp = "" + s.charAt(n-1);
        for (int i=n-2; i>=0; i--){
            if (s.charAt(i) > s.charAt(i+1)){
                tmp =  s.charAt(i) + tmp;
            }
            else
                break;
            
        }
        return tmp;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numCases = sc.nextInt();
        sc.nextLine(); // 개행 문자 소비

        for(int i = 0; i < numCases; i++)
        {
            String s = sc.nextLine();
            System.out.println("The longest decreasing suffix of " + s + " is " + findLongestDecreasigSuffix(s));
        }
    }
}
