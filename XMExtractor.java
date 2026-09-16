package com.github.eprendre.sources_by_eprendre;

import com.github.eprendre.tingshu.sources.AudioUrlExtractor;
import com.github.eprendre.tingshu.utils.Prefs;
import com.github.eprendre.tingshu.widget.RxBus;
import com.github.eprendre.tingshu.widget.RxEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 * 喜马拉雅音频提取器 — 网络请求放子线程, 结果经 RxBus 回传(与 AudioUrlDirectExtractor 行为一致)
 */
public final class XMExtractor implements AudioUrlExtractor {
    public static final XMExtractor INSTANCE = new XMExtractor();

    private static final String UA_APP = "tingapp_5.4.1 tzid=1000 deviceType=1";

    private XMExtractor() {}

    private static String httpGet(String urlStr) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
        conn.setRequestProperty("User-Agent", UA_APP);
        conn.setConnectTimeout(15000);
        conn.setReadTimeout(20000);
        int code = conn.getResponseCode();
        if (code != 200) throw new RuntimeException("HTTP " + code);
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) sb.append(line);
        br.close();
        return sb.toString();
    }

    @Override
    public void extract(String url, boolean autoPlay, boolean isCache, boolean isDebug) {
        final String fUrl = url;
        final boolean fAuto = autoPlay, fCache = isCache, fDebug = isDebug;
        Thread t = new Thread(() -> {
            try {
                String json = httpGet(fUrl);
                JSONObject d = new JSONObject(json);
                String audio = d.optString("playUrl64", "");
                if (audio.isEmpty()) audio = d.optString("playUrl32", "");
                if (audio.isEmpty()) audio = d.optString("playPathAacv164", "");
                if (audio.isEmpty()) {
                    String msg = "ret=" + d.opt("ret") + " msg=" + d.optString("msg", "无播放地址");
                    postError(fUrl, fCache, fDebug, new RuntimeException(msg));
                    return;
                }
                if (fDebug) {
                    RxBus.INSTANCE.post(new RxEvent.LogEvent("音频地址: " + audio));
                    return;
                }
                if (fCache) {
                    RxBus.INSTANCE.post(new RxEvent.CacheEvent(fUrl, audio, 0));
                    return;
                }
                Prefs.INSTANCE.setCurrentAudioUrl(audio);
                if (fAuto) {
                    RxBus.INSTANCE.post(new RxEvent.ParsingPlayUrlEvent(3));
                } else {
                    RxBus.INSTANCE.post(new RxEvent.ParsingPlayUrlEvent(1));
                }
            } catch (Exception e) {
                postError(fUrl, fCache, fDebug, e);
            }
        });
        t.setName("XMExtractor");
        t.start();
    }

    private static void postError(String url, boolean isCache, boolean isDebug, Exception e) {
        if (isDebug) {
            java.io.StringWriter sw = new java.io.StringWriter();
            e.printStackTrace(new java.io.PrintWriter(sw));
            RxBus.INSTANCE.post(new RxEvent.LogEvent(sw.toString()));
            return;
        }
        if (isCache) {
            RxBus.INSTANCE.post(new RxEvent.CacheEvent(url, "", 2));
        } else {
            RxBus.INSTANCE.post(new RxEvent.ParsingPlayUrlEvent(2));
        }
    }
}
