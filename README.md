# springboot-admin-demo
Spring Boot Admin 本身提供监控告警功能，但是默认只提供了Hipchat、Slack等国外流行的通讯软件的集成；默认也支持邮件通知，本示例主要考虑到国内很多公司使用钉钉进行办公交流，故扩展了钉钉机器人通知；

### 钉钉机器人开通方法
1. 新建一个群聊；
2. 添加钉钉机器人；

![](https://github.com/luoyoubao/springboot-admin-demo/blob/master/images/dingtalk01.jpg)
![](https://github.com/luoyoubao/springboot-admin-demo/blob/master/images/dingtalk02.jpg)

3. 将钉钉机器人webhook-token配置到项目中，当有服务上下线的时候即会有消息通知；
PS:关于钉钉机器人更详细的文档请参见:<br>
https://open-doc.dingtalk.com/docs/doc.htm?spm=a219a.7629140.0.0.xSiICS&treeId=257&articleId=105735&docType=1#s0
