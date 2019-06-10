package offer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @date 2019/6/5 09:15
 * Modified:
 * Description:
 */
public class T3 {
    /**
     * 31、输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
     *
     * @param numbers
     * @return
     */
    public String PrintMinNumber(int[] numbers) {
        int n;
        StringBuilder s = new StringBuilder();
        ArrayList<Integer> list = new ArrayList<>();
        n = numbers.length;
        for (int i = 0; i < n; i++) {
            list.add(numbers[i]);
        }

        list.sort((o1, o2) -> (o1 + "" + o2).compareTo((o2 + "" + o1)));

        for (int value : list) {
            s.append(value);
        }
        return s.toString();
    }

    /**
     * 32、把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。
     * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
     *
     * @param index
     * @return
     */
    public int GetUglyNumber_Solution(int index) {
        if (index < 7) {
            return index;
        }

        List<Integer> list = new ArrayList<>();
        list.add(1);
        int i2 = 0, i3 = 0, i5 = 0;
        while (list.size() < index) {
            int m2 = list.get(i2) * 2;
            int m3 = list.get(i3) * 3;
            int m5 = list.get(i5) * 5;
            int min = Math.min(m2, Math.min(m3, m5));
            list.add(min);
            if (min == m2) {
                i2++;
            }
            if (min == m3) {
                i3++;
            }
            if (min == m5) {
                i5++;
            }
        }
        return list.get(list.size() - 1);
    }


    /**
     * 32、在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,
     * 并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
     *
     * @param str
     * @return
     */
    public int FirstNotRepeatingChar(String str) {
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            if (map.containsKey(str.charAt(i))) {
                int count = map.get(str.charAt(i));
                map.put(str.charAt(i), ++count);
            } else {
                map.put(str.charAt(i), 1);
            }
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (map.get(c) == 1) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 33、在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
     * 输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。
     * 即输出P%1000000007
     * <p>
     * 题目保证输入的数组中没有的相同的数字数据范围：
     * 对于%50的数据,size<=10^4
     * 对于%75的数据,size<=10^5
     * 对于%100的数据,size<=2*10^5
     *
     * @param array
     * @return
     */
    public int InversePairs(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int[] copy = new int[array.length];
        System.arraycopy(array, 0, copy, 0, array.length);
        return InversePairs(array, copy, 0, array.length - 1);
    }

    private int InversePairs(int[] array, int[] copy, int low, int high) {
        if (low == high) {
            return 0;
        }
        int mid = (low + high) >> 1;
        int leftCount = InversePairs(array, copy, low, mid) % 1000000007;
        int rightCount = InversePairs(array, copy, mid + 1, high) % 1000000007;

        int count = 0;
        int i = mid;
        int j = high;
        int locCopy = high;
        while (i >= low && j > mid) {
            if (array[i] > array[j]) {
                count += j - mid;
                copy[locCopy--] = array[i--];
                if (count > 1000000007) {
                    count %= 1000000007;
                }
            } else {
                copy[locCopy--] = array[j--];
            }
        }

        for (; i >= low; i--) {
            copy[locCopy--] = array[i];
        }

        for (; j > mid; j--) {
            copy[locCopy--] = array[j];
        }
        if (high + 1 - low >= 0) {
            System.arraycopy(copy, low, array, low, high + 1 - low);
        }
        return (leftCount + rightCount + count) % 1000000007;
    }

    /**
     * 34、输入两个链表，找出它们的第一个公共结点。
     *
     * @param pHead1
     * @param pHead2
     * @return
     */
    public T1.ListNode FindFirstCommonNode(T1.ListNode pHead1, T1.ListNode pHead2) {
        T1.ListNode p1 = pHead1;
        T1.ListNode p2 = pHead2;
        Map<T1.ListNode, Integer> map = new HashMap<>();
        while (p1 != null) {
            map.put(p1, null);
            p1 = p1.next;
        }
        while (p2 != null) {
            if (map.containsKey(p2)) {
                return p2;
            }
            p2 = p2.next;
        }
        return null;
    }


    /**
     * 35、统计一个数字在排序数组中出现的次数。
     *
     * @param array
     * @param k
     * @return
     */
    public int GetNumberOfK(int[] array, int k) {
        if (array == null || array.length == 0) {
            return 0;
        }

        int first = getFirstK(array, k, 0, array.length - 1);
        int last = getLastK(array, k, 0, array.length - 1);
        if (first == -1 || last == -1) {
            return 0;
        } else {
            return last - first + 1;
        }

    }

    private int getFirstK(int[] array, int k, int start, int end) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (k < array[mid]) {
                end = mid - 1;
            } else if (k > array[mid]) {
                start = mid + 1;

            } else {
                if ((mid > 0 && array[mid - 1] != k) || mid == 0) {
                    return mid;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }

    private int getLastK(int[] array, int k, int start, int end) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (k < array[mid]) {
                end = mid - 1;
            } else if (k > array[mid]) {
                start = mid + 1;
            } else {
                if ((mid < array.length - 1 && array[mid + 1] != k) || mid == array.length - 1) {
                    return mid;
                } else {
                    start = mid + 1;
                }
            }
        }
        return -2;
    }


    /**
     * 36、输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，
     * 最长路径的长度为树的深度。
     *
     * @param root
     * @return
     */
    public int TreeDepth(T1.TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(TreeDepth(root.left), TreeDepth(root.right)) + 1;
    }

    /**
     * 37、输入一棵二叉树，判断该二叉树是否是平衡二叉树。
     *
     * @param root
     * @return
     */
    public boolean IsBalanced_Solution(T1.TreeNode root) {
        return getDepth(root) != -1;
    }

    private int getDepth(T1.TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getDepth(root.left);
        if (left == -1) {
            return -1;
        }
        int right = getDepth(root.right);
        if (right == -1) {
            return -1;
        }
        return Math.abs(left - right) > 1 ? -1 : 1 + Math.max(left, right);
    }


    /**
     * 38、一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
     *
     * @param array
     * @param num1
     * @param num2
     */
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        if (array == null || array.length < 2) {
            return;
        }
        int temp = 0;
        for (int value : array) {
            temp ^= value;
        }
        int indexOf1 = findFirstBit(temp);
        for (int value : array) {
            if (isBit(value, indexOf1)) {
                num1[0] ^= value;
            } else {
                num2[0] ^= value;
            }
        }
    }

    private int findFirstBit(int num) {
        int indexBit = 0;
        while (((num & 1) == 0) && (indexBit) < 8 * 4) {
            num = num >> 1;
            indexBit++;
        }
        return indexBit;
    }

    private boolean isBit(int num, int indexBit) {
        num = num >> indexBit;
        return (num & 1) == 1;
    }

    /**
     * 39、小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。但是他并不满足于此,
     * 他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。没多久,他就得到另一组连续正数和为100的序列:
     * 18,19,20,21,22。现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
     *
     * @param sum
     * @return
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        int low = 1, high = 2;

        while (high > low) {
            int cur = (low + high) * (high - low + 1) / 2;
            if (cur == sum) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = low; i <= high; i++) {
                    list.add(i);
                }
                result.add(list);
                low++;
            } else if (cur < sum) {
                high++;
            } else {
                low++;
            }
        }

        return result;
    }

    /**
     * 40、输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，
     * 如果有多对数字的和等于S，输出两个数的乘积最小的。
     *
     *
     * -8 -6 -4 -2 -1 0 1 2 3 4 5 6 7 8 9
     *
     * 0
     *
     * @param array
     * @param sum
     * @return
     */
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> result = new ArrayList<>();
        if (array == null || array.length < 2) {
            return result;
        }

        int i = 0, j = array.length - 1;
        while (i < j) {
            if (array[i] + array[j] == sum) {
                result.add(array[i]);
                result.add(array[j]);
                return result;
            } else if (array[i] + array[j] > sum) {
                j--;
            } else {
                i++;
            }
        }

        return result;
    }
}
