package com.example.tacademy.gameviewproject.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tacademy on 2017-02-06.
 */
public class ReViewPost
{
    // 제목
    String title;
    // 내용
    String content;
    // uid(내부 관리용)
    String uid;
    // 작성자 아이디
    String author;
    // star 카운트 (좋아요 개수)
    int start_count;
    // 누가 좋아요 했는지
//    Map<String, Boolean> stars = new HashMap<>();
    // 사진이 저장되는 url 주소
    String url;

    public ReViewPost(){

    }

    public ReViewPost(String title, String content, String uid, String author, String url) {
        this.title = title;
        this.content = content;
        this.uid = uid;
        this.author = author;
        this.url = url;

    }

    public Map<String,Object>toPostMap()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("title",title);
        map.put("content",content);
        map.put("uid",uid);
        map.put("author",author);
//        map.put("start_count",start_count);
        // map.put("stars",stars);
        map.put("url",url);
        return map;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getStart_count() {
        return start_count;
    }

    public void setStart_count(int start_count) {
        this.start_count = start_count;
    }

//    public Map<String, Boolean> getStars() {
//        return stars;
//    }

//    public void setStars(Map<String, Boolean> stars) {
//        this.stars = stars;
//    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}