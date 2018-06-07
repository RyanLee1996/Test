package ryan.arithmetic;

import java.util.Stack;

public class StackImplementQueue {
    /*push存入*/
    Stack<Integer> stack1 = new Stack<Integer>();
    /*pop临时*/
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        invert(stack2,stack1);
        stack1.push(node);
    }

    public int pop() {
        invert(stack1,stack2);
        return stack2.pop();
    }
    /*将Stack1倒置到Stack2中*/
    private void invert(Stack<Integer> s1,Stack<Integer> s2){
        while(s1.size() != 0){
            s2.push(s1.pop());
        }
    }
}
