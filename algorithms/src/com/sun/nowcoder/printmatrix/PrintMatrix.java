package com.sun.nowcoder.printmatrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: Review
 * @author: SunBo
 * @create: 2019-07-25 19:37
 **/

public class PrintMatrix {
    ArrayList<Integer> list = new ArrayList<Integer>();
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        int lx = 0;
        int ly = 0; 
        int rx = matrix.length - 1;
        int ry = matrix[0].length - 1; 
        while (ly <= rx && ly <= ry) {
            printEdge(matrix, ly++, ly++, rx--, ry--);
        }

        return list;
    }

    public void printEdge(int[][] matrix, int lx, int ly, int rx, int ry) {
        //子矩阵只有一行时
        if (lx == rx) {
            for (int i = ly; i <= ry; i++) {
                list.add(matrix[lx][i]);
            }
        //子矩阵只有一列时
        } else if (ly == ry) {
            for (int i = lx; i <= rx; i++) {
                list.add(matrix[i][ly]);
            }
        } else {
            int curC = ly;
            int curR = lx;
            while (curC != ry) {
                list.add(matrix[lx][curC]);
                curC++;
            }
            while (curR != rx) {
                list.add(matrix[curR][ry]);
                curR++;
            }
            while (curC != ly) {
                list.add(matrix[rx][curC]);
                curC--;
            }
            while (curR != lx) {
                list.add(matrix[curR][ly]);
                curR--;
            }
        }
    }

    public static void main(String[] args) {
        PrintMatrix pm = new PrintMatrix();
        ArrayList li = new ArrayList();
        int[][] a = {{1,2},{3,4}};
        li = pm.printMatrix(a);
        for (Object o : li) {
            System.out.println(o);
        }
    }
}
