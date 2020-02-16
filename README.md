## 介绍

这个项目是为React Music 提供的网易云API接口。前端请步至[react music](https://github.com/oleolema/react-music)

网易云API接口使用了加密算法，无法直接使用

例如: 
获取歌曲的url为

`https://music.163.com/weapi/song/enhance/player/url/v1`

请求一首歌需要的参数如下

```
{
    ids: [`36990266`],
    encodeType: "aac",
    level: "standard"
}
```

直接明文请求肯定是失败的

本项目核心就是生成加密结果

使用网易云的加密算法 加密后的参数如下

`params=r81W3nuHMhoUjPS%2F3NviKzBGt%2B3NGi14LS3o4wDN9mSZVZ1iMsX59e7zwqIJT41m%2FxlE4XgR4dH%2BSqZH9gMSjl%2F7vNhQ1%2FC%2BfgRM0pcuYKqx%2Fu8GIfo5MWE8fc0D9mN%2B&encSecKey=6c551b2b3eba941b262d2fd2def0540d14d814ecff890163104f7cf6b7824f309ba779b1b3bab745580bac4a5e08a7ea208aaa0f8a1c59c06d6d472f1324260d948d50780343ba920938d7f8aed5840ef42c9dcfea89a68795a1050c98a4346f8b779a0c456ac1943b73cff156897616f31a4df1407e84256fc81fd49f469d06`

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/a577d2f827e56e498166)

或者
[对比两种请求](https://gold-space-1114.postman.co/collections/7711325-76eaddc0-33c3-4d5e-b5cd-e78369ec2fe1?version=latest&workspace=407054ea-34d6-47d7-8f10-e245c2c92a3a#a16a4134-f594-4466-9200-609ad04ffb2b)


网易云API接口由抓包分析而来，具体api参数加密算法请参考[encode.js]()

