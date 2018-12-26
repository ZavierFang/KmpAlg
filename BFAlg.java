/**
 * 空间复杂度 O(0)
 * 时间发咋读O(nm)
**/
public class BFAlg { 
    private static int bruteForce(char[] a, int n, char[] b, int m) {
        if (m > n)
            return -1;

        for (int i = 0; i < n - m; i++) {
            int j = 0;
            while (j < m && a[i + j] == b[j]) {
                j++;
			}

			if (j == m) {
				return i;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		char[] str = "ABCDASAGAGJNNGJKNAJKSNGJKANJKGNFJKANGJANGJKNAJKSGF".toCharArray();

		char[] pat = "SAGAGJNNG".toCharArray();

		System.out.println(BFAlg.bruteForce(str, str.length, pat, pat.length));
	}
}
