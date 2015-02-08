package com.bootcamp.amberved.instagramphotoviewer;

import java.io.Serializable;

/**
 * Created by amber_ved on 2/7/15.
 */
public class InstagramPhoto implements Serializable {
    public String imageUrl;
    public int imageHeight;
    public String username;
    public String userImageUrl;
    public String caption;
    public int likes;
}
