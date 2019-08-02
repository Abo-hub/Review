# 顺时针打印矩阵

## 【题目】
给定一个矩阵matrix，请按照顺时针的方式打印它。
例如： 
```java    
1   2   3   4   
5   6   7   8   
9   10  11  12  
13  14  15  16  
``` 
打印结果为：1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10

##【解答】

本体上算法没有难度，关键在于设计一种容易理解、代码易于实现的方式。我们可以采用矩阵分圈处理。在矩阵中用左上角的坐标(lx,ly)和右下角的坐标(rx,ry)就可以表示一个子矩阵。比如题目中的矩阵，当(lx,ly)=(0,0)、(rx,ry)=(3,3)时，表示的子矩阵就是整个矩阵，那么这个子矩阵的最外层的部分如下：
```java
1   2   3   4
5           8
9           12
13  14  15  16
``` 
我们可以先把矩阵的最外层打印出来，接下来令lx和ly加1，rx和ry减1，即(lx,ly)=(1,1)、(rx,ry)=(2,2),此时表示的子矩阵如下：
```java
6   7
10  11
``` 
再把这个子矩阵转圈打印出来即可。如果发现左上角坐标跑到了右下角的右方或下方，整个过程结束。我们将每次打印的结果放到一个list数组里最后输出即可。(当然，你也可以选择每次循环时直接打印值)
具体过程参看如下代码：
```java
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
}


```