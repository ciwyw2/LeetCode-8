package search.backtracking;

/**
 * Title: 79. 单词搜索
 * Desc: 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * ！！！注意：同一个单元格内的字母不允许被重复使用。
 * 例如：
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 * 给定 word = "ABCCED", 返回 true.
 * 给定 word = "SEE", 返回 true.
 * 给定 word = "ABCB", 返回 false.
 * https://leetcode-cn.com/problems/word-search/
 * Created by Myth on 7/26/2019
 */
public class P79WordSearch {
    // 类似于迷宫问题，如何保证不重复使用(两周写法，一个是使用used数组，另一个是使用标记字符)
    private boolean backtracking(char[][] board, boolean[][] used, int i, int j, String word, int charPos) {
        if (charPos == word.length()-1) {  // 对应于测试例3
            return board[i][j] == word.charAt(charPos);
        }
        if (board[i][j] == word.charAt(charPos++)) {
            used[i][j] = true;
            // 上下左右
            if (inArea(board, i, j-1) && !used[i][j-1] && backtracking(board, used, i, j-1, word, charPos)) return true;
            if (inArea(board, i, j+1) && !used[i][j+1] && backtracking(board, used, i, j+1, word, charPos)) return true;
            if (inArea(board, i-1, j) && !used[i-1][j] && backtracking(board, used, i-1, j, word, charPos)) return true;
            if (inArea(board, i+1, j) && !used[i+1][j] && backtracking(board, used, i+1, j, word, charPos)) return true;
            used[i][j] = false; // 回溯！！！恢复到原来的状态，对应于测试例4
        }
        return false;
    }
    private boolean inArea(char[][] board, int i, int j) {
        return (i >= 0 && j >= 0 && i < board.length && j < board[i].length);
    }
    // 解法2 （简洁）
    private boolean backtrackingOld(char[][] board, int i, int j, String word, int charPos) {
        char c = board[i][j];
        if (charPos == word.length() - 1) return c == word.charAt(charPos);
        if (c == '#' || c != word.charAt(charPos)) return false;
        board[i][j] = '#';
        if (i > 0 && backtracking(board, i-1, j, word, charPos+1)) return true;
        if (j > 0 && backtracking(board, i, j-1, word, charPos+1)) return true;
        if (i < board.length - 1 && backtracking(board, i+1, j, word, charPos+1)) return true;
        if (j < board[0].length - 1 && backtracking(board, i, j+1, word, charPos+1)) return true;
        board[i][j] = c;
        return false;
    }
    // 最好的写法
    private boolean backtracking(char[][] board, int i, int j, String word, int charPos) {
        if (charPos == word.length()) return true;
        // 将判断移到此处来
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) return false;
        char c = board[i][j];
        if (c == '#' || c != word.charAt(charPos)) return false;
        board[i][j] = '#';
        if (backtracking(board, i-1, j, word, charPos+1)) return true;
        if (backtracking(board, i, j-1, word, charPos+1)) return true;
        if (backtracking(board, i+1, j, word, charPos+1)) return true;
        if (backtracking(board, i, j+1, word, charPos+1)) return true;
        board[i][j] = c;
        return false;
    }

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null || word.length() == 0) return false;
        int m = board.length, n = board[0].length;
        boolean[][] used = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (backtracking(board, used, i, j, word, 0)) {
                    System.out.println(i + "," +j);
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        P79WordSearch p79 = new P79WordSearch();
        char[][] board1 = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}};
        char[][] board2 = {{'A'}, {'A'}};
        char[][] board3 = {{'A'}};
        char[][] board4 = {
                {'C','A','A'},
                {'A','A','A'},
                {'B','C','D'}};
        System.out.println(p79.exist(board1, "SEE"));
        System.out.println(p79.exist(board2, "AAA"));
        System.out.println(p79.exist(board3, "A"));
        System.out.println(p79.exist(board4, "AAB"));
    }
}
