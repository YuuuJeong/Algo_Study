package umjiwoo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA2383 {
	static int N, pCNT, MIN_TIME;  // N : 정사각형 한 변 크기, pCNT: 사람 수, MIN_TIME : 최소 시간
	static List<Integer> prList, pcList;  // 사람의 행,열 위치 저장
	static int[][] stairs;  // 계단 정보 저장
	static int[] selStair;  // 각 사람들이 사용할 계단 번호 저장

	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		StringBuilder sb=new StringBuilder();
		
		st=new StringTokenizer(br.readLine());
		int T=Integer.parseInt(st.nextToken());
		
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			
			st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			MIN_TIME=Integer.MAX_VALUE;
			
			prList=new ArrayList<>();
			pcList=new ArrayList<>();
			stairs=new int[2][3]; // [[s1.r, s1.c, s1.k],[s2.r, sr.c, s2.k]]
			
			// 사람, 계단 위치 파악
			pCNT=0;
			int s=0;
			for(int n=0;n<N;n++) {
				int i=0;
				st=new StringTokenizer(br.readLine());
				while(st.hasMoreTokens()) {
					int token=Integer.parseInt(st.nextToken());
					if(token==1) {
						prList.add(n);
						pcList.add(i);
						pCNT+=1;
					}
					if(token>1) {
						stairs[s][0]=n;
						stairs[s][1]=i;
						stairs[s][2]=token;
						s++;
					}
					i++;
				}
			}			
			
			selStair=new int[pCNT]; // N명의 사람들이 선택한 계단 정보 배열
			selectStair(0);
			sb.append(MIN_TIME).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	// 각 사람들이 이용할 계단의 경우의 수
	static void selectStair(int cnt) {
		if(cnt==pCNT) { // 계단 선택 완료
			// 소요 시간 계산
			int time=calTime(selStair);
			
			// 소요 시간 갱신
			MIN_TIME=time<MIN_TIME? time:MIN_TIME;
			return;
		}
		selStair[cnt]=0;
		selectStair(cnt+1);
		
		selStair[cnt]=1;
		selectStair(cnt+1);
	}
	
	static int calTime(int[] selStair) {
		List<Integer> timeArrToS1=new ArrayList<>();
		List<Integer> timeArrToS2=new ArrayList<>();
		
		for(int i=0;i<pCNT;i++) {
			if(selStair[i]==0) {
				int t=Math.abs(prList.get(i)-stairs[0][0])+Math.abs(pcList.get(i)-stairs[0][1]);				
				timeArrToS1.add(t);
			}
			else {
				int t=Math.abs(prList.get(i)-stairs[1][0])+Math.abs(pcList.get(i)-stairs[1][1]);				
				timeArrToS2.add(t);
			}
		}
		
		// 각 계단별로 먼저 도착한 순서대로 정렬
		timeArrToS1.sort(Integer::compareTo);
		timeArrToS2.sort(Integer::compareTo);
		
		int finArrTimeS1=calDownTime(timeArrToS1,stairs[0][2]);  // stairs[0][2] : 첫번째 계단 소요 시간(K)
		int finArrTimeS2=calDownTime(timeArrToS2,stairs[1][2]);  // stairs[0][2] : 첫번째 계단 소요 시간(K)
		
		return Math.max(finArrTimeS1, finArrTimeS2);  // 둘 중 더 오래 걸리는 게 총 소요 시간
	}
	
	static int calDownTime(List<Integer> timeArrToStair, int K) {
		int p=timeArrToStair.size(); // 계단에 도착한 사람 수
		if(p==0) return 0;
		
		int[] endTime=new int[p];
		
		for(int i=0;i<p;i++) {
			int arrTime=timeArrToStair.get(i);
			if(i<3) {
				endTime[i]=arrTime+1+K;
			}else {
				int arr1stP=endTime[i-3];
				if(arr1stP>arrTime) endTime[i]=arr1stP+K; // 내려가던 3명 중 가장 앞사람이 도착한 시간이 현재 사람이 도착한 시간보다 큰 경우 자동으로 1초 기다려짐
				else endTime[i]=arrTime+1+K; // 이미 한 사람은 다 내려간 상태이거나 나의 도착과 함께 한 사람이 다 내려감 ; 1초 기다렸다가 출발
			}
		}
				
		return endTime[p-1];  // 계단에 가장 마지막으로 도착한 사람이 다 내려가야 끝 ; 가장 마지막 사람의 소요 시간 리턴
	}
}
