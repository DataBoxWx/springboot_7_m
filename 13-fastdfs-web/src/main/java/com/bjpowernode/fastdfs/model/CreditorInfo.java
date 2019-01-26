package com.bjpowernode.fastdfs.model;

public class CreditorInfo {
    private Integer id;

    private String realname;

    private String address;

    private String phone;

    private String groupname;

    private String remotefilename;

    private String contracturl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getRemotefilename() {
        return remotefilename;
    }

    public void setRemotefilename(String remotefilename) {
        this.remotefilename = remotefilename;
    }

    public String getContracturl() {
        return contracturl;
    }

    public void setContracturl(String contracturl) {
        this.contracturl = contracturl;
    }
}