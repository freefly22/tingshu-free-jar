# tingshu-free-jar — 我的听书外置 JAR 免费源

「我的听书」（com.github.eprendre.tingshu / 去广告版）的外置 JAR 源合集，支持在线订阅更新。

## 当前收录源（free_all_v7.jar）

| 源 | 状态 | 说明 |
|---|---|---|
| 懒人听书 (lrts.me) | ✅ 可用 | 搜索/详情/目录/分类全接口正常；VIP 内容需登录 |
| 恋听网 (ting55.com) | ✅ 可用 | POST /glink 动态取 mp3 |
| 喜马拉雅 | ✅ 可用 | 全链路免签名接口；VIP/付费标记 [付费] 不可播 |

## 在线订阅方法

⚠️ 订阅填的是 **JSON 描述文件**（不是 jar 直链）。app 会请求该 JSON（字段: version / entry_package / download_url），下载 download_url 的 jar 保存为 `jars/<entry_package>.jar`，并加载 `com.github.eprendre.<entry_package>.SourceEntry`。

**订阅链接（二选一）:**

海外/可直连 GitHub:
```
https://raw.githubusercontent.com/freefly22/tingshu-free-jar/main/source.json
```

国内推荐（jar 下载走 ghproxy 加速）:
```
https://raw.githubusercontent.com/freefly22/tingshu-free-jar/main/source_ghproxy.json
```

> entry_package 必须与 jar 内 SourceEntry 包名段一致（当前为 free_all）。
> 添加对话框会自动填入剪贴板里含 ".json" 的链接。

手动安装 jar 放到:
```
/sdcard/Android/data/com.github.eprendre.tingshu/files/jars/free_all.jar
```

## 更新

源有更新时直接改 jar 重新提交，订阅链接不变。发新版本请同步打 tag/release。

## 源码

- `XiMaLaYa.java` — 喜马拉雅源（搜索/分类/详情/曲目/音频直链）
- `XMExtractor.java` — 喜马拉雅音频地址提取器（子线程网络 + RxBus 回传）
- `AudioUrlDirectBridge.java` — 直链播放桥接

编译方式：`javac`（android-23 bootclasspath + 手写 stub）→ `d8` 转 dex → baksmali/smali 合并回 `classes.dex` → jar。

## 免责声明

仅供学习交流。所有源内容均来自各平台公开接口，与本项目无关；请支持正版。
