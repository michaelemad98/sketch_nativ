package com.mihtechno.sketch;

import java.util.ArrayList;




public class Accountdata {
    public class User{

        public int EmployeeId;
        public ArrayList<Integer> Roles;
        public int Id;
        public String UserName;
        public boolean CanEditUserName;
        public String FullName;
        public String Mobile;
        public Object OtherPhones;
        public String Email;
        public String Photo;
        public boolean IsActive;


    }
    public class  Data{

        private String AuthToken;
        private String RefreshToken;
        private String ExpireDate;
        private int ExpireInDays;
        private User User;

        public String getAuthToken() {
            return AuthToken;
        }

        public void setAuthToken(String authToken) {
            AuthToken = authToken;
        }

        public String getRefreshToken() {
            return RefreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            RefreshToken = refreshToken;
        }

        public String getExpireDate() {
            return ExpireDate;
        }

        public void setExpireDate(String expireDate) {
            ExpireDate = expireDate;
        }

        public int getExpireInDays() {
            return ExpireInDays;
        }

        public void setExpireInDays(int expireInDays) {
            ExpireInDays = expireInDays;
        }


    }


    public Accountdata.Data getData() {
        return Data;
    }

    public void setData(Accountdata.Data data) {
        Data = data;
    }

    private  Data Data;
    private String Message;
    private boolean Success;
    private boolean Authorized;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public boolean isAuthorized() {
        return Authorized;
    }

    public void setAuthorized(boolean authorized) {
        Authorized = authorized;
    }
}
