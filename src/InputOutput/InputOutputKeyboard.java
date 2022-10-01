package src.InputOutput;

import java.util.*;
import src.ADTMatrix.Matrix;
import src.ADTMatrix.PasanganMatrix;
import src.Primitif.Primitif;

public class InputOutputKeyboard {
    public static Matrix inputSPL(){
        Matrix m, x;
        int bar, kol;
        double[][] mTemp; 
        double[] xTemp;
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan banyak baris ");
        bar = input.nextInt();
        System.out.print("Masukkan banyak kolom ");
        kol = input.nextInt();
        mTemp = new double[bar][kol];
        xTemp = new double[bar];
        for(int i=0;i<bar;i++){
            for(int j=0;j<kol;j++){
                System.out.printf("%s%d,%d ", "Masukkan matriks a ke-", i, j);
                mTemp[i][j] = input.nextDouble();
            }
        }
        for(int i=0;i<bar;i++){
            System.out.printf("%s%d ", "Masukkan matriks b ke-", i);
            xTemp[i] = input.nextDouble();
        }

        m = new Matrix(mTemp, bar, kol);

        return Primitif.tambahKolom(m, xTemp);
    }

    public static Matrix inputSquare(){
        Matrix m;
        double[][] mTemp;
        int n;

        Scanner input = new Scanner(System.in);
        System.out.println("Masukkan ukuran matriks square");
        n = input.nextInt();

        mTemp = new double[n][n];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.printf("%s%d,%d ", "Masukkan matriks a ke-", i, j);
                mTemp[i][j] = input.nextDouble();
            }
        }

        m = new Matrix(mTemp, n, n);

        return m;
    }

    public static Matrix inputPolinom(){
        Matrix m;
        double[][] mTemp;
        int n; 
        double x,y;

        Scanner input = new Scanner(System.in);
        System.out.println("Masukkan n");
        n = input.nextInt();
        mTemp = new double[n+1][n+2];

        for(int i=0;i<=n;i++){
            System.out.printf("%s%d ","Masukkan x ke-",i);
            x = input.nextDouble();
            System.out.printf("%s%d ","Masukkan y ke-",i);
            y = input.nextDouble();
            for(int j=0;j<=n+1;j++){
                if(j<=n){
                    mTemp[i][j] = Math.pow(x, j);
                }else{
                    mTemp[i][j] = y;
                }
            }
        }
        
        m = new Matrix(mTemp, n, n+1);
        return m;
    }
}
