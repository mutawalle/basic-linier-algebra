package src.InputOutput;

import src.ADTMatrix.Matrix;
import src.Algoritma.*;
import src.Primitif.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Matrix m;
        String pilihan;
        double[] x;
        m = Primitif.readMatrix();
        x = Primitif.readX(m.col);
        pilihan = mainMenu();
        switch(pilihan){
            case "11":
                Gauss.gaussSPL(m, x);
                break;
            case "12":
                GaussJordan.gaussJordanSPL(m, x);
                break;
            case "13":
                Invers.inversSPL(m, x);
                break;
            case "14":
                Cramer.cramer(m, x);
                break;
            case "21":
                Determinant.showDetOBE(m);
                break;
            case "22":
                Determinant.showDetCofactor(m);
                break;
            case "31":
                Invers.showInversOBE(m);
            case "32":
                Invers.showInversCofactor(m);
                break;
        }
    }

    public static String mainMenu() {
        String pilihan;
        Scanner input = new Scanner(System.in);
        System.out.println("Main Menu");
        System.out.println("1. Sistem Persamaaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic");
        System.out.println("6. Regresi linier berganda");
        System.out.println("7. Keluar");
        pilihan = input.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        switch(pilihan){
            case "1":
                System.out.println("1. Metode Eliminasi Gauss");
                System.out.println("2. Metode Eliminasi Gauss-Jordan");
                System.out.println("3. Metode Matriks Balikan");
                System.out.println("4. Kaidah Cramer");
                pilihan += input.nextLine();
                break;
            case "2":
                System.out.println("1. Metode Matriks Segitiga");
                System.out.println("2. Metode Kofaktor");
                pilihan += input.nextLine();
                break;
            case "3":
                System.out.println("1. Metode Gauss-Jordan");
                System.out.println("2. Metode Kofaktor");
                pilihan += input.nextLine();
                break;
        }
        input.close();
        return pilihan;
    }
}
