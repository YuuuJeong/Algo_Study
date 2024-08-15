import java.io.*;
import java.util.*;
 
class Solution {
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static List<Atom> atoms;
    static int[][] map = new int[4001][4001];
    static Queue<int[]> deleteQ = new ArrayDeque<>();
     
    static class Atom {
        int row;
        int col;
        int d;
        int k;
        boolean isExist = true;
         
        Atom(int[] data) {
            this.row = (data[1] + 1000) << 1;
            this.col = (data[0] + 1000) << 1;
            this.d = data[2];
            this.k = data[3];
        }
    }
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
         
        int T = Integer.parseInt(br.readLine());
        for(int test = 1; test <= T; test++) {
            sb.append("#").append(test).append(" ");
            for(int i = 0; i < map.length; i++) {
                Arrays.fill(map[i], 0);
            }
             
            int N = Integer.parseInt(br.readLine());
            atoms = new ArrayList<>();
            for(int i = 0; i < N; i++) {
                int[] data = new int[4];
                st = new StringTokenizer(br.readLine(), " ");
                for(int j = 0; j < 4; j++) data[j] = Integer.parseInt(st.nextToken());
                Atom a =  new Atom(data);
                atoms.add(a);
                map[a.row][a.col] = a.k;
            }
             
            sb.append(solution()).append("\n");
        }
         
        System.out.print(sb.toString());
        br.close();
    }
     
    public static int solution() {
        int energy = 0;
        while(!atoms.isEmpty()) {
            for(int i = 0; i < atoms.size(); i++) {
                Atom a = atoms.get(i);
                map[a.row][a.col] = 0;
                a.row += dr[a.d];
                a.col += dc[a.d];
                 
                if(a.row < 0 || a.row > 4000 || a.col < 0 || a.col > 4000) {
                    atoms.remove(i);
                    i--;
                    continue;
                }
                 
                map[a.row][a.col] += a.k;
            }
             
            for(int i = 0; i < atoms.size(); i++) {
                Atom a = atoms.get(i);
                if(map[a.row][a.col] != a.k) {
                    energy += map[a.row][a.col];
                    map[a.row][a.col] = 0;
                    atoms.remove(i);
                    i--;
                }
            }
        }
        return energy;
    }
}
