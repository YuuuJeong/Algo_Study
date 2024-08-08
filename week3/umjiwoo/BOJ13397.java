package umjiwoo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ13397 {
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		
		st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		
		int[] nums=new int[n];
		st=new StringTokenizer(br.readLine());
		int i=0;
		while(st.hasMoreTokens()) {
			nums[i++]=Integer.parseInt(st.nextToken());
		}
		
		// 이분탐색 분기를 위한 포인트 찾기
//		int max_num=Arrays.stream(nums).max().getAsInt();
//		int min_num=Arrays.stream(nums).min().getAsInt();
		
		int max_num=nums[0], min_num=nums[0];   // right, left
		for(int j=0;j<n;j++) {
			if(nums[j]>max_num) max_num=nums[j];
			if(nums[j]<min_num) min_num=nums[j];
		}
		
		int left=0;
		int right=max_num-min_num;
		while(left<=right) {
			int mid=(left+right+1)/2;
			
			List<List<Integer>> r_lists=new ArrayList<>();
			int count=1;
			int j;
			outerloop:
			for(int k=0;k<nums.length;k=j+1) {
				r_lists.add(new ArrayList<>());
				int start=nums[k];
				r_lists.get(count-1).add(start);
				for(j=k+1;j<nums.length;j++) {
					r_lists.get(count-1).add(nums[j]);
//					System.out.println(r_lists.get(count));
					// 구간의 점수 계산
					int tmp=Collections.max(r_lists.get(count-1))-Collections.min(r_lists.get(count-1));
					if(tmp>mid) {
						count+=1;
						continue outerloop;
					}
				}
			}
			
			if(count>=m) {
				left=mid+1;
			}
			else {
				right=mid-1;
			}
		}
		
		System.out.println(right+1);
	}

}