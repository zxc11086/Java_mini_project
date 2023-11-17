# 1.设置屏幕居中
```
Toolkit kit = Toolkit.getDefaultToolkit();
Dimension screenSize = kit.getScreenSize();
int screenw = screenSize.width;
int screenh = screenSize.height;
int windowsWidth = 800;
int windowsHeight = 450;
int x = (screenw - windowsWidth)/2;
int y = (screenh - windowsHeight)/2;
```

# 2.多线程
```
Thread t = new Thread(() -> {
    t.start();
});
```

# 3.多线程停止
```
//最好使用信号量
while(flag){
    //run
}
```

# 4.多线程start()和run()
```
start()是开辟
run()是继续执行
```
