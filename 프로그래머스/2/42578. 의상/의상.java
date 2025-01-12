import java.io.*;
import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Set<String>> clothesMap = new HashMap<>();
        
        for (String[] cloth : clothes) {
            String name = cloth[0];
            String type = cloth[1];
            if (!clothesMap.keySet().contains(type)) {
                clothesMap.put(type, new HashSet<>());        
            }
            clothesMap.get(type).add(name); 
        }
        
        int result = 1;
        for (String type : clothesMap.keySet()) {
            result = result * (clothesMap.get(type).size() + 1);
        }
            
        return result - 1;
    }
}