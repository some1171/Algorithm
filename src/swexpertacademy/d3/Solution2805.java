package swexpertacademy.d3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int totalTestCaseCount, farmSize, centerValue, benefit;
        String[] temp;
        int[] farmLine;

        totalTestCaseCount = Integer.parseInt(in.readLine());

        for (int k = 0; k < totalTestCaseCount; k++) {
            benefit = 0;
            farmSize = Integer.parseInt(in.readLine());
            centerValue = (farmSize - 1) / 2;
            farmLine = new int[farmSize];

            for (int i = 0; i < (farmSize + 1) / 2; i++) {
                temp = in.readLine().split("");

                for (int j = 0; j < farmSize; j++) {
                    farmLine[j] = Integer.parseInt(temp[j]);
                }

                for (int j = centerValue - i; j < centerValue + i + 1; j++) {
                    benefit += farmLine[j];
                }
            }

            for (int i = (farmSize - 1) / 2 - 1; i >= 0; i--) {
                temp = in.readLine().split("");

                for (int j = 0; j < farmSize; j++) {
                    farmLine[j] = Integer.parseInt(temp[j]);
                }

                for (int j = centerValue - i; j < centerValue + i + 1; j++) {
                    benefit += farmLine[j];
                }
            }

            System.out.printf("#%d %d\n", (k + 1), benefit);
        }
    }
}