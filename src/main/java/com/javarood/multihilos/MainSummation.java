package com.javarood.multihilos;

import java.util.Random;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author Matías Roodschild <mroodschild@gmail.com>
 */
public class MainSummation {

    public static void main(String[] args) {
        int rows = 1;
        int cols = 5;

        Random rand = new Random();
        SimpleMatrix a = SimpleMatrix.random(rows, cols, 0, 100, rand);
        SimpleMatrix b = SimpleMatrix.random(rows, cols, 0, 100, rand);
        SimpleMatrix c = new SimpleMatrix(rows, cols);

        long tiempo = System.currentTimeMillis();

        Summation.sum(a.getMatrix().getData(), b.getMatrix().getData(), c.getMatrix().getData());

        System.out.println("Single: " + (System.currentTimeMillis() - tiempo)); // Single: 44
        c.zero();
        
        tiempo = System.currentTimeMillis();

        Summation.parallelSum(a.getMatrix().getData(), b.getMatrix().getData(), c.getMatrix().getData());
        System.out.println("tiempo total parallel: " + (System.currentTimeMillis() - tiempo) + "\tinicio: " + tiempo + "\tfin: " + System.currentTimeMillis());
        //System.out.println("Parallel: " + (System.currentTimeMillis() - tiempo)); // Parallel: 25
        a.print();
        b.print();
        c.print();
    }
}
