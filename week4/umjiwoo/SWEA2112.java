import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA2112 {
	static int D,W,K;
	static List<List<Integer>> fig;   // int[][] 를 통해 구현 시 실행 속도 단축 가능(인덱스로 접근 가능, 복사 작업 상대적으로 빠름)
	static int minChg=0;
	
	// fig 유효 체크 함수
	static boolean isValid() {		
		// 컬럼 기준 순회, 연속으로 같은 속성을 갖는 원소가 K개 이상 있는지 탐색
		for(int w=0;w<W;w++) {
			int cnt=1; // 열마다 cnt값 초기화
			boolean valid=false;
			for(int d=1;d<D;d++) { // 각 열의 1층~D-1층 돌며 현재층과 아래층 값이 같은지 확인
				if(fig.get(d-1).get(w).equals(fig.get(d).get(w))) {
					cnt++;
				}
				else {
					cnt=1; // 같은 값이 연속으로 오지 않는 경우 다른 층을 탐색해야 하므로 다시 cnt를 1로 초기화
				}
				
				if(cnt>=K) {
					valid=true;
					break;
				}
			}
			if(!valid) return false;
		}
		return true;
	}
	
	static void dfs(int depth, int chgCnt) {
		if(chgCnt>=minChg) return;  // 모든 층을 다 바꿨는데도 통과 안되는 경우
		if(isValid()) {
			// 유효해짐; 모든 층이 K개의 연속된 층을 가지게 되는 경우
			minChg=Math.min(minChg, chgCnt);
			return;
		}
		
		// 맨 윗층까지 가면 더이상 확인할 곳 없음 ; 종료
		if(depth==D) return;
		
		// 약물 주입 없이 다음 층 탐색
		dfs(depth+1, chgCnt);
		
		// 약물 주입
		// 특정 층의 배열 복사 떠놓기
		List<Integer> org=new ArrayList<>(fig.get(depth));
//		List<Integer> org=fig.get(depth); error -> 얕은 복사로 인해 제대로 된 원본 반영이 되지 않음
		// int[][]로 구현 시 int[] originalRow=fig[depth]; 얕은 복사 가능 -> 참조 시점의 배열을 참조함
		
		// 1.A약물 주입
		fig.get(depth).replaceAll(i->0);
		dfs(depth+1, chgCnt+1);
		
		// A약물 주입 후 탐색하고 와서 다시 원본으로 돌려놓기
		for(int i=0;i<org.size();i++) {
			fig.get(depth).set(i, org.get(i));
		}
		
		// 2.B약물 주입
		// 현재 층을 B로 변경하고 다음 층으로 진행
		fig.get(depth).replaceAll(i->1);
		dfs(depth+1, chgCnt+1);
		
		for(int i=0;i<org.size();i++) {
			fig.get(depth).set(i, org.get(i));
		}
        
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		StringBuilder sb=new StringBuilder();
		
		st=new StringTokenizer(br.readLine());
		int T=Integer.parseInt(st.nextToken());
		for(int i=1;i<T+1;i++) {
			st=new StringTokenizer(br.readLine());
			D=Integer.parseInt(st.nextToken());
			W=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			
			/*배열 입력받기*/
			fig=new ArrayList<>();
			for(int d=0;d<D;d++) {
				st=new StringTokenizer(br.readLine());
				List<Integer> layer=new ArrayList<>();
				while(st.hasMoreTokens()) {
					String token=st.nextToken();
					if(token.equals("0")) {
						layer.add(0);
					}else {
						layer.add(1);
					}
				}
				fig.add(layer);
			}
			/*배열 입력받기 끝*/
			
			if(K==1) {
				sb.append("#").append(i).append(" 0\n");
			}
			else {
				minChg=D;
				dfs(0,0);
				sb.append("#").append(i).append(" ").append(minChg).append("\n");
			}
		}
		System.out.println(sb.toString());
	}
}
