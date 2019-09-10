# 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

示例 1:
```
输入: 123
输出: 321
 示例 2:
```
```
输入: -123
输出: -321
```
示例 3:
```
输入: 120
输出: 21
```
> 注意:假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。

## 【思路】
- int类型 取值范围[−2^31,  2^31 − 1]也就是[-2147483648,2147483647]
- 首先，设当前计算结果为`rev`，下一位为`pop`
- 通过循环将数字`x`的每一位拆开，在计算新值时每一步都判断是否溢出，将拆开的数字拼在rev的后面。
- 溢出条件有两个
    - 反转后的数大于`MAX_VALUE`
    - 反转后的数小于`MIN_VALUE`
- 从`rev * 10 + pop > MAX_VALUE` 这个溢出条件来看
    - 当出现`rev > MAX_VALUE / 10`且`pop还要添加`时，则一定会溢出
    - 当出现`rev == MAX_VALUE / 10`且`pop>7`时，则一定会溢出(假设rev=214748364，当pop>7时，会超出最大范围)
- 从`rev * 10 + pop < MIN_VALUE`这个溢出条件来看
    - 当出现`ans < MIN_VALUE / 10`且 还有`pop`需要添加 时，则一定溢出
    - 当出现`ans == MIN_VALUE / 10`且`pop < -8`时，则一定溢出，8是-2^31的个位数


## 【代码】
```java

public  int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }
```