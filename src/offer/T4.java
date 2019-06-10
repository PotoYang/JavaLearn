package offer;

import java.util.LinkedList;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @date 2019/6/10 15:05
 * Modified:
 * Description:
 */
public class T4 {

    /**
     * 41、汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
     * 对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,要求输出循环左移
     * 3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
     *
     * @param str 输入的字符串
     * @param n   移位
     * @return 移位后字符串
     */
    public String LeftRotateString(String str, int n) {
//        int len = str.length();
//        if (len == 0){
//            return "";
//        }
//        n = n%len;
//        str += str;
//        return str.substring(n, len + n);
        if (str == null || str.length() == 0) {
            return "";
        }
        if (n == 0) {
            return str;
        }
        String suffix = str.substring(0, n);
        String prefix = str.substring(n);
        return prefix + suffix;
    }


    /**
     * 42、牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。
     * 同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。例如，“student. a am I”。
     * 后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。
     * Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
     *
     * @param str
     * @return
     */
    public String ReverseSentence(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }

        StringBuilder res = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                res.insert(0, " " + temp);
                temp = new StringBuilder();
            } else {
                temp.append(str.charAt(i));
            }
        }
        if (temp.length() != 0) {
            res = temp.append(res);
        }

        return res.toString();
    }


    /**
     * 43、LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...
     * 他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！
     * “红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....LL不高兴了,他想了想,决定大\小 王可以看成任何数字,
     * 并且A看作1,J为11,Q为12,K为13。上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。
     * LL决定去买体育彩票啦。
     * 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何， 如果牌能组成顺子就输出true，否则就输出false。
     * 为了方便起见,你可以认为大小王是0。
     *
     * @param numbers
     * @return
     */
    public boolean isContinuous(int[] numbers) {
        if (numbers.length != 5) {
            return false;
        }
        int min = 14;
        int max = -1;
        int flag = 0;
        for (int number : numbers) {
            if (number < 0 || number > 13) {
                return false;
            }
            if (number == 0) {
                continue;
            }
            if (((flag >> number) & 1) == 1) {
                return false;
            }
            flag |= (1 << number);
            if (number > max) {
                max = number;
            }
            if (number < min) {
                min = number;
            }
            if (max - min >= 5) {
                return false;
            }
        }

        return true;
    }

    /**
     * 44、每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。HF作为牛客的资深元老,
     * 自然也准备了一些小游戏。其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,
     * 让编号为0的小朋友开始报数。每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,
     * 并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....这样下去....直到剩下最后一个小朋友,
     * 可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。
     * 请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
     *
     * @param n
     * @param m
     * @return
     */
    public int LastRemaining_Solution(int n, int m) {
//        if (n < 1 || m < 1) {
//            return -1;
//        }
//        int[] array = new int[n];
//        int i = -1, step = 0, count = n;
//        while (count > 0) {
//            i++;
//            if (i >= n) {
//                i = 0;
//            }
//            if (array[i] == -1) {
//                continue;
//            }
//            step++;
//            if (step == m) {
//                array[i] = -1;
//                step = 0;
//                count--;
//            }
//        }
//
//        return i;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int bt = 0;
        while (list.size() > 1) {
            bt = (bt + m - 1) % list.size();
            list.remove(bt);
        }

        return list.size() == 1 ? list.get(0) : -1;
    }


    /**
     * 45、求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case
     * 等关键字及条件判断语句（A?B:C）。
     *
     * @param n
     * @return
     */
    public int Sum_Solution(int n) {
        int sum = n;
        boolean ans = (n > 0) && (sum += Sum_Solution(n - 1)) > 0;
        return sum;
    }

    /**
     * 46、写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
     *
     * @param num1
     * @param num2
     * @return
     */
    public int Add(int num1, int num2) {
        while (num2 != 0) {
            int temp = num1 ^ num2;
            num2 = (num1 & num2) << 1;
            num1 = temp;
        }

        return num1;
    }

    /**
     * 47、将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，但是string不符合数字要求时返回0)，
     * 要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0。
     *
     * @param str
     * @return
     */
    public int StrToInt(String str) {
        int n = str.length(), s = 1;
        long res = 0;
        if (n == 0) {
            return 0;
        }
        if (str.charAt(0) == '-') {
            s = -1;
        }
        for (int i = (str.charAt(0) == '-' || str.charAt(0) == '+') ? 1 : 0; i < n; i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                return 0;
            }
            res = (res << 1) + (res << 3) + (str.charAt(i) & 0xf);
        }

        return (int) (res * s);
    }

    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false

    /**
     * 48、在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。
     * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，
     * 那么对应的输出是第一个重复的数字2。
     *
     * @param numbers
     * @param length
     * @param duplication
     * @return
     */
    public boolean duplicate(int numbers[], int length, int[] duplication) {
//        if (numbers == null || numbers.length == 0){
//            duplication[0] = -1;
//            return false;
//        }
//        List<Integer> list = new ArrayList<>();
//        for(int number : numbers) {
//            if (number < 0 || number >= length) {
//                duplication[0] = -1;
//                return false;
//            }
//            if (list.contains(number)) {
//                duplication[0] = number;
//                return true;
//            }
//            list.add(number);
//        }
//        return false;
        for (int i = 0; i < numbers.length; i++) {
            int index = numbers[i];
            if (index >= length) {
                index -= length;
            }
            if (numbers[index] >= length) {
                duplication[0] = index;
                return true;
            }

            numbers[index] = numbers[index] + length;
        }
        duplication[0] = -1;
        return false;
    }


    /**
     * 49、给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
     * 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
     *
     * @param A
     * @return
     */
    public int[] multiply(int[] A) {
        int length = A.length;
        int[] B = new int[length];
        if (length != 0) {
            B[0] = 1;
            for (int i = 1; i < length; i++) {
                B[i] = B[i - 1] * A[i - 1];
            }

            int temp = 1;
            for (int j = length - 2; j >= 0; j--) {
                temp *= A[j + 1];
                B[j] *= temp;
            }
        }
        return B;
    }

    /**
     * 50、请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的
     * 字符可以出现任意次（包含0次）。 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与
     * 模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
     *
     * @param str
     * @param pattern
     * @return
     */
    public boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) {
            return false;
        }

        int strIndex = 0, patternIndex = 0;
        return matchCore(str, strIndex, pattern, patternIndex);
    }

    private boolean matchCore(char[] str, int strIndex, char[] pattern, int patterIndex) {
        if (strIndex == str.length && patterIndex == pattern.length) {
            return true;
        }
        if (strIndex != str.length && patterIndex == pattern.length) {
            return false;
        }
        if (patterIndex + 1 < pattern.length && pattern[patterIndex + 1] == '*') {
            if ((strIndex != str.length) && pattern[patterIndex] == str[strIndex] || (pattern[patterIndex] == '.' && strIndex != str.length)) {
                return matchCore(str, strIndex, pattern, patterIndex + 2)
                        || matchCore(str, strIndex + 1, pattern, patterIndex + 2)
                        || matchCore(str, strIndex + 1, pattern, patterIndex);
            } else {
                return matchCore(str, strIndex, pattern, patterIndex + 2);
            }
        }

        if ((strIndex != str.length && pattern[patterIndex] == str[strIndex]) || (pattern[patterIndex] == '.' && strIndex != str.length)) {
            return matchCore(str, strIndex + 1, pattern, patterIndex + 1);
        }
        return false;
    }
}
