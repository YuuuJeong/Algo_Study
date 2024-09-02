import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BOJ14567 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String[] nm=sc.nextLine().split(" ");
		int n=Integer.parseInt(nm[0]);
		int m=Integer.parseInt(nm[1]);
		
		List<Integer>[] adj_in=new ArrayList[n+1];
		List<Integer>[] adj_out=new ArrayList[n+1];
		for(int i=1;i<=n;i++) {
			adj_in[i]=new ArrayList<>();
			adj_out[i]=new ArrayList<>();
		}
		
		int[] in_degree=new int[n+1];
		int[] out_degree=new int[n+1];
		for(int i=0;i<m;i++) {
			String[] ab=sc.nextLine().split(" ");
			int a=Integer.parseInt(ab[0]);
			int b=Integer.parseInt(ab[1]);
			
			adj_in[b].add(a);
			adj_out[a].add(b);
			in_degree[b]++;
			out_degree[a]++;
		}
		
		Queue<Integer> queue=new LinkedList<>();
		for(int i=1;i<=n;i++) {
			if(in_degree[i]==0) {
				queue.add(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int cur=queue.poll();
			for(int nxt:adj_in[cur]) {
				in_degree[nxt]--;
				if(in_degree[nxt]==0) {
					queue.add(nxt);
				}
			}
		}
		
		int[] s=new int[n+1];
		int[] l=new int[n+1];
		
		for(int i=1;i<=n;i++) {
			for(int nxt:adj_in[i]) {
				s[nxt]=Math.max(s[nxt], s[i]+1);
				l[i]=Math.max(l[i], l[nxt]+1);
			}
		}
		
		int count=0;
		for(int i=1;i<=n;i++) {
			if(s[i]+l[i]==n-1) {
				count++;
			}
		}
		
		System.out.println(count);
		sc.close();
	}

}
