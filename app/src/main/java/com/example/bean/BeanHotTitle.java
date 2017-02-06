package com.example.bean;

import java.util.List;

/**
 * Created by PC on 2017/1/16.
 */

public class BeanHotTitle {

    /**
     * code : 200
     * data : [{"ctime":"1466753392","hottopic":"0","ishot":"1","isrecommend":"1","name":"星座","recommend_sort":"2","sort":"2","status":"1","tid":"77"},{"ctime":"1467117856","hottopic":"0","ishot":"1","isrecommend":"1","name":"健身","recommend_sort":"1","sort":"1","status":"1","tid":"98"},{"ctime":"1467201978","hottopic":"0","ishot":"1","isrecommend":"1","name":"穿搭","recommend_sort":"1","sort":"1","status":"1","tid":"110"},{"ctime":"1467203112","hottopic":"0","ishot":"1","isrecommend":"1","name":"吐槽","recommend_sort":"1","sort":"1","status":"1","tid":"111"},{"ctime":"1466753300","hottopic":"0","ishot":"1","isrecommend":"1","name":"美妆","recommend_sort":"1","sort":"1","status":"1","tid":"76"},{"ctime":"1466753543","hottopic":"0","ishot":"1","isrecommend":"1","name":"冷知识","recommend_sort":"0","sort":"0","status":"1","tid":"86"},{"ctime":"1466753448","hottopic":"0","ishot":"1","isrecommend":"1","name":"手作","recommend_sort":"0","sort":"0","status":"1","tid":"79"},{"ctime":"1467354890","hottopic":"0","ishot":"0","isrecommend":"1","name":"音乐","recommend_sort":"0","sort":"0","status":"1","tid":"68"},{"ctime":"1466753589","hottopic":"0","ishot":"1","isrecommend":"1","name":"摄影","recommend_sort":"0","sort":"0","status":"1","tid":"89"},{"ctime":"1466753463","hottopic":"0","ishot":"1","isrecommend":"1","name":"烘焙","recommend_sort":"0","sort":"0","status":"1","tid":"80"}]
     * msg : 请求成功
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * ctime : 1466753392
         * hottopic : 0
         * ishot : 1
         * isrecommend : 1
         * name : 星座
         * recommend_sort : 2
         * sort : 2
         * status : 1
         * tid : 77
         */

        private String ctime;
        private String hottopic;
        private String ishot;
        private String isrecommend;
        private String name;
        private String recommend_sort;
        private String sort;
        private String status;
        private String tid;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getHottopic() {
            return hottopic;
        }

        public void setHottopic(String hottopic) {
            this.hottopic = hottopic;
        }

        public String getIshot() {
            return ishot;
        }

        public void setIshot(String ishot) {
            this.ishot = ishot;
        }

        public String getIsrecommend() {
            return isrecommend;
        }

        public void setIsrecommend(String isrecommend) {
            this.isrecommend = isrecommend;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRecommend_sort() {
            return recommend_sort;
        }

        public void setRecommend_sort(String recommend_sort) {
            this.recommend_sort = recommend_sort;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }
    }
}
