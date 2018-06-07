package ryan.arithmetic;

public class RotateArray {
    public int minNumberInRotateArray(int [] array) {
        if(array.length == 0) {
            return 0;
        }else if(array.length <= 2){
            return array[array.length-1];
        }else {
            return 0;
        }
    }
}
