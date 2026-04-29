import java.util.*;

public class Subordinates {
    static List<Integer>[] tree;
    static int[] subordinates;

    static void dfs(int node) {
        subordinates[node] = 0;

        for (int child : tree[node]) {
            dfs(child);
            subordinates[node] += 1 + subordinates[child];
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        tree = new ArrayList[n + 1];
        subordinates = new int[n + 1];

        for (int i = 1; i <= n; i++)
            tree[i] = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            int boss = sc.nextInt();
            tree[boss].add(i);
        }

        dfs(1); // root

        for (int i = 1; i <= n; i++)
            System.out.print(subordinates[i] + " ");
    }
}