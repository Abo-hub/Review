package com.sun.nowcoder.isPopOrder;

import java.util.Stack;

/**
 * @program: Review
 * @author: SunBo
 * @create: 2019-07-31 20:35
 **/

public class IsPopOrder {

    public boolean isPopOrder(int [] pushA,int [] popA) {
        if (pushA.length == 0 || popA.length == 0){
            return false;
        }
        Stack<Integer> stack = new Stack<Integer>();
        int popIndex = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (!stack.empty() && stack.peek() == popA[popIndex]){
                stack.pop();
                popIndex++;
            }
        }
        return stack.empty();
    }
}
