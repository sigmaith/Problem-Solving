// boj 14891 톱니바퀴

import java.io.*;
import java.util.*;

public class Main {
    static class GearWheel {
        int[] states; // N극은 0, S극은 1

        public GearWheel(int[] states) {
            this.states = states;
        }

        public void rotateBy(int direction) {
            int tmp;
            if (direction == 1) {
                tmp = states[7];
                for (int i = 7; i >= 1; i--) {
                    states[i] = states[i - 1];
                }
                states[0] = tmp;
            } else if (direction == -1) {
                tmp = states[0];
                for (int i = 0; i <= 6; i++) {
                    states[i] = states[i + 1];
                }
                states[7] = tmp;
            }
        }
    }

    static List<GearWheel> gearWheels;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        gearWheels = new ArrayList<>();
        gearWheels.add(new GearWheel(new int[]{0}));
        for (int i = 1; i <= 4; i++) {
            gearWheels.add(new GearWheel(convert(br.readLine())));
        }

        int numOfRotation = Integer.parseInt(br.readLine());
        for (int i = 0; i < numOfRotation; i++) {
            st = new StringTokenizer(br.readLine());
            // 톱니바퀴 번호, 1이 시계, -1이 반시계 방향
            int wheelNumber = Integer.parseInt(st.nextToken()), dir = Integer.parseInt(st.nextToken());
            int[] directions = getDirections(wheelNumber, dir);
            for (int j = 1; j <= 4; j++) {
                gearWheels.get(j).rotateBy(directions[j]);
            }
        }

        int score = 0;
        if (gearWheels.get(1).states[0] == 1) {
            score += 1;
        }
        if (gearWheels.get(2).states[0] == 1) {
            score += 2;
        }
        if (gearWheels.get(3).states[0] == 1) {
            score += 4;
        }
        if (gearWheels.get(4).states[0] == 1) {
            score += 8;
        }
        System.out.println(score);
    }

    private static int[] getDirections(int wheelNumber, int dir) { // 극이 같으면 같은 방향, 극이 다르면 다른 방향
        int[] dirs = new int[5];
        dirs[wheelNumber] = dir;
        for (int i = wheelNumber; i > 1; i--) {
            if (gearWheels.get(i).states[6] == gearWheels.get(i - 1).states[2]) {
                dirs[i - 1] = -Integer.MAX_VALUE;
            } else {
                dirs[i - 1] = -dirs[i];
            }
        }
        for (int i = wheelNumber; i < 4; i++) {
            if (gearWheels.get(i).states[2] == gearWheels.get(i + 1).states[6]) {
                dirs[i + 1] = -Integer.MAX_VALUE;
            } else {
                dirs[i + 1] = -dirs[i];
            }
        }
        return dirs;
    }

    private static int[] convert(String input) {
        int[] states = new int[8];
        for (int i = 0; i < 8; i++) {
            states[i] = input.charAt(i) - '0';
        }
        return states;
    }
}