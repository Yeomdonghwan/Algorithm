import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String word = sc.next();
        int[] word_alpha = new int[26];
        int ans = 0;

        word = word.toUpperCase();
        for(int i=0;i<26;i++){
            word_alpha[i]=0;
        }
        for(int i=0;i<word.length();i++){
            word_alpha[word.charAt(i)-'A']++;
        }
        for(int i=0;i<n-1;i++){
            String str = sc.next();
            str=str.toUpperCase();

            if(Math.abs(str.length()-word.length())>1){
                continue;
            }
            int[] str_alpha = new int[26];
            for(int j=0;j<26;j++){
                str_alpha[j]=0;
            }
            for(int j=0;j<str.length();j++){
                str_alpha[str.charAt(j)-'A']++;
            }

            boolean flag = true;
            int max_diff = 0;
            for(int j=0;j<26;j++){
                int diff = Math.abs(str_alpha[j]-word_alpha[j]);
                max_diff+=diff;

                if(max_diff>2 || diff>1){
                    flag=false;
                    break;
                }
            }

            if(flag){
                ans++;
            }

        }

        System.out.println(ans);

    }
}
