import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Lab1 {
    public static void main(String args[]) {
        Scanner userInput = new Scanner(System.in);
        double seriesSum = 0;
        int n = 0;

        System.out.print("Enter Number: ");
        if (userInput.hasNextInt()) {
            n = userInput.nextInt();
        } else {
            System.out.println("Ups problem...");
        }

        if (n < 15) {
            seriesSum = 0;
            for (int i = 1; i <= n; i++) {
                seriesSum += 1 / (2 * Math.pow( i, 2 * i - 1));
            }
            System.out.println(seriesSum);
        } else {
            BigDecimal seriesSumdouble = new BigDecimal("0.0");
            for (int i = 1; i <= n; i++) {

                seriesSumdouble = seriesSumdouble.add((BigDecimal.valueOf(1)
                        .divide(BigDecimal.valueOf(2).multiply(BigDecimal.valueOf(i).pow(2 * i - 1)),n,RoundingMode.HALF_UP)));
            }
            System.out.println(seriesSumdouble);
        }
        userInput.close();
    }
}
