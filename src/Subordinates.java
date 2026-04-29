import java.io.*;
import java.util.*;

public class Subordinates {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] parent = new int[n + 1];
        int[] childCount = new int[n + 1];
        int[] sub = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        // Leer padres
        for (int i = 2; i <= n; i++) {
            parent[i] = Integer.parseInt(st.nextToken());
            childCount[parent[i]]++; // contar hijos
        }

        // Cola para nodos listos (hojas)
        Deque<Integer> queue = new ArrayDeque<>();

        // Inicializar con hojas
        for (int i = 1; i <= n; i++) {
            if (childCount[i] == 0) {
                queue.add(i);
            }
        }

        // Procesamiento tipo topológico
        while (!queue.isEmpty()) {
            int u = queue.poll();

            int p = parent[u];
            if (p != 0) { // 1 no tiene padre
                sub[p] += 1 + sub[u];
                childCount[p]--;

                if (childCount[p] == 0) {
                    queue.add(p);
                }
            }
        }

        // Output
        StringBuilder out = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            out.append(sub[i]).append(" ");
        }

        System.out.println(out);
    }
}