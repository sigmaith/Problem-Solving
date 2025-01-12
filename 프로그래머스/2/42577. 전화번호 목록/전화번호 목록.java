import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        // 정렬
        Arrays.sort(phone_book);
            
        // ex) 12, 123, 1235, 1978, 735
        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                return false;
            }
        }
        
        return true;
    }
}