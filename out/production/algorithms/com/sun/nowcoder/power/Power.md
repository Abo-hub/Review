
# 数值的整数次方

## 【题目】
给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。

## 【解答】
### 方法一：
求 a^b 就是 a * a * a * a * a...* a  所以可以直接用循环 累乘。复杂度O(N)。不推荐。不做过多描述了

### 方法二： 快速幂
利用快速幂的算法。复杂度为O(log₂N)。如果不了解快速幂。戳[这里]()
```java
public double Power(double base, int n) {
        double res = 1, curr = base;
        int exponent;
        if (n > 0) {
            exponent = n;
        } else if (n < 0) {
            if (base == 0){
                throw new RuntimeException("分母不能为0");
            }
            exponent = -n;
        } else {// n==0
            // 0的0次方
            return 1;
        }
        while (exponent != 0) {
            //判断exponent是否为偶数
            if ((exponent & 1) == 1){
                res *= curr;
            }
            curr *= curr;
            // 右移一位
            exponent >>= 1;
        }
        return n >= 0 ? res : (1 / res);
    }
```