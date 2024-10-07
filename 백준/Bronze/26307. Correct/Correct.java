import java.time.LocalDateTime;
import java.time.Duration;
import java.util.Scanner;

public class Main {
    public static int correct(int HH, int MM) {
        // 시작 시간과 끝 시간을 설정
        LocalDateTime startTime = LocalDateTime.of(2022, 1, 1, 9, 0);
        LocalDateTime endTime = LocalDateTime.of(2022, 1, 1, HH, MM);
        
        // 두 시간의 차이를 계산
        Duration consumedTime = Duration.between(startTime, endTime);
        
        // 초 단위로 차이를 분 단위로 변환
        return (int) consumedTime.getSeconds() / 60;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int HH = scanner.nextInt();
        int MM = scanner.nextInt();
        
        System.out.println(correct(HH, MM));
        scanner.close();
    }
}
