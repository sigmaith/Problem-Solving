// boj 29731 2033년 밈 투표
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Set<String> pledges = new HashSet<>();
        pledges.add("Never gonna give you up");
        pledges.add("Never gonna let you down");
        pledges.add("Never gonna run around and desert you");
        pledges.add("Never gonna make you cry");
        pledges.add("Never gonna say goodbye");
        pledges.add("Never gonna tell a lie and hurt you");
        pledges.add("Never gonna stop");

        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            String str = sc.nextLine().strip();
            if (!pledges.contains(str)) {
                flag = true;
            }
        }

        if (flag) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
