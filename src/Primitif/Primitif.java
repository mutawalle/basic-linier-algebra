package src.Primitif;

import java.util.Scanner;

import src.ADTMatrix.Matrix;

public class Primitif {
    public static Matrix readMatrix() {
        // Kamus
        Scanner input = new Scanner(System.in);
        int r,c;
        double[][] matriks;
        Matrix m;

        // input row dan column
        System.out.print("Masukkan jumlah baris: ");
        r = input.nextInt();
        System.out.print("Masukkan jumlah kolom: ");
        c = input.nextInt();

        // create matriks
        matriks = new double[r][c];

        // input matriks
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                matriks[i][j] = input.nextDouble();
            }
        }

        // create Matrix
        m = new Matrix(matriks, r, c);

        return m;
    }

    public static void displayMatrix(Matrix m){
        for(int i=0;i<m.row;i++){
            for(int j=0;j<m.col;j++){
                if(j>0){
                    System.out.print(" ");
                }
                System.out.printf("%.2f", m.contents[i][j]);
            }
            System.out.println();
        }
    }

    public static double[] readX(int n){
        // kamus
        double[] x;
        Scanner input = new Scanner(System.in);

        // input
        x = new double[n];
        for(int i=0;i<n;i++){
            x[i] = input.nextDouble();
        }

        return x;
    }
}

