/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eneskaracayhw1;


import java.io.IOException;
import java.util.Random;

public class RMQExperiment3 {
    public static void main(String[] args) throws IOException {
        // Sabit dizi boyutu
        int arraySize = 10000; // Tek bir dizi boyutu kullanıyoruz
        int[] queryCounts = {100, 500, 1000, 5000}; // Test edilecek sorgu sayıları

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

        // Rastgele bir dizi oluştur
        int[] sampleArray = generateRandomArray(arraySize);
        String arrayType = "Random"; // Kullanılan dizi türünü belirtmek için

        // Her sorgu sayısı için işlem yapalım
        for (int queryCount : queryCounts) {
            // Her algoritma için işlem yapalım
            for (int i = 0; i < algorithms.length; i++) {
                RMQAlgorithm algorithm = algorithms[i];
                String name = algorithmNames[i];

                // Ön işleme süresi ölçümü
                long startPreprocess = System.nanoTime();
                algorithm.preprocess(sampleArray);
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
                System.out.printf(arraySize + " - " + queryCount + " queries - " + name + " - Preprocessing: %.9f s, Query: %.9f s\n", preprocessingTime, queryTime);
            }
        }

      
    }

    // Rastgele bir dizi oluşturma
    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10000); // 0-9999 arasında rastgele sayılar
        }
        return array;
    }
}
