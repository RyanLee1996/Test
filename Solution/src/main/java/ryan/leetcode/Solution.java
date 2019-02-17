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
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        if (nums.length < 3){
            return new ArrayList<>(result);
        }
        int reverse  = 0;
        Arrays.sort(nums);
        int min =  - nums[nums.length - 1] - nums[nums.length - 1];
        if (nums[0] > 0 || nums[nums.length - 1] < 0 ){
            return new ArrayList<>(result);
        }
        Map<Integer,Integer> map  = new HashMap<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            map.put(nums[i],i);
        }
        int left = 1;
        int rightIndex = nums.length - 1;
        for (int i = 0; i < nums.length - 1 && nums[i] <= 0; i++) {
            if (nums[i] < min || left == nums[i]){
                continue;
            }
            left = nums[i];
            int right = -1;
            for (int j = rightIndex; j > i +1 && nums[j] >= - left/2; j--) {
                if (nums[j] > - left*2){
                    rightIndex = j - 1;
                    continue;
                }
                if (right == nums[j]){
                    continue;
                }
                right = nums[j];
                reverse = - left - right;
                if (map.containsKey(reverse)
                        &&(i < map.get(reverse)
                            || (i == map.get(reverse) && left == nums[i + 1]))){
                    result.add(Arrays.asList(left,reverse,right));
                }
            }
        }
        return new ArrayList<>(result);
    }
    public static void main(String[] args) {
//        System.out.println(new Solution().threeSum( new int[] {-1,0,1,2,-1,-4}));
        System.out.println(new Solution().threeSum( new int[] {-10,-3,-8,-3,4,-1,-2,-4,-8,-5}));
//        System.out.println(3/2);

    }
}
