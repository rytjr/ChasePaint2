package ChasePaint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class ChasePaint2 {

    static int[][] black, white;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        black = new int[n + 1][m + 1];
        white = new int[n + 1][m + 1];

        String s;
        for (int i = 1; i <= n; i++) {
            s = br.readLine();
            for (int j = 1; j <= m; j++) {
                if (s.charAt(j - 1) == 'B') {
                    if (check(i, j)) {
                        sumWhite(i, j);
                    } else {
                        sumBlack(i, j);
                    }
                } else {
                    if (check(i, j)) {
                        sumBlack(i, j);
                    } else {
                        sumWhite(i, j);
                    }
                }
            }
        }

        int result = 4000000;
        for (int i = 0; i <= n - k; i++) {
            for (int j = 0; j <= m - k; j++) {
                result = Math.min(
                    result,
                    Math.min(
                        black[i + k][j + k] - black[i][j + k] - black[i + k][j] + black[i][j],
                        white[i + k][j + k] - white[i][j + k] - white[i + k][j] + white[i][j]
                    )
                );
            }
        }

        System.out.println(result);
    }

    private static boolean check(int row, int col) {
        return row % 2 == 1 && col % 2 == 1 || row % 2 == 0 && col % 2 == 0;
    }

    private static void sumWhite(int row, int col) {
        black[row][col] = black[row - 1][col] + black[row][col - 1] - black[row - 1][col - 1];
        white[row][col] = white[row - 1][col] + white[row][col - 1] - white[row - 1][col - 1] + 1;
    }

    private static void sumBlack(int row, int col) {
        black[row][col] = black[row - 1][col] + black[row][col - 1] - black[row - 1][col - 1] + 1;
        white[row][col] = white[row - 1][col] + white[row][col - 1] - white[row - 1][col - 1];
    }
}
