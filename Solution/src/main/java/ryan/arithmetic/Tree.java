package ryan.arithmetic;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
/*前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6},则重建二叉树并返回。*/
public class Tree {
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if(pre == null || in == null){
            return null;
        }
        return getRawTree(pre,in,0,pre.length - 1,0,in.length - 1);
    }
    public TreeNode getRawTree(int[] pre,int[] in,int preFirst,int preLast,int inFirst,int inLast){
        if(preFirst>preLast){
            return null;
        }
        int index = 0;
        TreeNode headNode = new TreeNode(pre[preFirst]);
        for (int i = inFirst; i <= inLast; i++) {
            if(pre[preFirst]==in[i]){
                index = i;
            }
        }
        headNode.left = getRawTree(pre,in,preFirst+1,index+preFirst-inFirst,inFirst,index-1);
        headNode.right = getRawTree(pre,in,index+1+preFirst-inFirst,preLast,index+1,inLast);
        return headNode;
    }
}
