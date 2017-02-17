package lanou.com.gifttalk.parser;

/**
 * Created by dllo on 17/2/13.
 */
public class ParserTool implements RunMethod{
    private static ParserTool ourInstance ;

    private RunMethod runMethod;
    public static ParserTool getInstance() {
        if (ourInstance==null){
            synchronized (ParserTool.class){
                if (ourInstance==null){

                    ourInstance=new ParserTool();
                }
            }

        }


        return ourInstance;
    }

    private ParserTool() {
        runMethod=new Base();
    }


    @Override
    public <T> void praser(String url, Class<T> tClass, ParseMethod<T> parseMethod) {
        runMethod.praser(url,tClass,parseMethod);
    }
}
