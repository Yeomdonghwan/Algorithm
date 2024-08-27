import java.io.*;
import java.util.*;

public class Main {
    static class User{
        int lv;
        String name;

        public User(int lv, String name){
            this.lv = lv;
            this.name = name;
        }
    }
    static class Room{
        boolean isStarted = false;
        int minLv;
        int maxLv;

        List<User> users = new ArrayList<>();

        public Room(int minLv, int maxLv){
            this.maxLv = maxLv;
            this.minLv = minLv;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Room> rooms = new ArrayList<>();

        for(int i=0;i<p;i++){
            st = new StringTokenizer(br.readLine());

            int l = Integer.parseInt(st.nextToken());
            String n = st.nextToken();

            boolean found = false;
            for(int j=0;j< rooms.size();j++){
                if(rooms.get(j).minLv<= l && rooms.get(j).maxLv>= l && rooms.get(j).users.size()<m){
                    found=true;
                    rooms.get(j).users.add(new User(l,n));

                    if(rooms.get(j).users.size()==m){
                        rooms.get(j).isStarted=true;
                    }

                    break;
                }
            }
            if(!found){
                User user = new User(l, n);
                Room room = new Room(l-10, l+10);
                room.users.add(user);
                if(room.users.size()==m){
                    room.isStarted=true;
                }
                rooms.add(room);

            }

        }

        for(int i=0;i<rooms.size();i++){
            bw.write(rooms.get(i).isStarted?"Started!\n":"Waiting!\n");
            rooms.get(i).users.sort(new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return o1.name.compareTo(o2.name);
                }
            });
            for(int j=0;j<rooms.get(i).users.size();j++){
                bw.write(rooms.get(i).users.get(j).lv+" "+rooms.get(i).users.get(j).name+"\n");
            }
        }
        bw.flush();

    }
}
