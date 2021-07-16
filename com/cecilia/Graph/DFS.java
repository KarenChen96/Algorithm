import java.util.LinkedList;
import java.util.List;

class DFS {
    /**
     * Print all subsets of a set S = {a, b, c}
     * 
     * @param set: Assume there is no duplicate characters in the original set
     */
    public List<String> findSubset(String set) {
        // i-th level represents if put i-th element in current result candidate
        // two states for each node in each level: put or not
        // Time complexity: O(2^N*N). StringBuilder operations take O(N).
        // Space complexity: O(N)
        List<String> result = new LinkedList<>();
        findSubsetHlper(set.toCharArray(), 0, new StringBuilder(), result);
        return result;
    }

    private void findSubsetHlper(char[] set, int index, StringBuilder sb, List<String> result) {
        if (index >= set.length) {
            result.add(sb.toString());
            return;
        }
        // Put this element into the subset
        sb.append(set[index]); // eat
        findSubsetHlper(set, index + 1, sb, result);
        // Restore modification to the candidate
        sb.deleteCharAt(sb.length() - 1); // spit
        // Not put this element into the subset
        findSubsetHlper(set, index + 1, sb, result);
    }

    public List<String> findValidParentheses(int n) {
        // Each level represents what to be put in i-th position, n*2 levels.
        // At most 2 states for each node
        // Time complexity: O(2^(2*N) * N), but should be btter if consider pruning
        // because of parentheses validity.
        List<String> result = new LinkedList<>();
        findValidParentheseshelper(n, n, 0, result, new StringBuilder());
        return result;
    }

    // Hint: for permutation problem, using char[] is better than StringBuilder
    // regarding time complexity.
    // Only for permutation problem because the results have the same length.
    private void findValidParentheseshelper(int left, int right, int index, List<String> result, StringBuilder sb) {
        if (right > 0) {
            result.add(sb.toString());
            return;
        }

        if (left > 0) {
            sb.append("(");
            findValidParentheseshelper(left - 1, right, index + 1, result, sb);
            sb.deleteCharAt(sb.length() - 1); // can we use index here?
        }

        if (right > left) {
            sb.append(")");
            findValidParentheseshelper(left, right - 1, index + 1, result, sb);
            sb.deleteCharAt(sb.length() - 1);
        }

    }
}