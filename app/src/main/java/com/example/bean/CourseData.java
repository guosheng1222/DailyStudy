package com.example.bean;

import java.util.List;

/**
 * Created by lenovo on 2017/2/4.
 */

public class CourseData {


    /**
     * datalist : [{"cid":"5773","course_tname":"辛欣","course_name":"每日演唱问答（十）","course_price":"0.00","course_pic":"http://img.dianfu.net/img/20170203/58a8af83eff8c56bc6696686d1903e00.jpg","course_paycount":"2","school_name":"歌者盟"},{"cid":"5772","course_tname":"戴笠","course_name":"每日演唱问答（九）","course_price":"0.00","course_pic":"http://img.dianfu.net/img/20170203/43c3b27deb3f5232d0bc1bf8618c38fa.jpg","course_paycount":"0","school_name":"歌者盟"},{"cid":"5771","course_tname":"常骏","course_name":"每日演唱问答（八）","course_price":"0.00","course_pic":"http://img.dianfu.net/img/20170203/83c9d72b7a4abcb6805bf51d3abbbfff.jpg","course_paycount":"0","school_name":"歌者盟"},{"cid":"5767","course_tname":"张奇聪","course_name":"每日演唱问答（七）","course_price":"0.00","course_pic":"http://img.dianfu.net/img/20170122/11e03f482ea3e1df2918afb5a60f0b8f.jpg","course_paycount":"0","school_name":"歌者盟"},{"cid":"5766","course_tname":"杨草飞","course_name":"每日演唱问答（六）","course_price":"0.00","course_pic":"http://img.dianfu.net/img/20170122/7f020c574d6463d4b2f3a1ec108b69ac.jpg","course_paycount":"0","school_name":"歌者盟"},{"cid":"5765","course_tname":"孙吟","course_name":"每日演唱问答（五）","course_price":"0.00","course_pic":"http://img.dianfu.net/img/20170122/533d6335f55cae11c4c6ba012488eb71.jpg","course_paycount":"0","school_name":"歌者盟"},{"cid":"5764","course_tname":"刘玉","course_name":"每日演唱问答（四）","course_price":"0.00","course_pic":"http://img.dianfu.net/img/20170122/f212f095e39562ffde2655ae40635011.jpg","course_paycount":"0","school_name":"歌者盟"},{"cid":"5763","course_tname":"戴笠","course_name":"每日演唱问答（三）","course_price":"0.00","course_pic":"http://img.dianfu.net/img/20170122/40f9437d94a875be48779af69ea19ef2.jpg","course_paycount":"0","school_name":"歌者盟"},{"cid":"5762","course_tname":"常骏","course_name":"每日演唱问答（二）","course_price":"0.00","course_pic":"http://img.dianfu.net/img/20170122/e3a5661d9debec3da5944187fb223554.jpg","course_paycount":"0","school_name":"歌者盟"},{"cid":"5761","course_tname":"柴彬","course_name":"每日演唱问答（一）","course_price":"0.00","course_pic":"http://img.dianfu.net/img/20170122/c6465bcdea3845aed2b17c9544260042.jpg","course_paycount":"0","school_name":"歌者盟"}]
     * count : 3708
     * limit : 10
     * curpage : 1
     */

    private int count;
    private int limit;
    private int curpage;
    private List<DatalistBean> datalist;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getCurpage() {
        return curpage;
    }

    public void setCurpage(int curpage) {
        this.curpage = curpage;
    }

    public List<DatalistBean> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<DatalistBean> datalist) {
        this.datalist = datalist;
    }

    public static class DatalistBean {
        /**
         * cid : 5773
         * course_tname : 辛欣
         * course_name : 每日演唱问答（十）
         * course_price : 0.00
         * course_pic : http://img.dianfu.net/img/20170203/58a8af83eff8c56bc6696686d1903e00.jpg
         * course_paycount : 2
         * school_name : 歌者盟
         */

        private String cid;
        private String course_tname;
        private String course_name;
        private String course_price;
        private String course_pic;
        private String course_paycount;
        private String school_name;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getCourse_tname() {
            return course_tname;
        }

        public void setCourse_tname(String course_tname) {
            this.course_tname = course_tname;
        }

        public String getCourse_name() {
            return course_name;
        }

        public void setCourse_name(String course_name) {
            this.course_name = course_name;
        }

        public String getCourse_price() {
            return course_price;
        }

        public void setCourse_price(String course_price) {
            this.course_price = course_price;
        }

        public String getCourse_pic() {
            return course_pic;
        }

        public void setCourse_pic(String course_pic) {
            this.course_pic = course_pic;
        }

        public String getCourse_paycount() {
            return course_paycount;
        }

        public void setCourse_paycount(String course_paycount) {
            this.course_paycount = course_paycount;
        }

        public String getSchool_name() {
            return school_name;
        }

        public void setSchool_name(String school_name) {
            this.school_name = school_name;
        }
    }
}
