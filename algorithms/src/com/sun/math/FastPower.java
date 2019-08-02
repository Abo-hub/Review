package com.sun.math;

/**
 * @program: Review
 * @author: SunBo
 * @create: 2019-07-31 11:19
 **/

public class FastPower {
    /**
     * 递归实现
     * @param a
     * @param n
     * @return
     */
    public double power1(double a, int n) {
        if (n == 0) {
            return 1;
        }
        double res = power1(a,n/2);
        if((n%2)==1){
            return res * res * a;
        }else{
            return res * res;
        }
    }

    public double power2(double a,int n){
        double res = 1;
        while (n>0){
            if((n&1)==1){
                res*=a;
            }
            a*=a;
            n>>=1;
        }
        return res;
    }

    public static void main(String[] args) {
        FastPower fastPower = new FastPower();

        double res1 = fastPower.power1(3, 11);
        System.out.println(res1);
        double res2 = fastPower.power2(3, 11);
        System.out.println(res2);
    }
}
