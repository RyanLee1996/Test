package ryan.arithmetic;

public class MaxToArray {
    public static Integer MaxSum(int[] array){
        Integer maxSum = null;
        boolean exit = false;
        int temp = 0;
        for(int i = array.length - 1; i > 0 && !exit; i--){
            for(int j = 0; j < i  && !exit; j++){
                if(array[j] > array[i]/2){
                    break;
                }else if(array[j] == (float)array[i]/2){
                    maxSum = array[i];
                    exit = true;
                    break;
                }else {
                    temp = array[i] - array[j];
                    for(int k = i-1;k > j;k--){
                        if(temp == array[k]){
                            exit = true;
                            maxSum = array[i];
                            break;
                        }else if(temp > array[k]){
                            break;
                        }
                    }
                }
            }
        }
        return maxSum;
    }
    public static void main(String[] args) {
        int[] arrays = {1,2,5,4,6,7,8,19,21};
        System.out.println(MaxSum(arrays));
    }
}
