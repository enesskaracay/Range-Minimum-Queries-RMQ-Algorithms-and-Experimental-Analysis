/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eneskaracayhw1;


import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class RMQExperiment4 {
    public static void main(String[] args) throws IOException {
        // Sabit dizi boyutu ve sorgu sayısı
        int arraySize = 10000; // Tek bir dizi boyutu
        int queryCount = 1000; // Tek bir sorgu sayısı
        String[] dataTypes = {"Positive", "Negative", "Mixed", "Constant"}; // Veri türleri

        // Sonuçları yazacağımız dosya
        

        // Algoritmaların dizisi
        RMQAlgorithm[] algorithms = {
            new PrecomputeNone(),
            new PrecomputeAll(),
            new SparseTable(),
            new Blocking()
        };

        // Algoritmaların isimleri
        String[] algorithmNames = {"PrecomputeNone", "PrecomputeAll", "SparseTable", "Blocking"};

        // Her veri türü için işlemler
        for (String dataType : dataTypes) {
            int[] arrayToTest = generateArrayByType(arraySize, dataType);

            // Her algoritma için işlem yapalım
            for (int i = 0; i < algorithms.length; i++) {
                RMQAlgorithm algorithm = algorithms[i];
                String name = algorithmNames[i];

                // Ön işleme süresi ölçümü
                long startPreprocess = System.nanoTime();
                algorithm.preprocess(arrayToTest);
                long endPreprocess = System.nanoTime();
                double preprocessingTime = (endPreprocess - startPreprocess) / 1_000_000_000.0;  // saniye cinsinden

                // Sorgu süresi ölçümü
                long startQuery = System.nanoTime();
                for (int j = 0; j < queryCount; j++) {
                    int left = (int) (Math.random() * arraySize / 2);
                    int right = left + (int) (Math.random() * (arraySize / 2));
                    algorithm.query(left, right);
                }
                long endQuery = System.nanoTime();
                double queryTime = (endQuery - startQuery) / 1_000_000_000.0;  // saniye cinsinden

                
               

                // Sonuçları ekrana yaz
                System.out.printf(arraySize + " - " + dataType + " - " + name + " - Preprocessing: %.9f s, Query: %.9f s\n", preprocessingTime, queryTime);
            }
        }
     
    }

    // Farklı veri türlerine göre dizi oluşturma
    private static int[] generateArrayByType(int size, String dataType) {
        switch (dataType) {
            case "Positive":
                return generatePositiveArray(size);
            case "Negative":
                return generateNegativeArray(size);
            case "Mixed":
                return generateMixedArray(size);
            case "Constant":
                return generateConstantArray(size);
            default:
                return generatePositiveArray(size);
        }
    }

    // Pozitif sayılarla dizi oluştur
    private static int[] generatePositiveArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10000); // 0-9999 arasında rastgele pozitif sayılar
        }
        return array;
    }

    // Negatif sayılarla dizi oluştur
    private static int[] generateNegativeArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = -random.nextInt(10000); // -10000 ile 0 arasında rastgele negatif sayılar
        }
        return array;
    }

    // Karışık sayılarla dizi oluştur
    private static int[] generateMixedArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            if (random.nextBoolean()) {
                array[i] = random.nextInt(10000); // Pozitif
            } else {
                array[i] = -random.nextInt(10000); // Negatif
            }
        }
        return array;
    }

    // Sabit sayılarla dizi oluştur
    private static int[] generateConstantArray(int size) {
        int[] array = new int[size];
        Arrays.fill(array, 5); // Tüm elemanlar 5 olacak şekilde dizi oluştur
        return array;
    }
}
