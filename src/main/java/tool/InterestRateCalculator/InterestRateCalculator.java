package tool.InterestRateCalculator;

/**
 * Gernerated by ChatGPT,hhhhhh
 */
public class InterestRateCalculator {
    public static double calculateMonthlyInterestRate(double principal, int period, double monthlyRepayment) {
        double low = 0.0;
        double high = 1.0;
        double mid = 0.0;
        double epsilon = 0.00001;

        while (high - low > epsilon) {
            mid = (low + high) / 2;
            double temp = principal * mid * Math.pow(1 + mid, period) / (Math.pow(1 + mid, period) - 1);
            if (temp < monthlyRepayment) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return mid;
    }

    public static void main(String[] args) {
        double principal = 150000.0;
        int period = 60;
        double monthlyRepayment = 2921.0;
        double monthlyInterestRate = calculateMonthlyInterestRate(principal, period, monthlyRepayment);
        String yearlyInterest = String.format("%.3f", monthlyInterestRate * 12 * 100).toString() + "%";
        System.out.println("yearly Interest Rate: " + yearlyInterest);
    }
}
