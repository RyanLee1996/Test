package ryan.arithmetic;
import java.util.ArrayList;
import java.util.Stack;

 class ListNode {
     int val;
     ListNode next = null;
     ListNode(int val) {
         this.val = val;
     }
 }

public class Solution {
    public boolean Find(int target, int [][] array) {
        int col = 0;
        int row = array.length - 1;
        while(row>=0 && col<array[0].length){
            if(array[row][col] == target){
                return true;
            }else if(array[row][col]>target){
                row--;
            }else{
                col++;
            }
        }
        return false;
    }
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack<Integer> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }

        ArrayList<Integer> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        return list;
    }
}
