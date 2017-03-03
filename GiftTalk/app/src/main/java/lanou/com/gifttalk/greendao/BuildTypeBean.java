package lanou.com.gifttalk.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dllo on 17/3/3.
 */
@Entity
public class BuildTypeBean {
    @Id
    private Long id;
    private int BUILD_TYPE;
    private String name;
    private String imageUrl;
    @Generated(hash = 1940469440)
    public BuildTypeBean(Long id, int BUILD_TYPE, String name, String imageUrl) {
        this.id = id;
        this.BUILD_TYPE = BUILD_TYPE;
        this.name = name;
        this.imageUrl = imageUrl;
    }
    @Generated(hash = 1988815022)
    public BuildTypeBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getBUILD_TYPE() {
        return this.BUILD_TYPE;
    }
    public void setBUILD_TYPE(int BUILD_TYPE) {
        this.BUILD_TYPE = BUILD_TYPE;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImageUrl() {
        return this.imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
