import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Solution {
    static int maxVisit;
    static boolean[] visited;
    static int n;
    static int[][] dungeons;
    public int solution(int k, int[][] input) {
        maxVisit = -1;
        dungeons = input;
        n = dungeons.length;
        visited = new boolean[n];
        permutation(k, 0, 0);
        return maxVisit;
    }
    
    private void permutation(int tired, int check, int visit) {
        if (check == n) {
            if (maxVisit < visit) {
                maxVisit = visit;
            }    
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (dungeons[i][0] <= tired) {
                    permutation(tired - dungeons[i][1], check + 1, visit + 1);
                } else {
                    permutation(tired, check + 1, visit);
                }
                visited[i] = false;
            }
        }
    }
}