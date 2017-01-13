package com.example.bean;

import java.util.List;

/**
 * Created by ASUS on 2017/1/12.
 */

public class HomeContent  {



    private int status;
    private DataBean data;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {


        private IndexrecommendBean indexrecommend;
        private List<SliderBean> slider;
        private List<HotcategoryBean> hotcategory;
        private List<AdlistBean> adlist;
        private List<HotcourseBean> hotcourse;
        private List<IndexothersBean> indexothers;

        public IndexrecommendBean getIndexrecommend() {
            return indexrecommend;
        }

        public void setIndexrecommend(IndexrecommendBean indexrecommend) {
            this.indexrecommend = indexrecommend;
        }

        public List<SliderBean> getSlider() {
            return slider;
        }

        public void setSlider(List<SliderBean> slider) {
            this.slider = slider;
        }

        public List<HotcategoryBean> getHotcategory() {
            return hotcategory;
        }

        public void setHotcategory(List<HotcategoryBean> hotcategory) {
            this.hotcategory = hotcategory;
        }

        public List<AdlistBean> getAdlist() {
            return adlist;
        }

        public void setAdlist(List<AdlistBean> adlist) {
            this.adlist = adlist;
        }

        public List<HotcourseBean> getHotcourse() {
            return hotcourse;
        }

        public void setHotcourse(List<HotcourseBean> hotcourse) {
            this.hotcourse = hotcourse;
        }

        public List<IndexothersBean> getIndexothers() {
            return indexothers;
        }

        public void setIndexothers(List<IndexothersBean> indexothers) {
            this.indexothers = indexothers;
        }

        public static class IndexrecommendBean {
            private List<TopBean> top;
            private List<ListviewBean> listview;

            public List<TopBean> getTop() {
                return top;
            }

            public void setTop(List<TopBean> top) {
                this.top = top;
            }

            public List<ListviewBean> getListview() {
                return listview;
            }

            public void setListview(List<ListviewBean> listview) {
                this.listview = listview;
            }

            public static class TopBean {

                private String cid;
                private String course_name;
                private String course_paycount;
                private String course_price;
                private String course_pic;
                private String sid;
                private String school_name;
                private int usercount;
                private int icon;

                public String getCid() {
                    return cid;
                }

                public void setCid(String cid) {
                    this.cid = cid;
                }

                public String getCourse_name() {
                    return course_name;
                }

                public void setCourse_name(String course_name) {
                    this.course_name = course_name;
                }

                public String getCourse_paycount() {
                    return course_paycount;
                }

                public void setCourse_paycount(String course_paycount) {
                    this.course_paycount = course_paycount;
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

                public String getSid() {
                    return sid;
                }

                public void setSid(String sid) {
                    this.sid = sid;
                }

                public String getSchool_name() {
                    return school_name;
                }

                public void setSchool_name(String school_name) {
                    this.school_name = school_name;
                }

                public int getUsercount() {
                    return usercount;
                }

                public void setUsercount(int usercount) {
                    this.usercount = usercount;
                }

                public int getIcon() {
                    return icon;
                }

                public void setIcon(int icon) {
                    this.icon = icon;
                }
            }

            public static class ListviewBean {

                private String cid;
                private String course_name;
                private String course_paycount;
                private String course_price;
                private String course_pic;
                private String sid;
                private String school_name;
                private int usercount;
                private int icon;

                public String getCid() {
                    return cid;
                }

                public void setCid(String cid) {
                    this.cid = cid;
                }

                public String getCourse_name() {
                    return course_name;
                }

                public void setCourse_name(String course_name) {
                    this.course_name = course_name;
                }

                public String getCourse_paycount() {
                    return course_paycount;
                }

                public void setCourse_paycount(String course_paycount) {
                    this.course_paycount = course_paycount;
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

                public String getSid() {
                    return sid;
                }

                public void setSid(String sid) {
                    this.sid = sid;
                }

                public String getSchool_name() {
                    return school_name;
                }

                public void setSchool_name(String school_name) {
                    this.school_name = school_name;
                }

                public int getUsercount() {
                    return usercount;
                }

                public void setUsercount(int usercount) {
                    this.usercount = usercount;
                }

                public int getIcon() {
                    return icon;
                }

                public void setIcon(int icon) {
                    this.icon = icon;
                }
            }
        }

        public static class SliderBean {

            private String id;
            private String title;
            private String url;
            private String img;
            private String stype;
            private String order;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getStype() {
                return stype;
            }

            public void setStype(String stype) {
                this.stype = stype;
            }

            public String getOrder() {
                return order;
            }

            public void setOrder(String order) {
                this.order = order;
            }
        }

        public static class HotcategoryBean {


            private String id;
            private String cid;
            private String cname;
            private String img;
            private Object category_fiid;
            private Object category_fid;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getCname() {
                return cname;
            }

            public void setCname(String cname) {
                this.cname = cname;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public Object getCategory_fiid() {
                return category_fiid;
            }

            public void setCategory_fiid(Object category_fiid) {
                this.category_fiid = category_fiid;
            }

            public Object getCategory_fid() {
                return category_fid;
            }

            public void setCategory_fid(Object category_fid) {
                this.category_fid = category_fid;
            }
        }

        public static class AdlistBean {

            private String id;
            private String name;
            private String title;
            private String img;
            private String url;
            private Object color;
            private String stype;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public Object getColor() {
                return color;
            }

            public void setColor(Object color) {
                this.color = color;
            }

            public String getStype() {
                return stype;
            }

            public void setStype(String stype) {
                this.stype = stype;
            }
        }

        public static class HotcourseBean {

            private String id;
            private String cid;
            private String name;
            private String title;
            private String img;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }

        public static class IndexothersBean {

            private String cid;
            private String course_name;
            private String course_paycount;
            private String course_price;
            private String course_pic;
            private String sid;
            private String school_name;
            private int usercount;
            private int icon;

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getCourse_name() {
                return course_name;
            }

            public void setCourse_name(String course_name) {
                this.course_name = course_name;
            }

            public String getCourse_paycount() {
                return course_paycount;
            }

            public void setCourse_paycount(String course_paycount) {
                this.course_paycount = course_paycount;
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

            public String getSid() {
                return sid;
            }

            public void setSid(String sid) {
                this.sid = sid;
            }

            public String getSchool_name() {
                return school_name;
            }

            public void setSchool_name(String school_name) {
                this.school_name = school_name;
            }

            public int getUsercount() {
                return usercount;
            }

            public void setUsercount(int usercount) {
                this.usercount = usercount;
            }

            public int getIcon() {
                return icon;
            }

            public void setIcon(int icon) {
                this.icon = icon;
            }
        }
    }
}
