package offer;

import java.util.*;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @date 2019/6/4 10:15
 * Modified:
 * Description:
 */
public class T2 {

    /**
     * 21、输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
     * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
     * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
     *
     * @param pushA
     * @param popA
     * @return
     */
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        if (pushA.length == 0 || popA.length == 0) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();

        int popIndex = 0;

        for (int a : pushA) {
            stack.push(a);
            while (!stack.empty() && stack.peek() == popA[popIndex]) {
                stack.pop();
                popIndex++;
            }
        }

        return stack.empty();
    }


    /**
     * 22、从上往下打印出二叉树的每个节点，同层节点从左至右打印。
     *
     * @param root
     * @return
     */
    public ArrayList<Integer> PrintFromTopToBottom(T1.TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<T1.TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            T1.TreeNode treeNode = queue.poll();
            if (treeNode.left != null) {
                queue.offer(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.offer(treeNode.right);
            }

            result.add(treeNode.val);
        }

        return result;
    }

    /**
     * 23、输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。
     * 假设输入的数组的任意两个数字都互不相同。
     *
     * @param sequence 输入序列
     * @return 是否为结果
     */
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence.length == 0) {
            return false;
        }
        if (sequence.length == 1) {
            return true;
        }

        return judge(sequence, 0, sequence.length - 1);
    }

    private boolean judge(int[] a, int start, int end) {
        if (start >= end) {
            return true;
        }
        int i = start;
        while (a[i] < a[end]) {
            i++;
        }
        for (int j = i; j < end; j++) {
            if (a[j] < a[end]) {
                return false;
            }
        }

        return judge(a, start, i - 1) && judge(a, i, end - 1);
    }

    /**
     * 24、输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
     * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
     * (注意: 在返回值的list中，数组长度大的数组靠前)
     *
     * @param root
     * @param target
     * @return
     */
    public ArrayList<ArrayList<Integer>> FindPath(T1.TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> paths = new ArrayList<>();

        if (root == null) {
            return paths;
        }

        find(paths, new ArrayList<Integer>(), root, target);

        return paths;
    }

    private void find(ArrayList<ArrayList<Integer>> paths, ArrayList<Integer> path, T1.TreeNode root, int target) {
        path.add(root.val);
        if (root.left == null && root.right == null) {
            if (target == root.val) {
                paths.add(path);
            }
            return;
        }

        ArrayList<Integer> path2 = new ArrayList<>(path);

        if (root.left != null) {
            find(paths, path, root.left, target - root.val);
        }
        if (root.right != null) {
            find(paths, path2, root.right, target - root.val);
        }
    }

    public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    /**
     * 24、输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），
     * 返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
     *
     * @param pHead
     * @return
     */
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }

        RandomListNode currNode = pHead;

        while (currNode != null) {
            RandomListNode node = new RandomListNode(currNode.label);
            node.next = currNode.next;
            currNode.next = node;
            currNode = node.next;
        }

        currNode = pHead;

        while (currNode != null) {
            RandomListNode node = currNode.next;
            if (currNode.random != null) {
                node.random = currNode.random.next;
            }
            currNode = node.next;
        }

        RandomListNode pCloneHead = pHead.next;
        RandomListNode tmp;
        currNode = pHead;
        while (currNode.next != null) {
            tmp = currNode.next;
            currNode.next = tmp.next;
            currNode = tmp;
        }

        return pCloneHead;
    }

    /**
     * 25、输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
     * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
     * 中序遍历
     *
     * @param pRootOfTree
     * @return
     */
    public T1.TreeNode Convert(T1.TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }

        Stack<T1.TreeNode> stack = new Stack<>();

        T1.TreeNode p = pRootOfTree;
        T1.TreeNode pre = null;

        boolean isFirst = true;

        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }

            p = stack.pop();

            if (isFirst) {
                pRootOfTree = p;
                pre = pRootOfTree;
                isFirst = false;
            } else {
                pre.right = p;
                p.left = pre;
                pre = p;
            }

            p = p.right;
        }

        return pRootOfTree;
    }

    /**
     * 26、输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则打印出由字符a,b,c
     * 所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
     *
     * @param str
     * @return
     */
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str != null && str.length() > 0) {
            PermutationHelper(str.toCharArray(), 0, res);
            Collections.sort(res);
        }

        return res;
    }

    private void PermutationHelper(char[] cs, int i, ArrayList<String> list) {
        if (i == cs.length - 1) {
            String val = String.valueOf(cs);
            if (!list.contains(val)) {
                list.add(val);
            }
        } else {
            for (int j = i; j < cs.length; j++) {
                swap(cs, i, j);
                PermutationHelper(cs, i + 1, list);
                swap(cs, i, j);
            }
        }
    }

    private void swap(char[] cs, int i, int j) {
        char temp = cs[i];
        cs[i] = cs[j];
        cs[j] = temp;
    }

    /**
     * 27、数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
     * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
     *
     * @param array
     * @return
     */
    public int MoreThanHalfNum_Solution(int[] array) {
        Arrays.sort(array);
        int count = 0;

        for (int value : array) {
            if (value == array[array.length / 2]) {
                count++;
            }
        }

        if (count > array.length / 2) {
            return array[array.length / 2];
        } else {
            return 0;
        }
    }

    /**
     * 28、输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
     *
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();

        if (input == null || input.length == 0 || k == 0 || k > input.length) {
            return res;
        }

        Queue<Integer> queue = new PriorityQueue<>(k, Collections.reverseOrder());

        for (int value : input) {
            if (queue.size() < k) {
                queue.add(value);
            } else {
                if (value < queue.peek()) {
                    queue.remove();
                    queue.add(value);
                }
            }
        }

        while (!queue.isEmpty()) {
            res.add(queue.remove());
        }

        return res;
    }


    /**
     * 29、HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,他又发话了:在古老的一维模式识别中,
     * 常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。但是,如果向量中包含负数,是否应该包含
     * 某个负数,并期望旁边的正数会弥补它呢？例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第
     * 3个为止)。给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
     *
     * @param array
     * @return
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int total = array[0], maxSum = array[0];
        for (int i = 1; i < array.length; i++) {
            if (total >= 0) {
                total += array[i];
            } else {
                total = array[i];
            }
            if (total > maxSum) {
                maxSum = total;
            }
        }
        return maxSum;
    }


    /**
     * 30、求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？为此他特别数了一下1~13中包含1的数字有
     * 1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。ACMer希望你们帮帮他,并把问题更加普遍化,可以很
     * 快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
     *
     * @param n
     * @return
     */
    public int NumberOf1Between1AndN_Solution(int n) {
        if (n <= 0) {
            return 0;
        }
        int count = 0;
        for (long i = 1; i <= n; i *= 10) {
            long divider = i * 10;
            count += (n / divider) * i + Math.min(Math.max(n % divider - i + 1, 0), i);
        }
        return count;
    }
}
