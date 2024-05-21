/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    // perform independent trials on an n-by-n grid
    private double[] thresholds;
    private int trialsCount;
    private double confidence95;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and trials should be > 0.");
        }
        thresholds = new double[trials];
        this.trialsCount = trials;
        confidence95 = 1.96;
        for (int i = 0; i < trialsCount; i++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int row, col;
                do {
                    row = StdRandom.uniformInt(n) + 1;
                    col = StdRandom.uniformInt(n) + 1;
                } while (perc.isOpen(row, col));
                perc.open(row, col);
            }
            thresholds[i] = (double) perc.numberOfOpenSites() / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (confidence95 * stddev() / Math.sqrt(trialsCount));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (confidence95 * stddev() / Math.sqrt(trialsCount));
    }

    // test client (see below)
    public static void main(String[] args) {
        if (args.length < 3) {
            StdOut.println("Usage: java-algs4 PercolationStats <grid size> <number of trials>");
            return;
        }
        int n = Integer.parseInt(args[1]);
        int trial = Integer.parseInt(args[2]);
        PercolationStats stats = new PercolationStats(n, trial);
        StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stddev                  = " + stats.stddev());
        StdOut.println(
                "95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi()
                        + "]");
    }
}
