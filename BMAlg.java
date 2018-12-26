/**
 *初级版本，没有复杂的优化
 *这里运用了好后缀和坏后缀两个规则，也可以只用好后缀规则
 *时间复杂度O(n),
**/
public class BMAlg {
    private static final int SIZE = 256; //字符集长度，此处只运用ACSII字符

    public int bm(char[] a, int n, char[] b, int m) {
        int[] bc = new int[SIZE];

        generateBC(b, m, bc);

        int[] suffix = new int[m];

        boolean[] prefix = new boolean[m];

        generateGS(b, m, suffix, prefix);

        int i = 0;

        while (i <= n - m) {
            int j;

            for (j = m -1; j >= 0; j--) {
                if (a[i + j] != b[j]) 
                    break;
            }

            if (j < 0) {
                return i;
            }

            int x = j - bc[(int)a[i + j]];
            int y = 0;

            if (j < m-1) {
                y = moveByGS(j, m, suffix, prefix);
            }

            i = i + Math.max(x, y);
        }
        return -1;

    }

    private void generateBC(char[] b, int m, int[] bc) {
        for (int i = 0; i < SIZE; i++) {
            bc[i] = -1; //初始化
        }

        for (int i = 0; i < m; i++) {
            int ascii = (int)b[i];

            bc[ascii] = i; //越往后的赋值会覆盖前面的赋值，以保证后移至后面的坏字符
        }
    }

    private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
        //初始化
        for (int i = 0; i < m; i++) {
            suffix[i] = -1;
            prefix[i] = false;
        }

        for (int i = 0; i < m-1; i++) {
            int j = i;
            int k = 0; //公共后缀子字符串的长度

            while(j >= 0 && b[j] == b[m-1-k]) { //求公共后缀子字符串
                j--;
                k++;
                suffix[k] = j+1; //代表起始下标
            }

            if (j == -1) prefix[k] = true;
        }
    }

    private int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
        int k = m-1-j; //好后缀的长度

        //前缀串存在与好后缀相匹配的字符串时，移动的距离
        if (suffix[k] != -1)
            return j - suffix[k] + 1;

        //无匹配字符串，如果存在好后缀子串已前缀字符串相符，移动的距离
        for (int r = j+1; r < m; r++) {
            if (prefix[m-r] == true) {
                return r;
            }
        }
        //都不匹配情况下移动
        return m;
    }

    public static void main(String[] args) {
        char[] str = "ABCDASAGAGJNNGJKNAJKSNGJKANJKGNFJKANGJANGJKNAJKSGF".toCharArray();

        char[] pat = "SAGAGJNNG".toCharArray();

        BMAlg bmAlg = new BMAlg();

        System.out.println(bmAlg.bm(str, str.length, pat, pat.length));
    }
}

