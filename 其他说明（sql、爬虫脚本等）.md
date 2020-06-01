### 数据库

``` sql
CREATE TABLE user(
    uid INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    email CHAR(30) NOT NULL,
    status CHAR(1),
    uuid VARCHAR(50) UNIQUE,
    CONSTRAINT user_ck_status CHECK (status = 'Y' or status = 'N')
)
```



``` sql
CREATE TABLE tlike(
uid INT NOT NULL,
tid INT NOT NULL,
date DATE NOT NULL,
PRIMARY KEY (uid, tid)
);

CREATE TABLE ted(
tid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(300),
discription VARCHAR(500),
mp4ShdUrl VARCHAR(100)
);


ALTER TABLE tlike ADD CONSTRAINT FK_user_tlike FOREIGN KEY ( uid ) REFERENCES USER ( uid ) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE tlike ADD CONSTRAINT FK_ted_tlike FOREIGN KEY ( tid ) REFERENCES ted ( tid ) ON DELETE RESTRICT ON UPDATE RESTRICT;


INSERT INTO ted (title, discription, mp4ShdUrl) VALUES 
('【TED】下一场全球农业革命', '传统肉类生产对我们的环境造成了危害，也给全人类的健康带来风险。但是人们并不会减少食用肉类，除非我们能够提供一些价格相当（或更便宜）、且口味一致（或更好）的替代品。在这场令人眼界大开的演讲中，食物改革家和TED成员布鲁斯·弗里德里克展示了植物肉和细胞肉产品，它们会很快促进全球肉类产业转型——也会改变你的晚餐。', 'http://mov.bn.netease.com/open-movie/nos/mp4/2019/12/02/SEUF3S7GU_shd.mp4'),
('【TED】恶意伪造技术是如何破坏真相的？', 'Deepfake，是一种为了恶意的目的，像是引发暴力或诽谤政客和记者，来伪造视频或音频的技术。它的使用在逐渐变成一种真实的威胁。随着这方面的工具变的更加便捷，做出来的产品更加真实，它们将如何塑造我们对世界的看法？在这个演讲中，法律教授 Danielle Citron展示了Deepfakes技术如何放大了我们的不信任感，并且提出了一些保护真相的方法。', 'http://mov.bn.netease.com/open-movie/nos/mp4/2019/12/03/SEUI6M74B_shd.mp4'),
('【TED】垂直农场：节约资金、资源的新型农业模式','到 2050 年，全球人口预计将达到 98 亿。我们怎么养活所有人？由投资银行从业者转行农业工作者的 Stuart Oda 提到了室内垂直农场：在一个可控的、不受气候影响的环境中，在分层的架子上种植食物。在一次前沿性的演讲中，他解释了这种方法如何能保持更好的安全标准，节约资金，节约用水，为我们的子孙后代造福。', 'http://mov.bn.netease.com/open-movie/nos/mp4/2020/03/29/SF807Q11K_shd.mp4'),
('【TED】当还在童年的我们受到了创伤，很有可能会影响我们一生的健康', '儿童时期的创伤并不是你在成长过程中得到的。儿科医生Nadine Burke Harris解释说，虐待，忽视和父母在心理健康或药物滥用问题上挣扎的反复压力对大脑的发育产生了真实而切实的影响。这种情况在一生中展开，直到那些经历过高水平创伤的患者患心脏病和肺癌的风险增加了三倍。恳求儿科医学对抗创伤的预防和治疗，正面。', 'http://mov.bn.netease.com/open-movie/nos/mp4/2015/01/19/SAFDAP8DJ_shd.mp4'),
('【TED】所谓断舍离', '我们原以为买来会带来快乐的东西，却给我们的心挖了更深的洞。', 'http://mov.bn.netease.com/open-movie/nos/mp4/2016/03/28/SBIAEU9P4_shd.mp4')
```



### python脚本



```python
import requests

url = 'https://c.open.163.com/open/pc/v2/recommend/list.do?targetId=MF5HNCDAQ'
headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.9 Safari/537.36'
}
session = requests.session()
session.get(url='https://open.163.com/ted/', headers=headers)
page_data = session.get(url=url, headers=headers).json()
all_data = page_data['data']
allUrl = []
for playId in range(len(all_data)):
    allUrl.append('https://c.open.163.com/open/mob/movie/list.do?plid=' + all_data[playId]['playId'])
print(allUrl)
allSql = 'INSERT INTO ted (title, discription, mp4ShdUrl) VALUES '
for oneUrl in allUrl:
    detail_datas = session.get(url=oneUrl,headers=headers).json()
    print(detail_datas)
    detail_data = detail_datas['data']
    sql = f'("{detail_data["title"]}","{detail_data["description"]}","{detail_data["videoList"][0]["mp4ShdUrl"]}")'
    if oneUrl != allUrl[0]:
        allSql += ','+ sql
    else:
        allSql += sql

print(allSql)
with open('./sql.sql', 'w', encoding='utf-8') as fp:
    fp.write(allSql)


```



### 配置

运行前请修改java版本及mysql版本，发送邮件的邮箱账号密码也需要修改



我的配置

JDK 10

MYSQL 8.0.12

python 3.8