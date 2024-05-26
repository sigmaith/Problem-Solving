import java.util.*;


class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        
        int[] arr = new int[m];

        int max = 0;
        for (int i = 0; i< m; i++){
            arr[i] = scanner.nextInt();
            if (max < arr[i]){
                max = arr[i];
            }
        } 

        double avg = 0;
        double sum = 0;
        for (int i = 0; i < m; i++){
            sum += arr[i]/(double)max*100;
            // System.out.println(sum);
        }
        avg = sum/(double)m;
        System.out.println(avg);
    }
}
