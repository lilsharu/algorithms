package UnionFind;

public class WeightedQuickUnionUFCanonical {
    private int[] size;
    private int[] id;
    private int[] maxInt;

    public WeightedQuickUnionUFCanonical(int N) {
        id = new int[N];
        size = new int[N];
        maxInt = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
            maxInt[i] = i;
        }
    }

    private int root(int i) {
        /* Recursive Way:
        if (id[i] == i) return i;
        else return root(id[i]);
         */
        while (i != id[i]) {
            id[i] = id[id[i]];//Path Compression
            i = id[i];
        }
        return i;
    }

    public void union(int p, int q) {
        int pid = root(p);
        int qid = root(q);
        if (pid == qid) return;
        if (size[pid] > size[qid]) {
            id[qid] = pid;
            size[pid] += size[qid];
            if (maxInt[qid] > maxInt[pid]) {
                maxInt[pid] = maxInt[qid];
            }
        } else {
            id[pid] = qid;
            size[qid] += size[pid];
            if (maxInt[pid] > maxInt[qid]) {
                maxInt[qid] = maxInt[pid];
            }
        }
    }

    public int find(int j) {
        return maxInt[root(j)];
    }
}
