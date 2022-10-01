package src.InputOutput;

import src.ADTMatrix.Matrix;
import src.Algoritma.*;
import src.Primitif.*;

import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String pilihan;
        pilihan = mainMenu();
        switch(pilihan){
            case "11":
                Gauss.gaussSPL();
                break;
            case "12":
                GaussJordan.gaussJordanSPL();
                break;
            case "13":
                Invers.inversSPL();
                break;
            case "14":
                Cramer.cramer();
                break;
            case "21":
                Determinant.showDetOBE();
                break;
            case "22":
                Determinant.showDetCofactor();
                break;
            case "31":
                Invers.showInversOBE();
                break;
            case "32":
                Invers.showInversCofactor();
                break;
            case "4":
                PolinomInterpolation.polinomInterpolation();
                break;
            case "5":
                BicubicInterpolation.bicubicInterpolate();
                break;
            case "6":
                RegresiBerganda.MLR();
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
        return pilihan;
    }
}
