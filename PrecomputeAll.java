/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eneskaracayhw1;

public class PrecomputeAll implements RMQAlgorithm {
    private int[][] precomputedMin;

    @Override
    public void preprocess(int[] array) {
        int n = array.length;
        precomputedMin = new int[n][n];

        for (int i = 0; i < n; i++) {
            precomputedMin[i][i] = array[i];
            for (int j = i + 1; j < n; j++) {
                precomputedMin[i][j] = Math.min(precomputedMin[i][j - 1], array[j]);
            }
        }
    }

    @Override
    public int query(int left, int right) {
        return precomputedMin[left][right];
    }
}

