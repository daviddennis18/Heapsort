/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import static java.lang.System.currentTimeMillis;

public class Testing {

    public static double comparisons = 0;
    public static double swaps = 0;

    public void sort(int arr[]) {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // One by one extract an element from heap
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    void heapify(int arr[], int n, int i) {
        int largest = i;  // Initialize largest as root
        int l = 2 * i + 1;  // left = 2*i + 1
        int r = 2 * i + 2;  // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr[l] > arr[largest]) {
            comparisons++;
            largest = l;
        }

        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest]) {
            comparisons++;
            largest = r;
        }

        // If largest is not root
        if (largest != i) {
            comparisons++;
            swaps++;
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    /* A utility function to print array of size n */
    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            System.out.print(arr[i] + "\n");
        }
        System.out.println();
    }

    // Driver program
    public static void main(String args[]) {

        int n = 10000000;
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * 1001);
        }

        double start = 0;
        double finish = 0;
        double total = 0;

        Testing ob = new Testing();
        start = (double) currentTimeMillis();
        ob.sort(arr);
        finish = (double) currentTimeMillis();

        total = finish - start;

        System.out.println("The runtime of this sort is " + (double)total/1000 + " seconds");
        System.out.println("The number of comparisons made is " + comparisons);
        System.out.println("The number of swaps made is " + swaps);
        
    }
}
