import java.io.*;
import java.util.*;

public class Main {

    static class TrieNode {
        Map<String, TrieNode> children = new TreeMap<>();
    }

    static TrieNode root = new TrieNode();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            TrieNode currentNode = root;
            for (int j = 0; j < k; j++) {
                String element = st.nextToken();
                currentNode = currentNode.children.computeIfAbsent(element, x -> new TrieNode());
            }
        }

        printTrie(root, 0);
        bw.flush();
        bw.close();
        br.close();
    }

    static void printTrie(TrieNode node, int depth) {
        for (String key : node.children.keySet()) {
            for (int i = 0; i < depth; i++) {
                System.out.print("--");
            }
            System.out.println(key);
            printTrie(node.children.get(key), depth + 1);
        }
    }
}
