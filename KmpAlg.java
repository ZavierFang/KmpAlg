/**
 * 空间负责度O(m)
 *时间复杂度O（m+n）
**/

public class KmpAlg {

    //a、b分别代表主串和模式串，n、m代表主串长度和模式串长度
    private static int kmp(char[] a, int n, char[] b, int m) {
        if (m > n) {
            return -1;
        }
        //失效函数数组
        int[] next = getNext(b, m);

        int j = 0;

        for(int i = 0; i < n; i++) {
            //while和if的顺序不能调换
            //好前缀无 可匹配后缀子串  或者  a[i] = b[j] 则退出
            while (j > 0 && a[i] != b[j]) {
                j = next[j - 1] + 1;
            }

            if (a[i] == b[j]) {
                j++;
            }

            //找到匹配字符串,返回主串中对应的起始下标
            if (j == m) {
                return i - m + 1;
            }
        }

        return -1;
    }

    //类似于动态规划的思想
    private static int[] getNext(char[] b, int m) {
        int[] next = new int [m];

        next[0] = -1;

        int k = -1;

        for (int i = 1; i < m; i++) {
            while(k != -1 && b[k+1] != b[i]) {
                k = next[k];
            }

            //匹配则最长可匹配前缀子串 为 i
            if (b[k+1] == b[i]) {
                k++;
            }

            next[i] = k;
        }
        return next;
    }

    public static void main(String[] args) {
        char[] str = "ABCDASAGAGJNNGJKNAJKSNGJKANJKGNFJKANGJANGJKNAJKSGF".toCharArray();

        char[] pat = "SAGAGJNNG".toCharArray();

        System.out.println(KmpAlg.kmp(str, str.length, pat, pat.length));
    }
}
