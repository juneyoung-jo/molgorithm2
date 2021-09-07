# 프로그래머스 42860 조이스틱

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42860)

## 1. 설계 로직

1. 고려해야할 사항 
   - name의 길이 ( 20 )
   - 첫 번째 위치에서 왼쪽으로 이동하면 마지막 문자에 커서
2. 설계로직
   1. 모든 단어가 'A' 에서 시작하므로 조작해야할 값은 정해져 있기 때문에 Map을 사용해서 미리 값을 담아 놓기.
      - 단어를 하나씩 넣기 불편하므로 아스키값 사용해서 Map을 생성
   2. name을 char배열로 만들어 각각의 인덱스를 'A' 로 변경하는 방법으로 문제 변경
   3. 첫번째 인덱스부터 탐색해서 'A' 로 변환
   4. 핵심 아이디어는 커서를 왼쪽으로 이동해야하는지 판별하고 왼쪽으로 이동했다면 더이상 신경 쓸 필요 없음.
      - 오른쪽으로 이동하는 값과 왼쪽으로 이동하는 값을 비교해서 왼쪽이 더 유리하다면 왼쪽으로 이동하고 그 이후로는 계속 왼쪽으로 가면 됨.
      - 오른쪽으로 이동하는 값은 현재 인덱스부터 다음인덱스로 탐색하다 보면 찾을 수 있음.
      - 왼쪽으로 이동하는 경우는 미리 마지막 바꿔야할 원소의 인덱스를 저장해 놓고 현재인덱스와 값 계산으로 알 수 있음.
3. 시간복잡도: O ( n ) 

## 2. 코드

```java
import java.util.*;

class Solution {
    static Map<Integer,Integer> map = new HashMap<>();
    static char[] word;
    
    public int solution(String name) {
        int answer = 0;
        int size = name.length();
        int last = 0;
        word = name.toCharArray();
        
        for(int i = 0; i < 13; i++) {
            map.put(i + 66,i+1);
            map.put(90 - i,i+1);
        }
        
        for(int i = size -1; i >= 0; i--) {
            if(word[i] != 'A') {
                last = i;
                break;
            }
        }
        
        int index = 0;
        boolean flag = false;
        L:while(true){
            if(check()) break;
            if(word[index] != 'A') {
                int num = (int)word[index];
                answer += map.get(num);
                word[index] = 'A';
            }
            if(check()) break;
            if(flag) {
                answer++;
                index--;
                continue L;
            }
            
            int rightCount = 0;
            for(int i = index+1; i<size; i++) {
                if(word[i] != 'A') {
                    rightCount = i - index;
                    break;
                }
            }
            
            int leftCount = size - last + index;
            if(leftCount < rightCount) {
                flag = true;
                answer += leftCount;
                index = last;
            }else{
                answer += rightCount;
                index = index+rightCount;
            }
                        
        }
        
        return answer;
    }
        
    public static boolean check(){
        for(int i = 0; i < word.length; i++) {
            if(word[i] != 'A') return false;
        }
        return true;
    }
}
```

## 3. 후기

- 아이디어는 빨리 떠올랐지만 구현이 생각보다  안 됐던 문제
