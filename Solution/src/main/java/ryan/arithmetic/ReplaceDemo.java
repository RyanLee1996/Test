package ryan.arithmetic;
public class ReplaceDemo {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(new ReplaceDemo().replaceSpace(new StringBuffer("We Are Happy")));
        System.out.println(System.currentTimeMillis() - start);
    }
    public String replaceSpace(StringBuffer str) {
        int spacingNum = 0;
        int indexOld = str.length()-1;
        for(int i=0;i<=indexOld;i++){
            if(str.charAt(i)==' ')
                spacingNum++;
        }
        int length = str.length() + spacingNum*2;
        int indexNew = length-1;
        str.setLength(length);
        while(indexOld>=0 && indexOld<length){
            if(str.charAt(indexOld) == ' '){
                str.setCharAt(indexNew--, '0');
                str.setCharAt(indexNew--, '2');
                str.setCharAt(indexNew--, '%');
            }else{
                str.setCharAt(indexNew--, str.charAt(indexOld));
            }
            indexOld--;
        }
        return str.toString();
    }
}
