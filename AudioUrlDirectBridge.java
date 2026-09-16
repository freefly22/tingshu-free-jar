package com.github.eprendre.sources_by_eprendre;
import com.github.eprendre.tingshu.sources.AudioUrlDirectExtractor;
public final class AudioUrlDirectBridge {
  public static void play(String url, boolean autoPlay, boolean isCache, boolean isDebug) {
    AudioUrlDirectExtractor.INSTANCE.extract(url, autoPlay, isCache, isDebug);
  }
}
