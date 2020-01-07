package com.qfjy.api.tuling;

import lombok.Data;

@Data
public class TulingBean {

    private int reqType = 0;

    private Perception perception;

    private UserInfo userInfo;


}
