/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eneskaracayhw1;

/**
 *
 * @author EXCALIBUR
 */
public class SparseTable implements RMQAlgorithm {
    private int[][] sparseTable;
    private int[] logValues;

    @Override
    public void preprocess(int[] array) {
        int n = array.length;
        int maxLog = (int) (Math.log(n) / Math.log(2)) + 1;

        sparseTable = new int[n][maxLog];
        logValues = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            logValues[i] = logValues[i / 2] + 1;
        }

        for (int i = 0; i < n; i++) {
            sparseTable[i][0] = array[i];
        }

        for (int j = 1; (1 << j) <= n; j++) {
            for (int i = 0; (i + (1 << j) - 1) < n; i++) {
                sparseTable[i][j] = Math.min(sparseTable[i][j - 1], sparseTable[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    @Override
    public int query(int left, int right) {
        int length = right - left + 1;
        int log = logValues[length];
        return Math.min(sparseTable[left][log], sparseTable[right - (1 << log) + 1][log]);
    }
}

