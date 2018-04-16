package pri.ryan.normal;

public class SingleClass{
    private static class SingleClassHolder{
        private static final SingleClass INSTANCE = new SingleClass();
    }
    private SingleClass(){}
    public SingleClass getInstance(){
        return SingleClassHolder.INSTANCE;
    }
}
