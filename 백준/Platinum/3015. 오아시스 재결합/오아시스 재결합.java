import java.io.*;
import java.util.*;

class Main {

    static int n;
    static Stack<Component> stack = new Stack<>();
    static long cnt;

    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  // 입력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 출력

        n = Integer.parseInt(br.readLine());   // 정수 입력

        for (int i = 0; i < n; i++){
            int height = Integer.parseInt(br.readLine());  // 정수 입력
            Component component = new Component(height, 1); 

            while (!stack.empty() && stack.peek().height <= height){
                Component com = stack.pop();
                cnt += com.cnt;
                if (com.height == height){
                    component.cnt += com.cnt;
                }
            }

            if (!stack.empty()){
                cnt += 1;
            }

            stack.push(component);
        }
        bw.write(cnt + "\n");  // "\n"을 붙여줘야 정수 그대로 출력
        bw.flush();  // 바로 출력
    }

    static class Component{
        int height;
        int cnt;
        Component(int height, int cnt){
            this.height = height;
            this.cnt = cnt;
        }
    }
    
}
