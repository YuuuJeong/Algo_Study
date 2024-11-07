import java.io.*;
import java.util.*;

public class BOJ_9019 {
    static int A, B;
    static String commands = "DSLR";
    static class Node{
        int value;
        String command;
        
        public Node(int value, String command) {
            this.value = value;
            this.command = command;
        }
    }
    
    //0: D, 1: S, 2: L, 3: R
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t = 0 ; t< T; t++) {
             st = new StringTokenizer(br.readLine());
             A = Integer.parseInt(st.nextToken());
             B = Integer.parseInt(st.nextToken());
             sb.append(bfs()+"\n");
        }
        
        System.out.println(sb);

    }
    
    public static String bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(A, ""));
        boolean[] visited = new boolean[10000];
        visited[A] = true;
        while(!q.isEmpty()) {
            Node curNode = q.poll();
            int curValue = curNode.value;
        
            String curCommand = curNode.command;
            if(curValue == B) {
                return curCommand;
            }
            
            for(int i = 0 ; i < 4; i++) {

                
                char command = commands.charAt(i);;
                int nextValue = 0;
                if('D' == command) {
                    nextValue = curValue * 2;
                    if(nextValue > 9999) nextValue %= 10000;
                } else if('S' == command) {
                    if(curValue == 0) nextValue = 9999;
                    else nextValue = curValue - 1;
                } else if('L' == command) {
                    int mod = curValue % 1000;
                    mod *= 10;
                    nextValue = curValue / 1000;
                    nextValue += mod;
                } else {
                    int mod = curValue % 10;
                    mod *= 1000;
                    nextValue = curValue / 10;
                    nextValue += mod;            
                }
                
                if(visited[nextValue] == false) {
	                StringBuilder sb = new StringBuilder(curCommand).append(command);
	                q.offer(new Node(nextValue, sb.toString()));
	                visited[nextValue] = true;
                }
            }
        }
        return null;
    }

}
