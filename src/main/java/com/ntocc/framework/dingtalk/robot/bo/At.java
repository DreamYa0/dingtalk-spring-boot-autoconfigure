package com.ntocc.framework.dingtalk.robot.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dreamyao
 */
public class At {

    /**
     * 被@人的手机号(在content里添加@人的手机号)
     */
    private List<String> atMobiles;
    /**
     * 当@所有人时：true，否则为：false
     */
    private Boolean isAtAll;

    public At(List<String> atMobiles, Boolean isAtAll) {
        this.atMobiles = atMobiles;
        this.isAtAll = isAtAll;
    }

    public At(List<String> atMobiles) {
        this.atMobiles = atMobiles;
        this.isAtAll = false;
    }

    public At(String atMobile) {
        List<String> atMobiles = new ArrayList<>();
        atMobiles.add(atMobile);
        this.atMobiles = atMobiles;
        this.isAtAll = false;
    }

    public At(String atMobile, Boolean isAtAll) {
        List<String> atMobiles = new ArrayList<>();
        atMobiles.add(atMobile);
        this.atMobiles = atMobiles;
        this.isAtAll = isAtAll;
    }

    public List<String> getAtMobiles() {
        return atMobiles;
    }

    public Boolean getIsAtAll() {
        return isAtAll;
    }
}
