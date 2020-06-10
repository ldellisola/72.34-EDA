package Class;

import Utils.Timer;

public class Main {

    public static final int N = 1000000000;

    public static void main(String[] args) {
        // generate array
        int[] myArray = new int[N];

        for (int rec = N; rec > 0; rec--)
            myArray[N - rec] = rec;

        // performance testing
        Timer t1 = new Timer();
        int rta1 = MaxCustom.max(myArray);
        t1.stop();

        Timer t2 = new Timer();
        int rta2 = MaxNative.max(myArray);
        t2.stop();

        System.out.println(String.format("MaxCustom %d. Delay %d (ms)", rta1, t1.getElapsedTime()));
        System.out.println(String.format("MaxNative %d. Delay %d (ms)", rta2, t2.getElapsedTime()));
    }

}
