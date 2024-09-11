package hsat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_염기서열커버 {
    static List<String> sequence;
    static List<String> answer;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        sequence = new ArrayList<>();
        answer = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            sequence.add(bf.readLine());
        }

        for (String temp : sequence) {
            if (!canCover(temp)) {
                answer.add(temp);
            }
        }
        System.out.println(answer.size());
    }

    private static boolean canCover(String value) {
        for (int i = 0; i < answer.size(); i++) {
            String current = answer.get(i);
            if (canMatch(current, value)) {
                String combined = combineStr(current, value);
                answer.set(i, combined);
                return true;
            }
        }
        return false;
    }

    private static String combineStr(String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                sb.append(s1.charAt(i));
            } else if (s1.charAt(i) == '.' || s2.charAt(i) == '.') {
                sb.append(s1.charAt(i) == '.' ? s2.charAt(i) : s1.charAt(i));
            }
        }
        return sb.toString();
    }

    private static boolean canMatch(String s1, String s2) {
        for (int i = 0; i < m; i++) {
            if (s1.charAt(i) != s2.charAt(i) && s1.charAt(i) != '.' && s2.charAt(i) != '.') {
                return false;
            }
        }
        return true;
    }
}
