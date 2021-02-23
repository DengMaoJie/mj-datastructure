package io.maojie.datastructure.sparse_array;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SparseArray {

    public static void main(String[] args)  throws IOException {
        int originArr[][]= new int[11][11] ;
        originArr[1][2] = 1;
        originArr[2][3] = 2;

        int sum = 0 ;
        for (int[] e : originArr) {
            for (int e1 : e) {
                if (e1!=0){
                    sum ++;
                }
            }
        }

        //初始化稀疏数组
        int sparseArr[][] = new int[sum+1][3];
        //赋值第一行
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //赋值后面的行
        int currentRow = 1;
        for (int i = 0; i < originArr.length; i++) {
            int arrI[] = originArr[i];
            for (int i1 = 0; i1 < arrI.length; i1++) {
                if (arrI[i1]!=0){
                    sparseArr[currentRow][0] = i;
                    sparseArr[currentRow][1] = i1;
                    sparseArr[currentRow][2] = arrI[i1];
                    currentRow++;
                }
            }
        }

        //打印稀疏数组

        for (int i = 0; i < sparseArr.length; i++) {
            System.out.println(sparseArr[i][0]+"    "+sparseArr[i][1]+"    "+sparseArr[i][2]+"    ");
        }

        //稀疏数组 转 二维数组
        int[][] chessArrary = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            chessArrary[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //打印二维数组
        for (int i = 0; i < chessArrary.length; i++) {
            for (int i1 = 0; i1 < chessArrary[i].length; i1++) {
                System.out.print(chessArrary[i][i1]+"   ");
            }
            System.out.println("");
        }

        //将稀疏数组保存到磁盘
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sparseArr.length; i++) {
            sb.append(sparseArr[i][0]+"    "+sparseArr[i][1]+"    "+sparseArr[i][2]+"    \n");
        }
        FileOutputStream fos = new FileOutputStream("S:\\devData\\my_git\\mj-datastructure\\map.data");
        fos.write(sb.toString().getBytes());

        //从文件中读取稀疏数组
        FileInputStream fis = new FileInputStream("S:\\devData\\my_git\\mj-datastructure\\map.data");
        StringBuilder sbi = new StringBuilder();
        byte[] buffer = new byte[100];
        while (fis.read(buffer) != -1){
            sbi.append(new String(buffer));
        }

        System.out.println(sbi);

    }
}
