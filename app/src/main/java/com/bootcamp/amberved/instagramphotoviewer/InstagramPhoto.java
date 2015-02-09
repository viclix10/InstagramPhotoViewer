package com.bootcamp.amberved.instagramphotoviewer;

import java.io.Serializable;

/**
 * Created by amber_ved on 2/7/15.
 */
public class InstagramPhoto implements Serializable {
    private static final long serialVersionUID = 5177222050535318633L;

    public String imageUrl;
    public int imageHeight;
    public String username;
    public String userImageUrl;
    public String caption;
    public String id;
    public int likes;

}
