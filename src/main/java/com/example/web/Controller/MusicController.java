package com.example.web.Controller;

import com.example.web.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 邓启龙
 * @Description:
 * @Date: Created in 2019/5/21 16:31
 */
@Controller
public class MusicController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 根据歌单id获取歌单
     *
     * @param songSheetId 歌单id
     * @return
     */
    @RequestMapping("music/query")
    @ResponseBody
    public ResponseBo queryWeather(String songSheetId) {
        try {
            String data = HttpUtils.sendPost(FebsConstant.MUSIC_SHEET_ID, "id=" + songSheetId + "&s=0&n=1000&t=0");
            return ResponseBo.ok(data);
        } catch (Exception e) {
            log.error("查询歌曲失败", e);
            return ResponseBo.error("查询歌单失败");
        }
    }

    /**
     * 根据歌曲id查找歌曲播放地址
     *
     * @param musicId
     * @return
     */
    @RequestMapping("music/queryByMusicId")
    @ResponseBody
    public ResponseBo queryByMusicId(String musicId) {
        try {
            String[] urls = {musicId};
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("ids", urls);
            map.put("br", 999000);
            map.put("csrf_token", "");
            try {
                String data = MusicUtil.connection(FebsConstant.MUSIC_ID, MusicUtil.prepare(map));
                System.out.println(data);
                return ResponseBo.ok(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @description: 根据歌曲id查找歌曲图片
     * @params picid
     * @return: data
     * @author: 邓启龙
     * @time: 2020/3/9 10:46
     */
    @RequestMapping("music/queryPicMusicId")
    @ResponseBody
    public ResponseBo queryImageById(String picId) {
        try {
            String data = HttpUtils.sendPost(FebsConstant.MUSIC_PIC, "id=" + picId);
            return ResponseBo.ok(data);
        } catch (Exception e) {
            return ResponseBo.error("查询歌曲图片失败");
        }
    }

    /**
     * 根据歌词id查找歌词
     *
     * @param MusicID
     * @return
     */
    @RequestMapping("music/queryLrc")
    @ResponseBody
    public ResponseBo queryLrcById(String MusicID) {
        try {
            String data = HttpUtils.sendPost(FebsConstant.MUSIC_LRC, "id=" + MusicID + "&lv=-1&kv=-1&tv=-1&os=linux");
            return ResponseBo.ok(data);
        } catch (Exception e) {
            log.error("查询歌词失败", e);
            return null;
        }
    }

    /**
     * @description: 根据用户提供的ID获取歌单
     * @params
     * @return:
     * @author: 邓启龙
     * @time: 2020/3/10 13:40
     */
    @RequestMapping("music/queryByUserID")
    @ResponseBody
    public ResponseBo queryUserSheetByUserId(String UserId) {
        try {
            String data = HttpUtils.sendPost(FebsConstant.User_SHEET_ID, "uid=" + UserId + "&offset=0&limit=1001");
            return ResponseBo.ok(data);
        } catch (Exception e) {
            log.error("查询用户歌单失败", e);
            return null;
        }

    }
    /**
     * @description: 根据歌曲id获取评论
     * @params SongId
     * @return: data
     * @author: 邓启龙
     * @time: 2020/3/10 13:40
     */
    @RequestMapping("music/queryComment")
    @ResponseBody
   public ResponseBo queryComment(String SongId){
       //R_SO_4_516997458
        try {
            String data=HttpUtils.sendPost2(FebsConstant.SONG_COMMENT,"R_SO_4_"+SongId);
           return ResponseBo.ok(data);
        }catch (Exception e){
            log.error("查询歌曲评论失败");
        }
        return null;
   }
   /**
    * @description:根据名称查找歌曲
    * @params
    * @return:
    * @author: 邓启龙
    * @time: 2020/3/11 13:36
    */
   @RequestMapping("/music/search")
   @ResponseBody
   public ResponseBo queryComment(String SongName,String source, int count,  int Pages){
       int type = 1;

       switch (source){
           case "netease":
               try {
                   Map<String, Object> map = new HashMap<String, Object>();
                   map.put("s", SongName);
                   map.put("type", type);
                   map.put("limit",count);
                   map.put("offset",Pages);
                   map.put("total","true");
                   map.put("csrf_token", "");
                   String data = MusicUtil.connection(FebsConstant.SERACH_BYNAME, MusicUtil.prepare(map));
                   System.out.println(data);
                   return ResponseBo.ok(data);
               } catch (Exception e) {
                  log.error("查找歌曲失败");
               }
               break;
       }
        return null;
   }
}

