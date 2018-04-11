##如何运行主程序
>进入uvahomework/src/main/java/pers/ryan/uav/control
>编译运行PlaneControl.java

运行之后：
>提示：如果自行输入数据请输入input , 导入文本请输入import:

请按照提示操作

####input操作
- 自行输入测试用例操作
- 输入的信息符合条件会自动保存在uvahomework/src/main/resources下
>提示：请输入无人机信息,exit退出输入

此时可以输入测试用例：
```
plane1 1 1 1
plane1 1 1 1 1 2 3
plane1 2 3 4 1 1 1
plane1 3 4 5
plane1 1 1 1 1 2 3
exit
```
退出输入操作后；
>提示：请输入指定消息ID,exit退出服务：
####指定消息ID
```
当指定消息ID 2 时，输出：plane1 2 3 4 5
当指定消息ID 4 时，输出：Error: 4
当指定消息ID 100 时，输出：Cannot find 100
```
注意：
>当输入id不合规范时，如：3.0，会提示：请输入整数

####import操作
- 导入已有测试用例操作
>提示：请输入无人机id

注意：
- 测试用例存放在uvahomework/src/main/resources下
- 以.txt保存
- 已有plane1.txt测试用例

此时可以导入已有测试用例：
```
plane1
```
console打印出：
```
plane1 1 1 1
plane1 1 1 1 1 2 3
plane1 2 3 4 1 1 1
plane1 3 4 5
plane1 1 1 1 1 2 3
请输入指定消息ID,exit退出服务：
```

此时请参考指定消息ID
