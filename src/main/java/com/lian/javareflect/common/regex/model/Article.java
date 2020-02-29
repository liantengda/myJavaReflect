package com.lian.javareflect.common.regex.model;

import lombok.Data;

@Data
public class Article {
    /// 文章标题
    public String AritcleTitle ;
    /// 文章链接
    public String AritcleUrl ;
    /// 文章简介
    public String AritcleInto ;
    /// 作者名
    public String Author ;
    /// 作者地址
    public String AuthorUrl ;
    /// 作者图片
    public String AuthorImg ;
    /// 发布时间
    public String PublishTime ;
    /// 推荐数
    public String DiggNum ;
    /// 评论数
    public String CommentNum ;
    /// 阅读数
    public String ReadNum ;
}
