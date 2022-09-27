package src.ADTMatrix;

public class Matrix {
    public double contents[][];
    public int row;
    public int col;

    public Matrix(double contents[][], int row, int col){
        this.contents = contents;
        this.row = row;
        this.col = col;
    }
}