package eneskaracayhw1;

import java.util.Random;

public class RMQExperiment1 {
    public static void main(String[] args) {
        // Farklı dizi boyutları
        int[] arraySizes = {100, 1000, 5000, 10000}; // Array boyutları
        int queryCount = 1000; // Her dizide yapılacak sorgu sayısı

        // Algoritmaların dizisi
        RMQAlgorithm[] algorithms = {
            new PrecomputeNone(),
            new PrecomputeAll(),
            new SparseTable(),
            new Blocking()
        };

        // Algoritmaların isimleri
        String[] algorithmNames = {"PrecomputeNone", "PrecomputeAll", "SparseTable", "Blocking"};

        // Her dizi boyutu için işlemler
        for (int size : arraySizes) {
            int[] sampleArray = generateRandomArray(size);

            // Her algoritma için işlem yapalım
            for (int i = 0; i < algorithms.length; i++) {
                RMQAlgorithm algorithm = algorithms[i];
                String name = algorithmNames[i];

                // Ön işleme süresi ölçümü
                long startPreprocess = System.nanoTime();
                algorithm.preprocess(sampleArray);
                long endPreprocess = System.nanoTime();
                // Saniyeye çevirmek için 1,000,000,000'a bölüyoruz (nanosecond -> second)
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
                System.out.printf(size + " - " + name + " - Preprocessing: %.9f s, Query: %.9f s\n", preprocessingTime, queryTime);
            }
        }
    }

    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10000);
        }
        return array;
    }
}
