/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eneskaracayhw1;

/**
 *
 * @author EXCALIBUR
 */
public interface RMQAlgorithm {
    void preprocess(int[] array); // Ön işleme metodu
    int query(int left, int right); // Belirli bir aralık için minimumu sorgulama
}

