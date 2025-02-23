/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eneskaracayhw1;

/**
 *
 * @author EXCALIBUR
 */
public class Blocking implements RMQAlgorithm {
    private int[] blockMin;
    private int blockSize;
    private int[] array;

    @Override
    public void preprocess(int[] array) {
        this.array = array;
        int n = array.length;

        blockSize = (int) Math.ceil(Math.sqrt(n));
        int numBlocks = (int) Math.ceil((double) n / blockSize);
        blockMin = new int[numBlocks];

        for (int i = 0; i < numBlocks; i++) {
            blockMin[i] = Integer.MAX_VALUE;
            for (int j = i * blockSize; j < Math.min((i + 1) * blockSize, n); j++) {
                blockMin[i] = Math.min(blockMin[i], array[j]);
            }
        }
    }

    @Override
    public int query(int left, int right) {
        int min = Integer.MAX_VALUE;
        int startBlock = left / blockSize;
        int endBlock = right / blockSize;

        if (startBlock == endBlock) {
            for (int i = left; i <= right; i++) {
                min = Math.min(min, array[i]);
            }
        } else {
            for (int i = left; i < (startBlock + 1) * blockSize; i++) {
                min = Math.min(min, array[i]);
            }
            for (int i = startBlock + 1; i < endBlock; i++) {
                min = Math.min(min, blockMin[i]);
            }
            for (int i = endBlock * blockSize; i <= right; i++) {
                min = Math.min(min, array[i]);
            }
        }
        return min;
    }
}

