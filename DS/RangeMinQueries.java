import java.util.Arrays;

// 0 index based
class RangeMinQueries {
    int ga[];
    int st[];
    RangeMinQueries(){
        ga = new int[100001];
        st = new int[4*a.length];
        Arrays.fill(st, Integer.MAX_VALUE);
    }
    RangeMinQueries(int a[]){
        ga = a;
        st = new int[4*a.length];
        Arrays.fill(st, Integer.MAX_VALUE);
        for(int i=0;i<a.length;i++)
            update(i, ga[i]);
    }
    private int getMid(int s, int e){
        return s + (e-s)/2;
    }
    int minQuery(int stRoot, int stLeft, int stRight, int arrayLeft, int arrayRight){
        if(arrayLeft<=stLeft && arrayRight>=stRight)
            return st[stRoot];
        if(arrayLeft > stRight || arrayRight < stLeft)
            return Integer.MAX_VALUE;
        int mid = getMid(stLeft, stRight);
        return Math.min(
            minQuery(2*stRoot+1, stLeft, mid, arrayLeft, arrayRight),
            minQuery(2*stRoot+2, mid+1, stRight, arrayLeft, arrayRight)
        );
    }

    int minQuery(int arrayLeft, int arrayRight){
        return minQuery(0, 0, ga.length-1, arrayLeft, arrayRight);
    }

    int update(int stRoot, int stLeft, int stRight, int arrIdx, int arrVal){
        if(stLeft==stRight){
            st[stLeft] = ga[arrIdx];
            return ga[arrIdx];
        }
        int mid = getMid(stLeft, stRight); 
        int ans = Math.min(
            update(2*stRoot+1, stLeft, mid, arrIdx, arrVal), 
            update(2*stRoot+2, mid+1, stRight, arrIdx, arrVal)
        );
        return st[stRoot] = ans;
    }

    void update(int arrIdx, int arrVal){
        ga[arrIdx] = arrVal;
        update(0, 0, ga.length-1, arrIdx, arrVal);
    }

    public static void main(String args[]){
        int a[] = {5, 2, 7, 1, 3};
        RangeMinQueries rmq = new RangeMinQueries(a);
        System.out.println(rmq.minQuery(0, 4));
    }
}
