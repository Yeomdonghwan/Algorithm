import java.util.*;

class Solution {
    
    static Map<Long,Long> map;
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        
        
        map= new HashMap<>();
        for(int i=0;i<room_number.length;i++){
            long room = find(room_number[i]);
            // System.out.println(room);
            answer[i]=room-1;
        }
        
        
        return answer;
    }
    static long find(long room){
        if(!map.containsKey(room)){
            //넣을 자리가 있으면 여기 넣음
            map.put(room, room+1);
            return room+1;
        }
        
        //방 이미 사용중임
        long next = find(map.get(room));
        map.put(room,next);//경로압축
        return next;
    }
}