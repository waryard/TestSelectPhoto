package com.wyd.royalprince.testselectphoto.selectphotoview;

import java.io.Serializable;

/**
 * Created by wyd on 2018/3/15.
 */

public class SingleImageModel implements Serializable {

    public String path;
    public boolean isPicked;
    public long date;
    public long id;

    public SingleImageModel(String path, boolean isPicked, long date, long id) {
        this.path = path;
        this.isPicked = isPicked;
        this.date = date;
        this.id = id;
    }

    public SingleImageModel() {

    }

    public boolean isThisImage(String path) {
        return this.path.equalsIgnoreCase(path);
    }
}
