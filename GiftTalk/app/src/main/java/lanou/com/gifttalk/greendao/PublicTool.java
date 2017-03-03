package lanou.com.gifttalk.greendao;

import android.util.Log;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import lanou.com.gifttalk.activity.extra.*;

/**
 * Created by dllo on 17/3/3.
 */
public class PublicTool {
    private static PublicTool ourInstance;

    private BuildTypeBeanDao typeBeanDao;
    public static PublicTool getInstance() {
        if (ourInstance==null){
            synchronized (PublicTool.class){
                if (ourInstance==null){
                    ourInstance=new PublicTool();
                }
            }
        }
        return ourInstance;
    }

    private PublicTool() {
        typeBeanDao= MyApp.getDaoSession().getBuildTypeBeanDao();
    }

    public void insertData(int type){
        BuildTypeBean buildTypeBean=new BuildTypeBean();
        buildTypeBean.setBUILD_TYPE(type);
        typeBeanDao.insert(buildTypeBean);
    }


    public void insertData(BuildTypeBean bean){

        typeBeanDao.insert(bean);
    }

    public void insertPicData(String imageUrl){
        BuildTypeBean picBean=new BuildTypeBean();
        picBean.setImageUrl(imageUrl);
        typeBeanDao.insert(picBean);
    }

    public boolean isExist(int type){
        QueryBuilder<BuildTypeBean> builder=typeBeanDao.queryBuilder().where(BuildTypeBeanDao.Properties.BUILD_TYPE.eq(type));

        Long count=builder.buildCount().count();
        return count>0?true:false;
    }

    public void deleteDataZero(){
        List<BuildTypeBean> list=typeBeanDao.queryBuilder().where(BuildTypeBeanDao.Properties.BUILD_TYPE.eq(0)).build().list();

        typeBeanDao.deleteInTx(list);
    }

    public void deleteDataOne(){
        List<BuildTypeBean> list=typeBeanDao.queryBuilder().where(BuildTypeBeanDao.Properties.BUILD_TYPE.eq(1)).build().list();

        typeBeanDao.deleteInTx(list);
    }

    public String queryImageData(){
//        QueryBuilder<BuildTypeBean> builder=typeBeanDao.queryBuilder().where(BuildTypeBeanDao.Properties.ImageUrl.eq(buildTypeBean.getImageUrl()));
        List<BuildTypeBean> list=typeBeanDao.loadAll();
        String url=null;
        for (BuildTypeBean typeBean : list) {
            Log.d("PublicTool1", "list.size():image" + list.size());

            if (typeBean.getImageUrl()!=null) {
                Log.d("PublicTool11", typeBean.getImageUrl());
                 url = typeBean.getImageUrl();
            }
        }
        return url;
    }

    public String queryNameData(){
        List<BuildTypeBean> list=typeBeanDao.loadAll();
        String url=null;
        for (BuildTypeBean typeBean : list) {
            Log.d("PublicTool", "list.size():image" + list.size());

            if (typeBean.getName()!=null) {
                url = typeBean.getName();
            }
        }
        return url;
    }

}
