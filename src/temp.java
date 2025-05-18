import java.util.HashMap;
import java.util.Map;

public class temp {
    int findsum(int n){
        int sum=0;
        while(n>0){
            sum+=(n%10);
            n/=10;
        }
        return sum;
    }
    public int countLargestGroup(int n) {
        HashMap<Integer,Integer> mp=new HashMap<>();
        int mx_group=0;
        int cnt=0;
        for(int i=1;i<=n;i++){
            int x=findsum(i);
            mp.put(x,mp.getOrDefault(x,0)+1);
            mx_group=Math.max(mx_group,mp.get(x));
        }
        for(Map.Entry<Integer,Integer> entry: mp.entrySet()){
            if(entry.getValue()==mx_group){
                cnt++;
            }
        }
        return cnt;
    }
    public static void main(String[] args) {
System.out.println("Hello");
    }
}
