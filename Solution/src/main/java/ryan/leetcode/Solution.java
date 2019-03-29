package ryan.leetcode;

import java.util.*;

public class Solution {
    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     *你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     *示例:
     *给定 nums = [2, 7, 11, 15], target = 9
     *因为 nums[0] + nums[1] = 2 + 7 = 9
     *所以返回 [0, 1]
     */
    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ( nums[i] + nums[j] == target){
                    return new int[] {i,j};
                }
            }
        }
        return null;
    }
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer,Integer> dataMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int remainder = target - nums[i];
            if (dataMap.containsKey(remainder)){
                return new int[] {dataMap.get(remainder),i};
            }
            dataMap.put(nums[i],i);
        }
        return null;
    }
    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;
        @Override
        public String toString() {
            StringBuilder s = new StringBuilder(String.valueOf(val));
            ListNode temp = next;
            while(temp != null){
                s.append(",").append(temp.val);
                temp = temp.next;
            }
            return s.toString();
        }
        public void addLast(ListNode node){
            ListNode temp = this;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = node;
        }
        ListNode(int x) {
            val = x;
        }
    }
    /**
     *给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     示例：
     输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     输出：7 -> 0 -> 8
     原因：342 + 465 = 807
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        return addTwoNumbers(l1,l2,0);
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2,int up) {
        if ( (l1 == null || l2== null) && up == 0){
            return l1 == null ? l2 : l1;
        }
        int val = (l1 == null ? 0 :l1.val) + (l2 == null ? 0 :l2.val) + up;
        int flag = 0;
        if (val > 9) {
            val -= 10;
            flag = 1;
        }
        ListNode result = new ListNode(val);
        result.next = addTwoNumbers(l1 == null ? l1 : l1.next, l2 == null ? l2 : l2.next,flag);
        return result;
    }
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode current = result;
        int up = 0;
        while( !(l1 == null || l2== null) || up != 0){
            int val = (l1 == null ? 0 :l1.val) + (l2 == null ? 0 :l2.val) + up;
            up = val > 9 ? 1 : 0;
            current.next = new ListNode(val % 10);
            current = current.next;
            if (l1 != null){
                l1 =l1.next;
            }
            if (l2 != null){
                l2 =l2.next;
            }
        }
        current.next =  l1 == null ? l2 : l1;
        return result.next;
    }
/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 示例 1:
 输入: "abcabcbb"
 输出: 3
 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 示例 2:
 输入: "bbbbb"
 输出: 1
 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 示例 3:
 输入: "pwwkew"
 输出: 3
 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
    public int lengthOfLongestSubstring(String s) {
        Map<Character,Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        int start = 0;
        int length = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (map.containsKey(c)){
                if (map.get(c) >= start){
                    int temp = i - start;
                    start = map.get(c) +1;
                    length = Math.max(temp,length);
                }
            }
            map.put(c,i);
            if (i == chars.length - 1 && map.size() > 0){
                int temp = i - start +1 ;
                length = Math.max(temp,length);
            }
        }
        return length;
    }
    public int lengthOfLongestSubstring2(String s) {
        Map<Character,Integer> map = new HashMap<>();
        int start = 0;
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)){
                start = Math.max(map.get(c),start);
            }
            length =  Math.max(i - start +1,length);
            map.put(c,i + 1);
        }
        return length;
    }

    /**
     *
     给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。

     请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

     你可以假设 nums1 和 nums2 不会同时为空。

     示例 1:

     nums1 = [1, 3]
     nums2 = [2]

     则中位数是 2.0
     示例 2:

     nums1 = [1, 2]
     nums2 = [3, 4]

     则中位数是 (2 + 3)/2 = 2.5
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int median = (m + n + 1)/2;
        if (m > n) { // to ensure m<=n
            int[] temp = A;
            A = B;
            B = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        int iMin = 0;
        int iMax = m;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = median - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = i + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = i - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n < 1) return head;
        ListNode temp = new ListNode(0);
        temp.next = head;
        ListNode left = temp;
        ListNode right = temp;
        while (n >= 0 && right != null) {
            right = right.next;
            n--;
        }
        while(right != null){
            right = right.next;
            left = left.next;
        }
        if (n < 0){
            left.next = left.next.next;
        }
        return temp.next;
    }
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (c=='(' || c=='[' || c=='{') {
                stack.push(c);
            } else if (stack.empty()
                    || (c==')' && stack.pop()!='(')
                    || (c==']' && stack.pop()!='[')
                    || (c=='}' && stack.pop()!='{')) {
                return false;
            }
        }
        return stack.empty();
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null){
            return l1 == null ? l2 : l1;
        }
        ListNode small = l1;
        ListNode large = l2;
        if (l1.val > l2.val){
            small = l2;
            large = l1;
        }
        ListNode res = small;
        while(small.next != null && small.next.val <= large.val){
            small = small.next;
        }
        small.next = mergeTwoLists(small.next,large);
        return res;
    }
    public List<String> generateParenthesis(int n) {
        Set<String> res = new HashSet<>();
        res.add("");
        String cur;
        Set<String> set;
        Iterator<String> iterator;
        for (int i = 0;i < n;i++){
            iterator = res.iterator();
            set = new HashSet<>();
            while (iterator.hasNext()){
                cur = iterator.next();
                set.add(cur+"()");
                set.add("()"+cur);
                set.add("("+cur+")");
            }
            res = set;
        }
        return new ArrayList<>(res);
    }
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return dac(lists,0,lists.length - 1);
    }
    private ListNode dac(ListNode[] lists,int startIndex,int endIndex) {
        int len = endIndex - startIndex + 1;
        if (len == 1){
            return lists[startIndex];
        }
        ListNode node1, node2;
        if (len == 2){
            node1 = lists[startIndex];
            node2 = lists[endIndex];
        }else {
            node1 = dac(lists,startIndex,startIndex + len/2 - 1);
            node2 = dac(lists,startIndex + len/2,endIndex);
        }
        ListNode res = new ListNode(0);
        ListNode cur = res;
        while (node1 != null && node2 != null) {
            if (node1.val < node2.val) {
                cur.next = node1;
                cur = cur.next;
                node1 = node1.next;
            } else {
                cur.next = node2;
                cur = cur.next;
                node2 = node2.next;
            }
        }
        cur.next = node1 == null ? node2 : node1;
        return res.next;
    }
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode pre = new ListNode(0);
        ListNode res = pre;
        ListNode mid = head;
        pre.next = mid;
        ListNode last = head.next;
        while (mid != null && last != null){
            mid.next = last.next;
            last.next = mid;
            pre.next = last;
            pre = mid;
            mid = mid.next;
            if (mid != null){
                last = mid.next;
            }
        }
        return res.next;
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k < 2) return head;/*无需翻转*/
        if(head == null) return null;/*非空判断*/
        int len = 0;
        ListNode cur = head;
        while (cur != null){/*计算长度*/
            cur = cur.next;
            len++;
        }
        if (k > len) return head;/*无需翻转*/
        ListNode res = new ListNode(0);/*记录翻转结果*/
        res.next = head;
        /*前置指针、移动指针、后置指针*/
        ListNode pre = res;
        cur = head;
        ListNode next = head.next;
        for (int i  = 0; i < len/k; i++){
            for (int step = 1; step < k; step++){/*记录步长*/
                cur.next = next.next;
                next.next = pre.next;
                pre.next = next;
                next = cur.next;
            }
            pre = cur;
            cur = next;
            if (next != null){
                next = next.next;
            }
        }
        return res.next;
    }
    public int removeDuplicates(int[] nums) {
        if (nums == null) return 0;
        if (nums.length < 2) return nums.length;
        int dcr = nums[0];
        int hand = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != dcr){
                nums[hand] = nums[i];
                dcr = nums[i];
                hand++;
            }
        }
        return hand;
    }
    public int removeElement(int[] nums, int val) {
        if (nums == null) return 0;
        int hand = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val){
                nums[hand] = nums[i];
                hand++;
            }
        }
        return hand;
    }
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return 0;
        if (haystack == null || needle.length() > haystack.length()) return -1;
        int[] next = next(needle);
        int i = 0, j = 0;
        while(i < haystack.length()){
            if (haystack.charAt(i) == needle.charAt(j)){
                i++;
                j++;
                if (j == needle.length()){
                    return i - j;
                }
            }else if (j == 0){
                i++;
            }else {
                j = next[j - 1] + 1;
            }
        }
        return -1;
    }
    private int[] next(String needle){
        int[] res = new int[needle.length()];
        res[0] = -1;
        int index = -1;
        for (int i = 1; i < needle.length(); i++) {
            while (index > -1 && needle.charAt(index + 1) != needle.charAt(i)){
                index = res[index];
            }
            if (needle.charAt(index + 1) == needle.charAt(i)){
                index++;
            }
            res[i] = index;
        }
        return res;
    }
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        if (divisor == 1) return dividend;
        if (divisor == dividend) return 1;
        if (divisor == Integer.MIN_VALUE) return 0;
        boolean flag = divisor < 0 ^ dividend < 0;
        divisor = divisor < 0 ? -divisor : divisor;
        long remainder = dividend;
        remainder = remainder < 0 ? -remainder : remainder;
        if (divisor > remainder) return 0;
        int res = 0;
        long realDivisor, quotient;
        while (remainder >= divisor){
            quotient  = 1;
            realDivisor = divisor;
            while (realDivisor <= remainder){
                quotient <<= 1;
                realDivisor <<= 1;
            }

            remainder -= realDivisor >> 1;
            res += quotient >> 1;
        }
        return flag ? -res : res;
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.divide(Integer.MIN_VALUE,-2));
        Arrays. asList ("1","2","3"). forEach (x -> System. out. println (x));
//        System.out.println(solution.strStr("bacbababadbbabacambabacaddababacasdsd","ababaca"));
//        System.out.println(solution.removeElement(new int[]{0,1,2,2,3,0,4,2},2));
//        ListNode node = solution.new ListNode(1);
//        node.addLast(solution.new ListNode(2));
//        node.addLast(solution.new ListNode(3));
//        node.addLast(solution.new ListNode(4));
//        node.addLast(solution.new ListNode(5));

//        ListNode node1 = solution.new ListNode(5);
//        node1.next = solution.new ListNode(5);
//        System.out.println(solution.mergeTwoLists(node,node1));
//        System.out.println(new Solution().removeNthFromEnd(node,1));
//        System.out.println(solution.isValid(")"));
//        System.out.println(solution.generateParenthesis(4));
//        System.out.println(solution.reverseKGroup(node,2));
    }
}
