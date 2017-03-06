package lanou.com.gifttalk.greendao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import lanou.com.gifttalk.activity.extra.MyApp;

/**
 * Created by dllo on 17/3/3.
 */
public class CollectTool {
    private static CollectTool ourInstance;

    private MyCollectDao collectDao;

    public static CollectTool getInstance() {

        if (ourInstance == null) {
            synchronized (CollectTool.class) {
                if (ourInstance == null) {

                    ourInstance = new CollectTool();
                }
            }
        }
        return ourInstance;
    }

    private CollectTool() {
        collectDao = MyApp.getDaoSession().getMyCollectDao();
    }

    public void insertData(MyCollect myCollect) {

        collectDao.insert(myCollect);
    }


    public void deleteData(MyCollect myCollect) {
        if (isExist(myCollect.getUrl())) {

//            collectDao.deleteByKey();
            //根据提供的对象上数据库中 查找对应对象 再删除
            // 直接delete就不对
            QueryBuilder<MyCollect> builder = collectDao.queryBuilder().where(MyCollectDao.Properties.Url.eq(myCollect.getUrl()));
            MyCollect collect = builder.unique();
            collectDao.delete(collect);
        }
    }

    public List<MyCollect> queryData() {
        return collectDao.loadAll();
    }

    public void deleteAll() {
        //方便测试
        collectDao.deleteAll();
    }

    public boolean quertDate(MyCollect myCollectt) {
        MyCollect newCollect = collectDao.queryBuilder().where(MyCollectDao.Properties.Url.eq(myCollectt.getUrl())).unique();
        return newCollect.getState();
    }


    public boolean isExist(String url) {
        QueryBuilder<MyCollect> builder = collectDao.queryBuilder().where(MyCollectDao.Properties.Url.eq(url));

        Long count = builder.buildCount().count();
        return count > 0 ? true : false;
    }
}
