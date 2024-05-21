/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // creates n-by-n grid, with all sites initially blocked
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufFull;
    private boolean[][] openSites;
    private int n;
    private int openSiteCount;
    private int virtualTop;
    private int virtualBottom;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive integer.");
        }
        this.n = n;
        uf = new WeightedQuickUnionUF(n * n + 2);
        ufFull = new WeightedQuickUnionUF(n * n + 1);
        openSites = new boolean[n][n];
        openSiteCount = 0;
        virtualTop = n * n;
        virtualBottom = n * n + 1;
    }

    private void validate(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) {
            throw new IllegalArgumentException("Index out of bounds");
        }
    }

    private int index(int row, int col) {
        validate(row, col);
        return (row - 1) * n + col - 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            openSites[row - 1][col - 1] = true;
            openSiteCount++;
            int index = index(row, col);

            if (row == 1) {
                uf.union(index, virtualTop);
                ufFull.union(index, virtualTop);
            }
            if (row == n) {
                uf.union(index, virtualBottom);
            }
            if (row < n && isOpen(row + 1, col)) {
                uf.union(index, index(row + 1, col));
                ufFull.union(index, index(row + 1, col));
            }
            if (row > 1 && isOpen(row - 1, col)) {
                uf.union(index, index(row - 1, col));
                ufFull.union(index, index(row - 1, col));
            }
            if (col < n && isOpen(row, col + 1)) {
                uf.union(index, index(row, col + 1));
                ufFull.union(index, index(row, col + 1));
            }
            if (col > 1 && isOpen(row, col - 1)) {
                uf.union(index, index(row, col - 1));
                ufFull.union(index, index(row, col - 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return openSites[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return ufFull.find(index(row, col)) == ufFull.find(virtualTop);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSiteCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(virtualTop) == uf.find(virtualBottom);
    }

    // test client (optional)
    public static void main(String[] args) {
    }
}
