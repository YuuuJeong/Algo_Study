import java.io.*;
import java.util.*;

public class BOJ10775 {
	static int[] parent;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int G = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());
		parent = new int[G + 1];
		for(int i = 1; i <= G; i++) {
			parent[i] = i;
		}
		
		int answer = 0;
		for(int i = 0; i < P; i++) {
			int gate = Integer.parseInt(br.readLine());
			int empty = findParent(gate);
			if(empty == 0) break;
			answer++;
			unionParent(empty, empty - 1);
		}
		
		System.out.println(answer);
		br.close();
	}
	
	public static int findParent(int x) {
		if(x == parent[x]) return x;
		return parent[x] = findParent(parent[x]);
	}
	
	public static void unionParent(int x, int y) {
		x = findParent(x);
		y = findParent(y);
		if(x != y) parent[x] = y;
	}
}
