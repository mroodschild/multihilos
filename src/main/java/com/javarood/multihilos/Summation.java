package com.javarood.multihilos;

/**
 *
 * @author Matías Roodschild <mroodschild@gmail.com>
 */
public class Summation extends Thread {

    private int id;

    private double[] A;
    private double[] B;
    private double[] C;

    private int low, high;

    public Summation(double[] a, double[] b, double[] c, int low, int high) {
        this.A = a;
        this.B = b;
        this.C = c;
        this.low = low;
        this.high = Math.min(high, a.length);
    }

    public void run() {
        long tiempo = System.currentTimeMillis();
        sum(A, B, C, low, high);
        System.out.println(getId() + " tiempo hilo: " + (System.currentTimeMillis() - tiempo) + "\tinicio: " + tiempo + "\tfin: " + System.currentTimeMillis());
    }

    public static void sum(double[] a, double[] b, double[] c) {
        sum(a, b, c, 0, a.length);
    }

    public static void sum(double[] a, double[] b, double[] c, int low, int high) {
        for (int i = low; i < high; i++) {
            c[i] = a[i] + b[i];
        }
    }

    public static void parallelSum(double[] a, double[] b, double[] c) {
        parallelSum(a, b, c, Runtime.getRuntime().availableProcessors());
        //return parallelSum(arr, 8);
    }

    public static void parallelSum(double[] a, double[] b, double[] c, int threads) {
        int size = (int) Math.ceil(a.length * 1.0 / threads);

        Summation[] sums = new Summation[threads];

        for (int i = 0; i < threads; i++) {
            sums[i] = new Summation(a, b, c, i * size, (i + 1) * size);
            sums[i].setPriority(MAX_PRIORITY);
            sums[i].start();
        }

        try {
            for (Summation sum : sums) {
                sum.join();
            }
        } catch (InterruptedException e) {
        }
    }

    public void setId(int id) {
        this.id = id;
    }
}