package dou.gank.enity;

import java.util.List;

/**
 * Created by mac on 16/7/22.
 */
public class Category {

    /**
     * error : false
     * results : [{"_id":"579187f5421aa90d39e7091d","createdAt":"2016-07-22T10:41:57.457Z","desc":"继承自 UIVisualEffectsView 的支持动态调整模糊度的库","publishedAt":"2016-07-22T11:04:44.305Z","source":"web","type":"Android","url":"https://github.com/ML-Works/Bluuur","used":true,"who":"代码家"},{"_id":"579186d2421aa90d39e7091c","createdAt":"2016-07-22T10:37:06.397Z","desc":"Stack Overflow要做文档了，未来将自动把典型问题加入文档。（Android篇）","publishedAt":"2016-07-22T11:04:44.305Z","source":"chrome","type":"Android","url":"http://stackoverflow.com/documentation/android/topics","used":true,"who":"Dear宅学长"},{"_id":"5790b2cd421aa90df638bb94","createdAt":"2016-07-21T19:32:29.130Z","desc":"字体文件压缩神器","publishedAt":"2016-07-22T11:04:44.305Z","source":"web","type":"Android","url":"https://github.com/forJrking/FontZip","used":true,"who":null},{"_id":"57907417421aa9711828d7e3","createdAt":"2016-07-21T15:04:55.494Z","desc":"Android水波动画帮助类，一行代码实现View显示/隐藏/startActivity特效","publishedAt":"2016-07-22T11:04:44.305Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/537e2cf2e870","used":true,"who":null},{"_id":"57903cec421aa90dea11ea4b","createdAt":"2016-07-21T11:09:32.6Z","desc":" Android：学习AIDL，这一篇文章就够了(上)","publishedAt":"2016-07-22T11:04:44.305Z","source":"web","type":"Android","url":"http://blog.csdn.net/luoyanglizi/article/details/51980630","used":true,"who":"lypeer"},{"_id":"579037fb421aa90df33fe7f5","createdAt":"2016-07-21T10:48:27.749Z","desc":"国内一线互联网公司内部面试题库","publishedAt":"2016-07-22T11:04:44.305Z","source":"chrome","type":"Android","url":"https://github.com/JackyAndroid/AndroidInterview-Q-A/blob/master/README-CN.md","used":true,"who":"wuzheng"},{"_id":"578f9402421aa90de83c1bf5","createdAt":"2016-07-20T23:08:50.341Z","desc":"倒计时效果数字变化动画","publishedAt":"2016-07-20T16:09:07.721Z","source":"chrome","type":"Android","url":"https://github.com/a-voyager/ScrollNumber","used":true,"who":"代码家"},{"_id":"578f587f421aa90df638bb82","createdAt":"2016-07-20T18:54:55.133Z","desc":"属性动画ValueAnimator在自定义View中的使用","publishedAt":"2016-07-20T16:09:07.721Z","source":"web","type":"Android","url":"http://mafei.site/2016/07/17/android-valueanimator/","used":true,"who":"马飞"},{"_id":"578edfbc421aa90de83c1be4","createdAt":"2016-07-20T10:19:40.769Z","desc":" 贝塞尔曲线开发","publishedAt":"2016-07-20T17:25:17.522Z","source":"chrome","type":"Android","url":"http://blog.csdn.net/eclipsexys/article/details/51956908","used":true,"who":"AndWang"},{"_id":"578df9e5421aa90df33fe7e0","createdAt":"2016-07-19T17:59:01.920Z","desc":"你真的理解AIDL中的in，out，inout么？","publishedAt":"2016-07-22T11:04:44.305Z","source":"web","type":"Android","url":"http://blog.csdn.net/luoyanglizi/article/details/51958091","used":true,"who":"lypeer"}]
     */

    private boolean error;
    /**
     * _id : 579187f5421aa90d39e7091d
     * createdAt : 2016-07-22T10:41:57.457Z
     * desc : 继承自 UIVisualEffectsView 的支持动态调整模糊度的库
     * publishedAt : 2016-07-22T11:04:44.305Z
     * source : web
     * type : Android
     * url : https://github.com/ML-Works/Bluuur
     * used : true
     * who : 代码家
     */

    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
