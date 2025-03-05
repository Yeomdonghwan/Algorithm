//import java.util.*;
//import java.io.*;
//
//public class Main {
//	static int[][] circle;
//	static int[] startIdx;
//	static int N,M,T;
//	public static void main(String[] args) throws IOException{
//		// TODO Auto-generated method stub
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		
//		N = Integer.parseInt(st.nextToken());
//		M = Integer.parseInt(st.nextToken());
//		T = Integer.parseInt(st.nextToken());
//		
//		circle = new int[N+1][N+1];
//		startIdx = new int[N+1];
//		
//		for(int i=1;i<N;i++) {
//			st = new StringTokenizer(br.readLine());
//			for(int j=0;j<M;j++) {
//				circle[i][j] = Integer.parseInt(st.nextToken());
//			}
//		}
//		
//		for(int i=0;i<T;i++) {
//			st = new StringTokenizer(br.readLine());
//			int x = Integer.parseInt(st.nextToken());
//			int d = Integer.parseInt(st.nextToken());
//			int k = Integer.parseInt(st.nextToken())%M;
//			
//			
//			//회전
//			if(d==0) {
//				//시계방향
//				for(int j=x;j<N;j+=x) {
//					startIdx[j]= startIdx[j]-k;
//					if(startIdx[j]<0)
//						startIdx[j]=M-startIdx[j];
//				}
//				
//			}else {
//				//반시계방향
//				for(int j=x;j<N;j+=x) {
//					startIdx[j]= (startIdx[j]+k)%M;
//					
//				}
//			}
//			
//			//인접 숫자 확인하기
//			for(int r=1;r<=N;r++) {
//				for(int c=0;c<M;c++) {
//					//circle[r][c]위치로부터 인접한 값들을 확인하기
//					int num = circle[r][(startIdx[r]+c)%M];
//					if(num!=-1) {
//						diff(r,c,num);	
//					}
//					
//				}
//				//원판 숫자 평균값으로 변경하기
////				int avg = 0;
////				int cnt =0;
////				for(int c=0;c<M;c++) {
////					if(circle[r][c]!=-1)
////						{	avg+=circle[r][c];
////							cnt++;
////						}
////					}
////				if(cnt>0) {
////					avg/=cnt;
////					for(int c=0;c<M;c++) {
////						if(circle[r][c]>avg) {
////							circle[r][c]-=1;
////						}else if (circle[r][c]<avg) {
////							circle[r][c]+=1;
////						}
////					}
////				}
//				
//			}
//			System.out.println("i회차"+i + Arrays.toString(startIdx));
//			for(int a=1;a<=N;a++) {
//				for(int b=0;b<M;b++) {
//					System.out.print(circle[a][b]+" ");
//				}
//				System.out.println();
//			}
//			
//			
//		}
//
//		int ans=0;
//		for(int a=1;a<=N;a++) {
//			for(int b=0;b<M;b++) {
//				ans+=(circle[a][b]!=-1)?circle[a][b]:0;
//			}
//		}
//		
//		System.out.println(ans);
//		
//	}
//	static void diff(int r,int c, int num) {
//			
//			boolean flag = false;
//			
//			int left = (startIdx[r]+c-1);
//			if(left>=0) {
//				left%=M;
//			}else {
//				left=M-1;
//			}
//			
//			if( circle[r][left]!=-1 &&circle[r][left]==num ) {
//				//재귀 돌리기ㅣㅣㅣ
//				circle[r][left]=-1;
//				diff(r,left,num);
//				flag=true;
//			}
//			
//			
//			int right = (startIdx[r]+c+1)%M;
//			if(circle[r][right]!=-1 && circle[r][right]==num) {
//				//재귀 ㄱ
//
//				circle[r][right]=-1;
//				diff(r,right,num);
//				flag=true;
//			}
//			
//			
////			if(r!=N) {
////				int up=r+1;
////				if((circle[up][(startIdx[up]+c)%M])!=-1 && (circle[up][(startIdx[up]+c)%M])==num) {
////					//재귀
////
////					circle[up][c]=-1;
////					diff(up,c,num);
////					flag=true;
////				}
////			}
////			
////			if(r!=1) {
////				int down=r-1;
////				if((circle[down][(startIdx[down]+c)%M]!=-1 && (circle[down][(startIdx[down]+c)%M]==num))) {
////					//재귀
////
////					circle[down][c]=-1;
////					diff(down,c,num);
////					flag=true;
////				}	
////			}
//	
////			if(flag) {
////				circle[r][(startIdx[r]+c)%M]=-1;
////			}
//	}
//
//}






import java.util.*;
import java.io.*;

class Main{

	static int[] dr = {0,0,-1,1};
	static int[] dc = {1,-1,0,0};
	static int N,M,T;
	static int[][] list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
//		Deque<Integer>[] list = new Deque[N+1];
		list = new int[N+1][M];
		
		
		
		
		for(int i=1;i<=N;i++) {
//			list[i]=new ArrayDeque<>();
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
//				list[i].add(Integer.parseInt(st.nextToken()));
				list[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int command = 1; command <= T ; command++) {
			int x,d,k;
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
		
			//회전시키기
			for(int target=x; target<=N; target+=x) {
				for(int rotate = 0 ; rotate<k; rotate++) {
					if(d==0) {
						//시계방향
						//맨뒤에걸 빼서 앞에다가 넣음
//						int last =list[target].removeLast();
//						list[target].addFirst(last);
						int last = list[target][M-1];
						
						for(int i=M-1;i>0;i--) {
							list[target][i]=list[target][i-1];
						}
						list[target][0]=last;
						
					}else {
						//반시계방향
//						int first = list[target].removeFirst();
//						list[target].addLast(first);
						int first = list[target][0];
						
						for(int i=0;i<M-1;i++) {
							list[target][i]=list[target][i+1];
						}
						list[target][M-1]=first;
					
					}
			
				}
			}
			
			
//			인접한것 중에 같은숫자가 있는지 보기
			boolean flag=false;
			for(int i=1;i<=N;i++) {
				for(int j=0;j<M;j++) {
					if(list[i][j]!=-1) {
						dfs(i,j,list[i][j]);
						if(list[i][j]==-1)
							flag=true;
					}
				}
			}
			
			if(!flag) {
				int sum=0;
				int count=0;
				//인접한숫자 삭제한것이 없는경우
				for(int i=1;i<=N;i++) {
				
					for(int j=0;j<M;j++) {
						if(list[i][j]==-1)	continue;
						sum+=list[i][j];
						count++;
					}
					
				}
				if(count==0)	continue;
				double avg = sum/(double)count;
//				System.out.println(avg);
//				for(int i=1;i<=N;i++) {
//					System.out.println(Arrays.toString(list[i]));
//				}
				for(int i=1;i<=N;i++) {

					for(int j=0;j<M;j++) {
						if(list[i][j]==-1)	continue;
						if(list[i][j]>avg) {
							list[i][j]--;
						}
						else if(list[i][j]<avg) {
							list[i][j]++;
						}
					}
						
				}
				
			}
			
//			for(int i=1;i<=N;i++) {
//				System.out.println(Arrays.toString(list[i]));
//			}
			
		}
		int ans=0;
		for(int i=1;i<=N;i++) {
			for(int j=0;j<M;j++) {
				if(list[i][j]==-1)	continue;
				ans+=list[i][j];
			}
		}
		
		System.out.println(ans);
		
	}
	static void dfs(int r,int c,int num) {
		
//		boolean flag=false;
		for(int i=0;i<4;i++) {
			int next_r = r+dr[i];
			int next_c = c+dc[i];
			
			if(next_r>N || next_r<=0)
				continue;
			
			if(next_c>=M) {
				next_c = 0;
			}
			if(next_c<0) {
				next_c = M-1;
			}
			
//			if(list[next_r][next_c]==-1)	continue;
			
			if(list[next_r][next_c]==num) {

				list[next_r][next_c]=-1;
				dfs(next_r,next_c,num);
//				flag=true;
			}
		}
		
//		if(flag) {
//			list[r][c]=-1;
//		}
	}
	
}
