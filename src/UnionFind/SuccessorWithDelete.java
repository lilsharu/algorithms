package UnionFind;

import java.util.Arrays;

public class SuccessorWithDelete {
    private int[] S;

    public SuccessorWithDelete(int N) {
        S = new int[N];
        for (int i = 0; i < N; i++) {
            S[i] = i;
        }
    }

    public SuccessorWithDelete(int[] S) {
        this.S = S;
    }

    public int[] remove(int x) {
        int[] nextS = new int[S.length - 1];
        int index = 0;
        for (int value : S) {
            if (value != x) {
                nextS[index] = value;
                index++;
            }
        }
        S = nextS;
        return nextS;
    }

    public int successor(int x) {
        Integer[] newS = new Integer[S.length];
        for (int i = 0; i < S.length; i++) {
            newS[i] = S[i];
        }
        Arrays.sort(newS);
        int min = 0;
        int max = newS.length - 1;
        int index = -1;
        while (min < max) {
            int mid = (max + min) / 2;
            if (newS[mid] > x) max = mid;
            else if (newS[mid] < x) min = mid;
            else {
                index = mid;
                break;
            }
        }

        return newS[index + 1];
    }
}
