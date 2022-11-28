package com.zuo.constant;

public class Constants {
    /*歌曲图片，歌手图片，歌曲文件，歌单图片等文件的存放路径*/
    //获取当前工作目录的位置
    public static String PROJECT_PATH = System.getProperty("user.dir");
    //头像图片位置
    public static String AVATOR_IMAGES_PATH = "file:" + PROJECT_PATH + "/img/avatorImages/";
    //歌单图片
    public static String SONGLIST_PIC_PATH = "file:" + PROJECT_PATH + "/img/songListPic/";
    //歌曲图片
    public static String SONG_PIC_PATH = "file:" + PROJECT_PATH + "/img/songPic/";
    //歌曲路径
    public static String SONG_PATH = "file:" + PROJECT_PATH + "/song/";
    //歌手图片
    public static String SINGER_PIC_PATH = "file:" + PROJECT_PATH + "/img/singerPic/";
    //轮播图图片
    public static String BANNER_PIC_PATH = "file:" + PROJECT_PATH + "/img/swiper/";


    /* 盐值加密 */
    public static String SALT = "zyt";
}
