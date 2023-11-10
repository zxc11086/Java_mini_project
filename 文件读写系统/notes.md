# 1.组件刷新

```
text_panel.revalidate();
text_panel.repaint();
```

# 2.组件添加默认居中，导致文本也在中间，添加组件时将位置设置为BorderLayout.WEST即可

# 3.给textarea设置滚动
```
JScrollPane jp = new JScrollPane(textarea);
panel.add(jp);
```

//有时会出现滚动条不显示的情况，可通过设置滚动条大小解决：
```
jp.setPreferrdSize(new Dimension(width,heigh));
```

# 4.通过GUI打开和保存文件
## 打开文件

```
JFileChooser fc = new JFileChooser();
fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
int result = fc.showOpenDialog(null);
if(result == JFileChooser.APPROVE_OPTION){
    try{
        File selectedFile = fc.getSelectedFile();
    } catch(Exception e){
        System.out.println(e);
    }
}
```

## 关闭文件

```
JFileChooser fc = new JFileChooser();
fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
int result = fc.showSaveDialog(null);
if(result == JFileChooser.APPROVE_OPTION){
    try{
        File selectedFile = fc.getSelectedFile();
        //假如文件不存在则新建
        if(selectedFile.exists() != null){
        	selectedFile.createNewFile();
        }
        //之后便可以对selectedFile进行操作
    } catch(Exception e){
        System.out.println(e);
    }
}
```

# 5.读写文件
## 读文件

```
FileReader fr = new FileReader(filename);
BufferedReader br = new BufferedReader(fr);
//按行读取
String content;
String line;
while((String line = br.readLine()) != null ){
	content += line;
	content += "\n";	//添加换行
}
br.close();
```

## 写文件

```
FileWriter fw = new FileWriter(filename);
BufferedWriter bw = new BufferedWriter(fw);
bw.write("content");
bw.flush();
bw.close();
```
