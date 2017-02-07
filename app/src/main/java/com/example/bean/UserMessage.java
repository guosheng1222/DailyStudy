package com.example.bean;

/**
 * Created by ASUS on 2017/2/7.
 */

public class UserMessage {


    /**
     * data : {"ischange":"0","user_big_log":"http://www.meirixue.com/assets/home/images/avatar_set-big.jpg","user_ctime":"1486435463","user_id":"50384","user_middle_log":"http://www.meirixue.com/assets/home/images/avatar_set-medium.jpg","user_name":"13146065579","user_phone":"13146065579","user_sex":"1","user_small_log":"http://www.meirixue.com/assets/home/images/avatar_set-small.jpg"}
     * msg : 登录成功
     * status : 200
     */

    private DataBean data;
    private String msg;
    private int status;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * ischange : 0
         * user_big_log : http://www.meirixue.com/assets/home/images/avatar_set-big.jpg
         * user_ctime : 1486435463
         * user_id : 50384
         * user_middle_log : http://www.meirixue.com/assets/home/images/avatar_set-medium.jpg
         * user_name : 13146065579
         * user_phone : 13146065579
         * user_sex : 1
         * user_small_log : http://www.meirixue.com/assets/home/images/avatar_set-small.jpg
         */

        private String ischange;
        private String user_big_log;
        private String user_ctime;
        private String user_id;
        private String user_middle_log;
        private String user_name;
        private String user_phone;
        private String user_sex;
        private String user_small_log;

        public String getIschange() {
            return ischange;
        }

        public void setIschange(String ischange) {
            this.ischange = ischange;
        }

        public String getUser_big_log() {
            return user_big_log;
        }

        public void setUser_big_log(String user_big_log) {
            this.user_big_log = user_big_log;
        }

        public String getUser_ctime() {
            return user_ctime;
        }

        public void setUser_ctime(String user_ctime) {
            this.user_ctime = user_ctime;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_middle_log() {
            return user_middle_log;
        }

        public void setUser_middle_log(String user_middle_log) {
            this.user_middle_log = user_middle_log;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getUser_sex() {
            return user_sex;
        }

        public void setUser_sex(String user_sex) {
            this.user_sex = user_sex;
        }

        public String getUser_small_log() {
            return user_small_log;
        }

        public void setUser_small_log(String user_small_log) {
            this.user_small_log = user_small_log;
        }
    }
}
