package offer;

import org.junit.Test;

import java.util.*;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @date 2019/5/31 17:39
 */
public class T1 {
    /**
     * 1、在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
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
     * 2、请实现一个函数，将一个字符串中的每个空格替换成“%20”。
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
     * 3、输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
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


    /**
     * 4、输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
     *
     * @param pre 前序遍历
     * @param in  中序遍历
     * @return 原二叉树
     * ？？？？？
     */
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


    /**
     * 5、用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
     */
    public class StackImplementQueue {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        public void push(int node) {
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }

            stack1.push(node);

            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

        public int pop() {
            if (!stack2.isEmpty()) {
                return stack2.pop();
            }
            return -1;
        }
    }

    /**
     * 6、把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     *
     * @param array 输入的数组
     * @return 最小值
     */
    public int minNumberInRotateArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int low = 0;
        int high = array.length - 1;
        int mid = low;
        while (array[low] >= array[high]) {
            if (array[low] == array[high]) {
                for (int i = low; i < array.length; i++) {
                    if (array[low] != array[i]) {
                        low = i - 1;
                        break;
                    }
                }
                for (int i = high; i >= 0; i--) {
                    if (array[high] != array[i]) {
                        high = i + 1;
                        break;
                    }
                }
            }
            if (high - low <= 1) {
                mid = high;
                break;
            }
            mid = (low + high) / 2;
            if (array[mid] >= array[low]) {
                low = mid;
            } else if (array[mid] <= array[high]) {
                high = mid;
            }
        }
        return array[mid];
    }

    public int Fibonacci1(int n) {
        if (n == 0)
            return 0;
        else if (n == 1 || n == 2)
            return 1;
        else if (n == 3)
            return 2;
        else
            return 3 * Fibonacci1(n - 3) + 2 * Fibonacci1(n - 4);
    }

    /**
     * 7、大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
     * n<=39
     *
     * @param n 第几项
     * @return 值
     */
    public int Fibonacci2(int n) {
        if (n <= 0) {
            return 0;
        }
        int first = 1;
        int second = 1;
        while (--n > 1) {
            second = first + second;
            first = second - first;
        }
        return second;
    }


    /**
     * 8、一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
     *
     * @param target 台阶级数
     * @return 跳法种数
     */
    public int JumpFloor(int target) {
        if (target < 2) {
            return target;
        }
        int f, f1 = 1, f2 = 0;
        for (int i = 1; i <= target; ++i) {
            f = f1 + f2;
            f2 = f1;
            f1 = f;
        }
        return f1;
    }

    /**
     * 9、一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
     *
     * @param target 台阶级数
     * @return 跳法种数
     */
    public int JumpFloorII(int target) {
        if (target <= 0)
            return 0;
        if (target == 1)
            return 1;
        int f = 1;
        for (int i = 2; i <= target; i++) {
            f = 2 * f;
        }
        return f;
    }

    /**
     * 10、我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
     *
     * @param target
     * @return
     */
    public int RectCover(int target) {
        if (target <= 0) {
            return 0;
        } else if (target <= 2) {
            return target;
        } else {
            return RectCover(target - 1) + RectCover(target - 2);
        }
    }

    /**
     * 11、输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
     *
     * @return 1 的个数
     */
    @Test
    public void NumberOf1() {
        int n = Integer.MIN_VALUE;
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        System.out.println(count);

        int m = Integer.MIN_VALUE;
        System.out.println(Integer.bitCount(m));
    }

    /**
     * 12、给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
     *
     * @param base
     * @param exponent
     * @return
     */
    public double Power(double base, int exponent) {
        if (exponent == 0 && base != 0)
            return 1;
        if (exponent == 1)
            return base;
        if (base == 0 && exponent <= 0) {
            throw new RuntimeException();
        }
        if (base == 0 && exponent > 0) {
            return 0;
        }
        int n = exponent < 0 ? -exponent : exponent;

        double result = Power(base, n >> 1);
        result *= result;
        if ((n & 1) == 1)
            result *= base;
        if (exponent < 0)
            result = 1 / result;
        return result;
    }

    /**
     * 13、输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
     * 所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
     *
     * @param array 输入数组
     */
    public void reOrderArray(int[] array) {
        Queue<Integer> queue_odd = new LinkedList<>();
        Queue<Integer> queue_even = new LinkedList<>();
        for (int i : array) {
            if ((i & 1) == 0) {
                queue_even.add(i);
            } else {
                queue_odd.add(i);
            }
        }

        int count = 0;
        while (!queue_odd.isEmpty()) {
            array[count] = queue_odd.poll();
            count++;
        }
        while (!queue_even.isEmpty()) {
            array[count] = queue_even.poll();
            count++;
        }
    }


    /**
     * 14、输入一个链表，输出该链表中倒数第k个结点。
     *
     * @param head 头结点
     * @param k    倒数 k 的位置
     * @return 节点
     */
    public ListNode FindKthToTail(ListNode head, int k) {
        ListNode p, q;
        p = q = head;
        int i = 0;
        for (; p != null; i++) {
            if (i >= k)
                q = q.next;
            p = p.next;
        }
        return i < k ? null : q;
    }

    /**
     * 15、输入一个链表，反转链表后，输出新链表的表头。
     *
     * @param head
     * @return ???
     */
    public ListNode ReverseList(ListNode head) {

        if (head == null)
            return null;
        //head为当前节点，如果当前节点为空的话，那就什么也不做，直接返回null；
        ListNode pre = null;
        ListNode next = null;
        //当前节点是head，pre为当前节点的前一节点，next为当前节点的下一节点
        //需要pre和next的目的是让当前节点从pre->head->next1->next2变成pre<-head next1->next2
        //即pre让节点可以反转所指方向，但反转之后如果不用next节点保存next1节点的话，此单链表就此断开了
        //所以需要用到pre和next两个节点
        //1->2->3->4->5
        //1<-2<-3 4->5
        while (head != null) {
            //做循环，如果当前节点不为空的话，始终执行此循环，此循环的目的就是让当前节点从指向next到指向pre
            //如此就可以做到反转链表的效果
            //先用next保存head的下一个节点的信息，保证单链表不会因为失去head节点的原next节点而就此断裂
            next = head.next;
            //保存完next，就可以让head从指向next变成指向pre了，代码如下
            head.next = pre;
            //head指向pre后，就继续依次反转下一个节点
            //让pre，head，next依次向后移动一个节点，继续下一次的指针反转
            pre = head;
            head = next;
        }
        //如果head为null的时候，pre就为最后一个节点了，但是链表已经反转完毕，pre就是反转后链表的第一个节点
        //直接输出pre就是我们想要得到的反转后的链表
        return pre;
    }


    /**
     * 16、输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val <= list2.val) {
            list1.next = Merge(list1.next, list2);
            return list1;
        } else {
            list2.next = Merge(list1, list2.next);
            return list2;
        }
    }

    /**
     * 17、输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
     *
     * @param root1
     * @param root2
     * @return
     */
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        boolean result = false;
        if (root1 != null && root2 != null) {
            if (root1.val == root2.val) {
                result = doesTree1HasTree2(root1, root2);
            }

            if (!result) {
                result = HasSubtree(root1.right, root2);
            }

            if (!result) {
                result = HasSubtree(root1.left, root2);
            }
        }

        return result;
    }

    private boolean doesTree1HasTree2(TreeNode tree1, TreeNode tree2) {
        if (tree2 == null) {
            return true;
        }

        if (tree1 == null) {
            return false;
        }

        if (tree1.val != tree2.val) {
            return false;
        }

        return doesTree1HasTree2(tree1.right, tree2.right) && doesTree1HasTree2(tree1.left, tree2.left);
    }

    /**
     * 18、操作给定的二叉树，将其变换为源二叉树的镜像。
     *
     * @param root
     */
    public void Mirror(TreeNode root) {
        TreeNode tmp;
        if (root != null) {
            tmp = root.left;
            root.left = root.right;
            root.right = tmp;
            if (root.left != null)
                Mirror(root.left);
            if (root.right != null)
                Mirror(root.right);
        }
    }

    /**
     * 19、输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
     * 例如，如果输入如下4 X 4矩阵：
     * [
     * [1 2 3 4]
     * [5 6 7 8]
     * [9 10 11 12]
     * [13 14 15 16]
     * ]
     * 则依次打印出数字：1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
     * <p>
     * 采用旋转魔方的方式 一次取一行，然后旋转
     *
     * @param matrix 输入
     * @return 输出
     */
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> al = new ArrayList<>();
        int row = matrix.length;
        while (row != 0) {
            for (int i = 0; i < matrix[0].length; i++) {
                al.add(matrix[0][i]);
            }
            if (row == 1)
                break;
            matrix = turn(matrix);
            row = matrix.length;
        }
        return al;
    }

    private int[][] turn(int[][] matrix) {
        int col = matrix[0].length;
        int row = matrix.length;
        int[][] newMatrix = new int[col][row - 1];
        for (int j = col - 1; j >= 0; j--) {
            for (int i = 1; i < row; i++) {
                newMatrix[col - 1 - j][i - 1] = matrix[i][j];
            }
        }
        return newMatrix;
    }


    /**
     * 20、定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
     */
    public class MinStack {
        // 借用辅助栈存储min的大小，自定义了栈结构
        private int size;
        private int min = Integer.MAX_VALUE;
        private Stack<Integer> minStack = new Stack<Integer>();
        private Integer[] elements = new Integer[10];

        public void push(int node) {
            ensureCapacity(size + 1);
            elements[size++] = node;
            if (node <= min) {
                minStack.push(node);
                min = minStack.peek();
            } else {
                minStack.push(min);
            }
            //    System.out.println(min+"");
        }

        private void ensureCapacity(int size) {
            // TODO Auto-generated method stub
            int len = elements.length;
            if (size > len) {
                int newLen = (len * 3) / 2 + 1; //每次扩容方式
                elements = Arrays.copyOf(elements, newLen);
            }
        }

        public void pop() {
            Integer top = top();
            if (top != null) {
                elements[size - 1] = (Integer) null;
            }
            size--;
            minStack.pop();
            min = minStack.peek();
            //    System.out.println(min+"");
        }

        public int top() {
            if (!empty()) {
                if (size - 1 >= 0)
                    return elements[size - 1];
            }
            return (Integer) null;
        }

        public boolean empty() {
            return size == 0;
        }

        public int min() {
            return min;
        }
    }



}
