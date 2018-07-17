package com.haopintui.extend.util;

import java.io.Serializable;

/**
 * Created by wu on 2017/12/31.
 */

public class PidBean implements Serializable{

    private String memberId;
    private String unionId;
    private String adzoneid;

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getAdzoneid() {

        return adzoneid;
    }

    public void setAdzoneid(String adzoneid) {
        this.adzoneid = adzoneid;
    }

    public void to_pid(String pid){
        if(pid!=null&&!pid.equals("")){
            if(pid.lastIndexOf("mm_")>=0){
                pid  = pid.replaceFirst("mm_","");
            }
            String[] pidList = pid.split("_");
            int len = pidList.length;
            if(len>=0){
                this.memberId = pidList[0];
            }
            if(len>=1){
                this.unionId = pidList[1];
            }
            if(len>=2){
                this.adzoneid = pidList[2];
            }

        }
    }

}
