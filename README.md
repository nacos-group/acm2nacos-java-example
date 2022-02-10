# acm2nacos-java-example
本仓库旨在给java用户提供acm迁移nacos的能跑通的demo。

在整个迁移过程中，用户只需要做两部操作：

1 参考https://help.aliyun.com/document_detail/312705.html  将配置从acm导出，并导入到nacos

2 在src/main/java目录下的Example类中进行下面进行如注释内容的替换。

```

    // 配置中心的endpoint。
    // 在ACM迁移MSE Nacos的过程中，需要将这个{serverAddr}替换为MSE Nacos的内网/外网地址。（可以在MSE Nacos集群的详情页的"基础信息"栏目中找到）
    static final String mseServerAddr = "{serverAddr}";
    static final String dataId = "{dataId}";
    static final String group = "{group}";
    static final Properties propertiesForMse;
    static final String keyId = "alias/acs/mse";
    // 购买的mse所在的region，比如cn-hangzhou
    static final String regionId = "{region}";
    
    // 账号的AK/SK
    static final String akForFather = "{ak}";
    static final String skForFather = "{sk}";
```
