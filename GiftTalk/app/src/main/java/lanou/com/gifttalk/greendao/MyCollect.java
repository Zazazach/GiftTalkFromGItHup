package lanou.com.gifttalk.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dllo on 17/3/3.
 */
@Entity
public class MyCollect {
    @Id
    Long id;
    String url;
    boolean state;
    @Generated(hash = 1328235349)
    public MyCollect(Long id, String url, boolean state) {
        this.id = id;
        this.url = url;
        this.state = state;
    }
    @Generated(hash = 1158916427)
    public MyCollect() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public boolean getState() {
        return this.state;
    }
    public void setState(boolean state) {
        this.state = state;
    }
}
