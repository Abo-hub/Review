package com.sun.nowcoder.power;

/**
 * @program: Review
 * @author: SunBo
 * @create: 2019-07-30 21:18
 **/

public class Power {

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
            if ((exponent & 1) == 1){
                res *= curr;
            }
            // 翻倍
            curr *= curr;
            // 右移一位
            exponent >>= 1;
        }
        return n >= 0 ? res : (1 / res);
    }
}
