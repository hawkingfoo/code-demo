package com.demo.algo.tree;

import java.util.LinkedList;
import java.util.Stack;

public class TreeOperation {

    /* 获取树的高度 */
    public static int getTreeDepth(TreeNode root) {
        if (root == null)
            return 0;
        int lftDepth = getTreeDepth(root.left);
        int rhtDepth = getTreeDepth(root.right);
        return lftDepth > rhtDepth ? lftDepth + 1 : rhtDepth + 1;
    }
    /* 层次遍历 */
    public static void LevelTree(TreeNode root) {
        if (root == null)
            return;

        TreeNode current;
        LinkedList<TreeNode> list = new LinkedList();
        list.add(root);

        while (!list.isEmpty()) {
            current = list.poll();
            System.out.println(current.data);
            if (current.left != null)
                list.add(current.left);
            if (current.right != null)
                list.add(current.right);
        }
    }

    /* 先序遍历 */
    public static void MLRTree(TreeNode root) {
        if (root == null)
            return;
        System.out.println(root.data);
        MLRTree(root.left);
        MLRTree(root.right);
    }

    /* 先序遍历非递归 - 和层次遍历一样，就是用的是栈 */
    public static void MLRTreeStack(TreeNode root) {
        if (root == null)
            return;

        TreeNode current;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        while (!stack.empty()) {
            current = stack.pop();
            System.out.println(current.data);
            if (current.right != null) // 先 right, 栈的关系
                stack.push(current.right);
            if (current.left != null)
                stack.push(current.left);
        }
    }

    /* 中序遍历 */
    public static void LMRTree(TreeNode root) {
        if (root == null)
            return;
        LMRTree(root.left);
        System.out.println(root.data);
        LMRTree(root.right);
    }

    /* 中序遍历非递归 */
    public static void LMRTreeStack(TreeNode root) {

    }

    /* 后序遍历 */
    public static void LRMTree(TreeNode root) {
        if (root == null)
            return;
        LRMTree(root.left);
        LRMTree(root.right);
        System.out.println(root.data);
    }

    /* 后序遍历非递归 */
    public static void LRMTreeStack(TreeNode root) {

    }
}

