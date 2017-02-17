package lanou.com.gifttalk.parser;

/**
 * Created by dllo on 17/2/13.
 */

public interface ParseMethod <T> {
    void onSucceed(T something);
}
