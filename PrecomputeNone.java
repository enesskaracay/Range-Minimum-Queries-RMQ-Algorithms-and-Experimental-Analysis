/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eneskaracayhw1;

public class PrecomputeNone implements RMQAlgorithm {
    private int[] array;

    @Override
    public void preprocess(int[] array) {
        this.array = array; // Sadece diziyi sakla
    }

    @Override
    public int query(int left, int right) {
        int min = Integer.MAX_VALUE;
        for (int i = left; i <= right; i++) {
            min = Math.min(min, array[i]);
        }
        return min;
    }
}
