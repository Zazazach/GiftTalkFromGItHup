package lanou.com.gifttalk.parser;

/**
 * Created by dllo on 17/2/13.
 */

public interface RunMethod  {
    <T> void praser(String url,Class<T> tClass,ParseMethod<T> parseMethod);
}
