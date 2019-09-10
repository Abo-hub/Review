package com.sun.tree;

/**
 * @program: Review
 * @author: SunBo
 * @create: 2019-09-03 11:28
 **/

public class AVLTreeDemo {


}

/**
 * 创建AVLTree
 */
class AVLTree{
    private Node root;

    public Node getRoot() {
        return root;
    }

    // 查找要删除的结点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    public Node searchParent(int value){
        if (root == null){
            return null;
        }else {
            return root.searchParent(value);
        }
    }
}

/**
 * 创建Node结点
 */
class Node{
    int value;
    Node left;
    Node right;

    public Node(int value){
        this.value = value;
    }

    /**
     * 返回以该节点为根节点的树的高度
     * @return
     */
    public int height(){
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height())+1;
    }

    /**
     * 返回左子树的高度
     * @return
     */
    public int leftHeight(){
        if (left == null){
            return 0;
        }
        return left.height();
    }

    /**
     * 返回右子树的高度
     * @return
     */
    public int rightHeiht(){
        if (right == null){
            return 0;
        }
        return right.height();
    }

}
