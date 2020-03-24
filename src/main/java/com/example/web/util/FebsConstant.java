package com.example.web.util;

/**
 * @Author: 邓启龙
 * @Description:
 * @Date: Created in 2019/5/21 16:34
 */
public class FebsConstant {
    private FebsConstant() {
    }

    //查找歌单的接口地址
    public static final String MUSIC_SHEET_ID = "http://music.163.com/api/v3/playlist/detail";
    //查找歌曲的接口地址
    public static final String MUSIC_ID = "http://music.163.com/weapi/song/enhance/player/url?csrf_token=";
    //查找歌曲图片
    public static final String MUSIC_PIC = "https://p3.music.126.net/";
    //查找歌词
    public static final String MUSIC_LRC = "http://music.163.com/api/song/lyric";
    //根据用户ID查找用户歌单
    public static final String User_SHEET_ID = "http://music.163.com/api/user/playlist/";
    //网易云歌曲评论查找
    public static final String SONG_COMMENT = "http://music.163.com/api/v1/resource/comments/";
    //网易云根据名称查找歌曲
    public static  final  String SERACH_BYNAME="http://music.163.com/weapi/cloudsearch/get/web?csrf_token=";
    //QQ音乐根据名称查找歌曲
    public static  final  String SERACH_QQMUSIC="https://c.y.qq.com/soso/fcgi-bin/client_search_cp";

}
