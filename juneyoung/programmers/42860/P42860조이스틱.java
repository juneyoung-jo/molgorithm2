package programmers;

import java.util.*;

public class P42860조이스틱 {
    static Map<Integer, Integer> map = new HashMap<>();
    static char[] word;

    public int solution(String name) {
        int answer = 0;
        int size = name.length();
        int last = 0;
        word = name.toCharArray();

        for (int i = 0; i < 13; i++) {
            map.put(i + 66, i + 1);
            map.put(90 - i, i + 1);
        }

        for (int i = size - 1; i >= 0; i--) {
            if (word[i] != 'A') {
                last = i;
                break;
            }
        }

        int index = 0;
        boolean flag = false;
        L:
        while (true) {
            if (check()) break;
            if (word[index] != 'A') {
                int num = (int) word[index];
                answer += map.get(num);
                word[index] = 'A';
            }
            if (check()) break;
            if (flag) {
                answer++;
                index--;
                continue L;
            }

            // 오른쪽 이동
            int rightCount = 0;
            for (int i = index + 1; i < size; i++) {
                if (word[i] != 'A') {
                    rightCount = i - index;
                    break;
                }
            }

            int leftCount = size - last + index;
            if (leftCount < rightCount) {
                flag = true;
                answer += leftCount;
                index = last;
            } else {
                answer += rightCount;
                index = index + rightCount;
            }

        }

        return answer;
    }


    public static boolean check() {
        for (int i = 0; i < word.length; i++) {
            if (word[i] != 'A') return false;
        }
        return true;
    }
}
 