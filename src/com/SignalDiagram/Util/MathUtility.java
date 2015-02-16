/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SignalDiagram.Util;

/**
 *
 * @author Dom
 */
public class MathUtility {

    // Fisher-Yates shuffle algorithm.
    public static int[] FisherYatesShuffle(int[] array, int seed) {
        java.util.Random rdm = new java.util.Random();
        rdm.setSeed(seed);

        for (int i = array.length - 1; i > 0; i--) {
            int j = (int) Math.floor(rdm.nextDouble() * (i + 1));
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        return array;
    }
}
