package ryan.arithmetic;
public class Spiral {
    private static int number = 1;
    private static int turns = 1;
    private static int innerTurns = 0;
    public static void main(String[] args) {
        int init = 8;
        int[][] ints = new int[init+2][init+2];
        ints = getSpiralArray(ints,init);
        System.out.println("When: init = " + init + ":");
        for (int i = 0; i < init+2; i++) {
            for (int j = 0; j < init+2; j++) {
                if(!((i == 0 || i == init+1) && j >= init)){
                    System.out.print(ints[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println(ints.length);
    }
    private static int[][] getSpiralArray(int[][] ints,int i){
        while(number<=i*4){
            if(number<=i)
                ints[0][number-1] = number;
            else if(number<=2*i)
                ints[number-i][i+1] = number;
            else if(number<=3*i)
                ints[i+1][3*i-number] = number;
            else
                ints[4*i+1-number][0] = number;
            number++;
        }
        int sideLength = i - turns - innerTurns;
        int startNum = number - 1;
        while (number <= startNum+sideLength*4 ){
            for (int j = 0;j<sideLength;j++){
                if (number <= startNum + sideLength){
                    ints[turns][turns+j] = number;
                }
                else if (number <= startNum + sideLength*2){
                    ints[turns+j][i-turns+1] = number;
                }
                else if (number <= startNum + sideLength*3){
                    ints[i-turns+1][i-turns+1-j] = number;
                }
                else
                    ints[i-turns+1-j][turns] = number;
                number++;
            }
        }
        turns++;
        innerTurns++;
        if(turns <= Math.ceil(i/2)){
            ints = getSpiralArray(ints,i);
        }
        if(i%2 == 1){
            ints[i/2 + 1][i/2 + 1] = i*4 + i*i;
        }
        return ints;
    }
}
