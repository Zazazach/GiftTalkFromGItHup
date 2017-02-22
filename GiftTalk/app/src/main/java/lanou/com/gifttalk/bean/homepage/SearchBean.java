package lanou.com.gifttalk.bean.homepage;

import java.util.List;

/**
 * Created by dllo on 17/2/20.
 */

public class SearchBean {
    /**
     * code : 200
     * data : {"hot_words":["情侣","","项链","生日","手机壳","钱包","双肩包","手表"],"placeholder":null}
     * message : OK
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * hot_words : ["情侣","","项链","生日","手机壳","钱包","双肩包","手表"]
         * placeholder : null
         */

        private Object placeholder;
        private List<String> hot_words;

        public Object getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(Object placeholder) {
            this.placeholder = placeholder;
        }

        public List<String> getHot_words() {
            return hot_words;
        }

        public void setHot_words(List<String> hot_words) {
            this.hot_words = hot_words;
        }
    }
}
