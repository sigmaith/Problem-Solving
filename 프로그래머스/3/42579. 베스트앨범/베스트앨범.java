import java.io.*;
import java.util.*;
import java.util.stream.Collectors; // stream!!!!!!!!

class Solution {
    class Node {
        String genre;
        int wholePlay;
        public Node(String genre, int wholePlay) {
            this.genre = genre;
            this.wholePlay = wholePlay;
        }
    }
    
    class Song {
        int play;
        int id;
        public Song(int play, int id) {
            this.play = play;
            this.id = id;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        // 속한 노래가 많이 재생된 장르? -> 장르 별 최대 재생 값 갱신
        // 장르 내에서 많이 재생된 노래 수록 -> 장르에서 최대 재생수 2곡
        // 장르 내에서 재생 횟수가 같은 노래 중 고유번호가 낮은 노래  ->
        
        Map<String, PriorityQueue<Song>> musics = new HashMap<>();
        
        
        int n = genres.length;
        
        for (int id = 0; id < n; id++) { // id
            String genre = genres[id]; // 장르
            int play = plays[id]; // 재생횟수
            
            if (!musics.keySet().contains(genre)) {
                musics.put(genre, new PriorityQueue<>((o1, o2) -> {
                    if (o1.play != o2.play) {
                        return Integer.compare(o2.play, o1.play); // 재생횟수
                    }
                    return Integer.compare(o1.id, o2.id); // id
                }));
            }
            musics.get(genre).offer(new Song(play, id));
        }
        
        
        PriorityQueue<Node> bestAwards = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.wholePlay, o1.wholePlay));
        for (String genre : musics.keySet()) {
            int wholePlay = 0;    
            PriorityQueue<Song> pq = musics.get(genre);
            for (Song song : pq) {
                wholePlay += song.play;
            }
            bestAwards.offer(new Node(genre, wholePlay));
        }
        
        List<Integer> album = new ArrayList<>();
        while(!bestAwards.isEmpty()) {
            Node node = bestAwards.poll();
            String genre = node.genre;
            int cnt = 0;
            while (cnt < 2 && !musics.get(genre).isEmpty()) {
                Song song = musics.get(genre).poll();
                album.add(song.id); // id 추가
                cnt++;
            }
        }
        
        int[] result = new int[album.size()];
        for (int i = 0; i < album.size(); i++) {
            result[i] = album.get(i);
        }
        return result;
    }
}