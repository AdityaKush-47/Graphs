public class Solution {
    String removeDuplicates(String str) {

        boolean visited[] = new boolean[256];
        String str1 = new String();

        for (int i = 0; i < str.length(); i++) {
            if (visited[str.charAt(i)]) {
                continue;
            }

            else {
                visited[str.charAt(i)] = true;
                str1 += str.charAt(i);
            }
        }
        return str1;
    }
}
