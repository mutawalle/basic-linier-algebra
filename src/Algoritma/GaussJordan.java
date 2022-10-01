package src.Algoritma;

import java.util.Scanner;

import javax.xml.transform.Source;

import src.ADTMatrix.*;
import src.Primitif.Primitif;
import src.InputOutput.*;

public class GaussJordan {
    public static void gaussJordanSPL(){
        Matrix m;
        Scanner input = new Scanner(System.in);
        System.out.println("Ketik 1 untuk input keyboard, Ketik lainnya untuk input file");
        System.out.println("Dan Pastikan file ada di folder test jika pilih input file");
        int pilihan = input.nextInt();
        if(pilihan==1){
            m = InputOutputKeyboard.inputSPL();
        }else{
            m =  InputOutputFile.bacaFile();
        }
        m = gaussJordan(m);

        if(m.col-1>m.row){
            System.out.println("Tidak meiliki solusi");
        }else{
            // asumsi solusi unik
            // jadi disini harusnya ada kondisi buat ngehandle solusi parametrik dibuat if else mungkin
            // ini langsung output nilai x yang didapat setelah gaussjordan, matriks berbentuk augmented matriks jadi tinggal outputin semua nilai di kolom terkahir karena asumsi unik
            Matrix hasil;
            hasil = new Matrix(m.contents, m.row, 1);
            for(int i=0;i<m.row;i++){
                System.out.println(m.contents[i][m.col-1]);
                hasil.contents[i][0] = m.contents[i][m.col-1];
            }
            InputOutputFile.OutputSPL(hasil);
        }

    }
    public static Matrix gaussJordan(Matrix m) {
        m = Gauss.gauss(m);
        // membuat kolom di atas 1 menjadi 0
        int baris = m.row-1;
        while(baris>=0 && m.contents[baris][m.col-2] != 1.00){
            baris--;
        }
        if(baris>=0){
            for(int i=baris;i>=0;i--){
                for(int j=i-1;j>=0;j--){
                    double pengurang = m.contents[j][i];
                    for(int k=0;k<m.col;k++){
                        m.contents[j][k] -= pengurang*m.contents[i][k];
                    }
                }
            }
        }

        return m;
    }
}
