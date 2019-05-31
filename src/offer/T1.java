package offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @date 2019/5/31 17:39
 */
public class T1 {
    /**
     * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
     * 每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *
     * @param target 目标值
     * @param array  输入数组
     * @return 是否存在
     */
    public boolean Find(int target, int[][] array) {
        int rows = array.length;
        int cols = array[0].length;

        if (cols > 0) {
            int row = 0;
            int col = cols - 1;
            while (row < rows && col >= 0) {
                if (array[row][col] == target) {
                    return true;
                } else if (array[row][col] > target) {
                    col--;
                } else {
                    row++;
                }
            }
        }
        return false;
    }

    /**
     * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
     * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     *
     * @param str 输入字符串
     * @return 转换后字符串
     */
    public String replaceSpace(StringBuffer str) {
        if (str == null) {
            return "";
        }
        String s = str.toString();
        return s.replace(" ", "%20");
    }


    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
     *
     * @param listNode 输入链表
     * @return 同序列表
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> resultList = new ArrayList<>();
        ListNode t = listNode;

        while (t != null) {
            stack.push(t.val);
            t = t.next;
        }

        while (!stack.isEmpty()) {
            resultList.add(stack.pop());
        }

        return resultList;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {

        if (pre.length == 0 || in.length == 0) {
            return null;
        }
        TreeNode node = new TreeNode(pre[0]);
        for (int i = 0; i < in.length; i++) {
            if (pre[0] == in[i]) {
                node.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
                node.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(in, i + 1, in.length));
            }
        }
        return node;
    }
}
