/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eneskaracayhw1;

import java.util.Arrays;
import java.util.Random;

public class RMQExperiment2 {
    public static void main(String[] args) {
        // Tek bir dizi boyutu
        int size = 10000; // Dizi boyutu
        int queryCount = 1000; // Yapılacak sorgu sayısı

        // Algoritmaların dizisi
        RMQAlgorithm[] algorithms = {
            new PrecomputeNone(),
            new PrecomputeAll(),
            new SparseTable(),
            new Blocking()
        };

        // Algoritmaların isimleri
        String[] algorithmNames = {"PrecomputeNone", "PrecomputeAll", "SparseTable", "Blocking"};

        // Array türleri
        String[] arrayTypes = {"Ordered", "Sorted", "Random", "Reversed"};

        // Farklı türlerde diziler oluştur
        int[] orderedArray = generateOrderedArray(size);
        int[] sortedArray = generateSortedArray(size);
        int[] randomArray = generateRandomArray(size);
        int[] reversedArray = generateReversedArray(size);

        // Her array tipi için işlemler
        for (String arrayType : arrayTypes) {
            int[] arrayToTest = switch (arrayType) {
                case "Ordered" -> orderedArray;
                case "Sorted" -> sortedArray;
                case "Random" -> randomArray;
                case "Reversed" -> reversedArray;
                default -> randomArray;
            };

            // Her algoritma için işlem yapalım
            for (int i = 0; i < algorithms.length; i++) {
                RMQAlgorithm algorithm = algorithms[i];
                String name = algorithmNames[i];

                // Ön işleme süresi ölçümü
                long startPreprocess = System.nanoTime();
                algorithm.preprocess(arrayToTest);
                long endPreprocess = System.nanoTime();
                // Saniyeye çevirmek için 1,000,000,000'a bölüyoruz
                double preprocessingTime = (endPreprocess - startPreprocess) / 1_000_000_000.0;  // saniye cinsinden

                // Sorgu süresi ölçümü
                long startQuery = System.nanoTime();
                for (int j = 0; j < queryCount; j++) {
                    int left = (int) (Math.random() * size / 2);
                    int right = left + (int) (Math.random() * (size / 2));
                    algorithm.query(left, right);
                }
                long endQuery = System.nanoTime();
                // Saniyeye çevirmek için 1,000,000,000'a bölüyoruz
                double queryTime = (endQuery - startQuery) / 1_000_000_000.0;  // saniye cinsinden

                // Sonuçları ekrana yaz
                System.out.printf(size + " - " + arrayType + " - " + name + " - Preprocessing: %.9f s, Query: %.9f s\n", preprocessingTime, queryTime);
            }
        }
    }

    // Ordered array oluştur (artık sıralı)
    private static int[] generateOrderedArray(int size) {
        int[] array = generateRandomArray(size);
        Arrays.sort(array); // Diziyi artan sırada sıralar
        return array;
    }

    // Sorted array oluştur
    private static int[] generateSortedArray(int size) {
        return generateOrderedArray(size); // Sorted = Ordered dizisi
    }

    // Reversed array oluştur
    private static int[] generateReversedArray(int size) {
        int[] array = generateSortedArray(size); // İlk önce sıralı dizi oluştur
        for (int i = 0; i < size / 2; i++) {
            int temp = array[i];
            array[i] = array[size - i - 1];
            array[size - i - 1] = temp;
        }
        return array;
    }

    // Random array oluştur
    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10000);
        }
        return array;
    }
}
