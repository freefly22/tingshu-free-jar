package com.github.eprendre.sources_by_eprendre;

import com.github.eprendre.tingshu.sources.AudioUrlExtractor;
import com.github.eprendre.tingshu.sources.AudioUrlJsonExtractor;
import com.github.eprendre.tingshu.sources.TingShu;
import com.github.eprendre.tingshu.utils.Book;
import com.github.eprendre.tingshu.utils.BookDetail;
import com.github.eprendre.tingshu.utils.Category;
import com.github.eprendre.tingshu.utils.CategoryMenu;
import com.github.eprendre.tingshu.utils.CategoryTab;
import com.github.eprendre.tingshu.utils.Episode;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import kotlin.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 喜马拉雅源 — 全链路无签名接口 (从唔语 app 逆向 + 实测)
 * 搜索:   search.ximalaya.com/front/v1        (UA: tingapp, 无需 xm-sign)
 * 专辑页: www.ximalaya.com/revision/album/v1/simple
 * 曲目:   mobile.ximalaya.com/mobile/v1/album/track
 * 直链:   mobile.ximalaya.com/mobile/track/{trackId} → playUrl64
 */
public final class XiMaLaYa extends TingShu {
    public static final XiMaLaYa INSTANCE = new XiMaLaYa();

    private static final String UA_APP = "tingapp_5.4.1 tzid=1000 deviceType=1";

    private XiMaLaYa() {}

    private static String httpGet(String urlStr) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
        conn.setRequestProperty("User-Agent", UA_APP);
        conn.setConnectTimeout(15000);
        conn.setReadTimeout(20000);
        int code = conn.getResponseCode();
        if (code != 200) throw new RuntimeException("HTTP " + code + " for " + urlStr);
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) sb.append(line);
        br.close();
        return sb.toString();
    }

    @Override
    public String getSourceId() {
        return "xia_malaya_wuyu_01";
    }

    @Override
    public String getUrl() {
        return "https://www.ximalaya.com";
    }

    @Override
    public String getName() {
        return "喜马拉雅";
    }

    public String getDesc() {
        return "喜马拉雅(唔语源)。搜索/分类全免费, VIP与付费专辑标记[VIP]/[精品]不可播。专辑详情免登录。";
    }

    @Override
    public boolean isMultipleEpisodePages() {
        return true;
    }

    @Override
    public AudioUrlExtractor getAudioUrlExtractor() {
        return XMExtractor.INSTANCE;
    }

    @Override
    public Pair<List<Book>, Integer> search(String keywords, int page) {
        try {
            String kw = URLEncoder.encode(keywords, "UTF-8");
            String url = "https://search.ximalaya.com/front/v1?core=album&kw=" + kw
                    + "&page=" + page + "&rows=20&device=android";
            JSONObject resp = new JSONObject(httpGet(url));
            JSONArray docs = resp.getJSONObject("response").getJSONArray("docs");
            List<Book> books = new ArrayList<>();
            for (int i = 0; i < docs.length(); i++) {
                JSONObject d = docs.getJSONObject(i);
                String cover = d.optString("cover_path", "");
                if (!cover.isEmpty() && !cover.startsWith("http")) cover = "https:" + cover;
                int cut = cover.indexOf("!op_type");
                if (cut > 0) cover = cover.substring(0, cut);
                String title = d.optString("title", "");
                boolean paid = d.optBoolean("is_paid", false);
                int vipType = d.optInt("is_vip", 0);
                if (vipType == 2) title = "[VIP] " + title;
                else if (vipType == 0 && paid) title = "[精品] " + title;
                Book b = new Book(cover, getUrl() + "?" + d.optInt("id"),
                        title,
                        "人气: " + d.optInt("play", 0),
                        d.optString("nickname", ""));
                b.setIntro(d.optString("intro", ""));
                boolean finished = d.optInt("is_finished", 0) == 1;
                b.setStatus((finished ? "完本|" : "") + "共" + d.optInt("tracks", 0) + "章");
                b.setSourceId(getSourceId());
                b.setCompleted(finished);
                books.add(b);
            }
            return new Pair<>(books, page + 1);
        } catch (Exception e) {
            throw new RuntimeException("喜马拉雅搜索失败: " + e.getMessage(), e);
        }
    }

    @Override
    public BookDetail getBookDetailInfo(String bookUrl, boolean loadEpisodes, boolean loadFullPages) {
        String albumId = bookUrl.split("\\?")[1];
        List<Episode> episodes = new ArrayList<>();
        String intro = "";
        String title = "";
        try {
            if (loadEpisodes) {
                int pageSize = 30;
                int pageNum = 1;
                while (true) {
                    String url = "https://mobile.ximalaya.com/mobile/v1/album/track?albumId=" + albumId
                            + "&page=" + pageNum + "&pageSize=" + pageSize;
                    JSONObject resp = new JSONObject(httpGet(url));
                    if (resp.optInt("ret", -1) != 0) break;
                    JSONArray list = resp.getJSONObject("data").optJSONArray("list");
                    if (list == null || list.length() == 0) break;
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject t = list.getJSONObject(i);
                        String epTitle = t.optString("title", "");
                        if (t.optBoolean("isPaid", false) || t.optInt("paidType", 0) != 0) {
                            epTitle = "[付费] " + epTitle;
                        }
                        Episode ep = new Episode(epTitle,
                                "https://mobile.ximalaya.com/mobile/track/" + t.optLong("trackId"));
                        ep.setFree(!(t.optBoolean("isPaid", false) || t.optInt("paidType", 0) != 0));
                        episodes.add(ep);
                    }
                    if (!loadFullPages || list.length() < pageSize) break;
                    pageNum++;
                }
            }
            // 专辑信息
            String simple = httpGet("https://www.ximalaya.com/revision/album/v1/simple?albumId=" + albumId);
            JSONObject mainInfo = new JSONObject(simple)
                    .getJSONObject("data").getJSONObject("albumPageMainInfo");
            title = mainInfo.optString("albumTitle", "");
            intro = mainInfo.optString("detailRichIntro", mainInfo.optString("shortIntro", ""));
        } catch (Exception e) {
            throw new RuntimeException("喜马拉雅详情失败: " + e.getMessage(), e);
        }
        BookDetail detail = new BookDetail(episodes, intro, "", "", episodes.size(), "");
        detail.setTitle(title);
        return detail;
    }

    @Override
    public Category getCategoryList(String url) {
        try {
            // url 形如 .../revision/category/v2/albums?pageNum=1&pageSize=30&sort=1&categoryId=12
            String u = url;
            if (u.contains("pageSize=56")) u = u.replace("pageSize=56", "pageSize=30");
            JSONObject resp = new JSONObject(httpGet(u));
            JSONObject data = resp.getJSONObject("data");
            JSONArray albums = data.getJSONArray("albums");
            List<Book> books = new ArrayList<>();
            for (int i = 0; i < albums.length(); i++) {
                JSONObject a = albums.getJSONObject(i);
                String cover = a.optString("albumCoverPath", "");
                if (!cover.isEmpty() && !cover.startsWith("http")) cover = "https://imagev2.xmcdn.com/" + cover;
                String title = a.optString("albumTitle", "");
                int vipType = a.optInt("vipType", 0);
                boolean paid = a.optBoolean("isPaid", false);
                if (vipType == 2) title = "[VIP] " + title;
                else if (vipType == 0 && paid) title = "[精品] " + title;
                Book b = new Book(cover, getUrl() + "?" + a.optInt("albumId"), title,
                        "人气: " + a.optInt("albumPlayCount", 0),
                        a.optString("albumUserNickName", ""));
                b.setStatus("共" + a.optInt("albumTrackCount", 0) + "章");
                b.setSourceId(getSourceId());
                books.add(b);
            }
            int pageNum = data.optInt("pageNum", 1);
            int pageSize = data.optInt("pageSize", 30);
            int total = data.optInt("total", 0);
            int totalPage = pageSize > 0 ? (total + pageSize - 1) / pageSize : 1;
            String nextUrl = "";
            if (pageNum < totalPage) {
                nextUrl = url.replaceFirst("pageNum=\\d+", "pageNum=" + (pageNum + 1));
            }
            return new Category(books, pageNum, totalPage, url, nextUrl);
        } catch (Exception e) {
            throw new RuntimeException("喜马拉雅分类失败: " + e.getMessage(), e);
        }
    }

    @Override
    public List<CategoryMenu> getCategoryMenus() {
        List<CategoryMenu> menus = new ArrayList<>();
        String base = "https://www.ximalaya.com/revision/category/v2/albums?pageNum=1&pageSize=30&sort=%d&categoryId=%d";
        menus.add(new CategoryMenu("相声评书", tabs(base, 12, 1, "热门", "最新", "播放最多", "高分")));
        menus.add(new CategoryMenu("有声书", tabs(base, 3, 1, "热门", "最新", "播放最多", "高分")));
        menus.add(new CategoryMenu("历史", tabs(base, 9, 1, "热门", "最新", "播放最多", "高分")));
        menus.add(new CategoryMenu("人文", tabs(base, 1001, 1, "热门", "最新", "播放最多", "高分")));
        menus.add(new CategoryMenu("音乐", tabs(base, 2, 1, "热门", "最新", "播放最多", "高分")));
        return menus;
    }

    private static java.util.List<CategoryTab> tabs(String base, int categoryId, int sort, String... names) {
        java.util.List<CategoryTab> arr = new java.util.ArrayList<>();
        for (String n : names) {
            arr.add(new CategoryTab(n, String.format(base, sort, categoryId)));
        }
        return arr;
    }

}
