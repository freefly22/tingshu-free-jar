package com.github.eprendre.sources_by_eprendre;

import com.github.eprendre.tingshu.extensions.MyExtKt;
import com.github.eprendre.tingshu.sources.AudioUrlDirectExtractor;
import com.github.eprendre.tingshu.sources.AudioUrlExtractor;
import com.github.eprendre.tingshu.sources.TingShu;
import com.github.eprendre.tingshu.utils.Book;
import com.github.eprendre.tingshu.utils.BookDetail;
import com.github.eprendre.tingshu.utils.Category;
import com.github.eprendre.tingshu.utils.CategoryMenu;
import com.github.eprendre.tingshu.utils.CategoryTab;
import com.github.eprendre.tingshu.utils.Episode;
import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.RequestFactory;
import com.github.kittinunf.fuel.json.FuelJson;
import com.github.kittinunf.fuel.json.FuelJsonKt;
import com.github.kittinunf.result.Result;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: BoKanYouSheng.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J \u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0004H\u0016J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0016J\b\u0010\u0019\u001a\u00020\u0004H\u0016J\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00172\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u0004H\u0016J\b\u0010\u001f\u001a\u00020\u0004H\u0016J\b\u0010 \u001a\u00020\u0004H\u0016J\b\u0010!\u001a\u00020\u0011H\u0016J\b\u0010\"\u001a\u00020\u0011H\u0016J\b\u0010#\u001a\u00020$H\u0016J*\u0010%\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020'0\u0017\u0012\u0004\u0012\u00020\t0&2\u0006\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\tH\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/github/eprendre/sources_by_eprendre/BoKanYouSheng;", "Lcom/github/eprendre/tingshu/sources/TingShu;", "()V", "instance_id", "", "getInstance_id", "()Ljava/lang/String;", "pageList", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getAudioUrlExtractor", "Lcom/github/eprendre/tingshu/sources/AudioUrlExtractor;", "getBookDetailInfo", "Lcom/github/eprendre/tingshu/utils/BookDetail;", "bookUrl", "loadEpisodes", "", "loadFullPages", "getCategoryList", "Lcom/github/eprendre/tingshu/utils/Category;", "url", "getCategoryMenus", "", "Lcom/github/eprendre/tingshu/utils/CategoryMenu;", "getDesc", "getEpisodes", "Lcom/github/eprendre/tingshu/utils/Episode;", "jsonArray", "Lorg/json/JSONArray;", "getName", "getSourceId", "getUrl", "isMultipleEpisodePages", "isWebViewNotRequired", "reset", "", "search", "Lkotlin/Pair;", "Lcom/github/eprendre/tingshu/utils/Book;", "keywords", "page", "CustomSources"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BoKanYouSheng extends TingShu {
    public static final BoKanYouSheng INSTANCE = new BoKanYouSheng();
    private static final String instance_id = "25304";
    private static final ArrayList<Integer> pageList = new ArrayList<>();

    private BoKanYouSheng() {
    }

    public final String getInstance_id() {
        return instance_id;
    }

    public String getSourceId() {
        return "c98a21452583434da5cfef8be16b71d6";
    }

    public String getUrl() {
        return "https://voicewk.bookan.com.cn/25303/index";
    }

    public String getName() {
        return "博看有声";
    }

    public String getDesc() {
        return "推荐指数:5星 ⭐⭐⭐⭐⭐\n有文化的人听这个😭";
    }

    public boolean isWebViewNotRequired() {
        return true;
    }

    public Pair<List<Book>, Integer> search(String keywords, int page) throws UnsupportedEncodingException {
        Intrinsics.checkNotNullParameter(keywords, "keywords");
        String encodedKeywords = URLEncoder.encode(keywords, "utf-8");
        int i = 2;
        String str = instance_id;
        int i2 = 0;
        Iterable listUrl = CollectionsKt.listOf(new String[]{"https://es.bookan.com.cn/api/v3/voice/book?instanceId=" + str + "&keyword=" + encodedKeywords + "&pageNum=1&limitNum=20", "https://es.bookan.com.cn/api/v3/voice/album?instanceId=" + str + "&keyword=" + encodedKeywords + "&pageNum=1&limitNum=20"});
        ArrayList list = new ArrayList();
        Iterable $this$forEach$iv = listUrl;
        for (Object element$iv : $this$forEach$iv) {
            String url = (String) element$iv;
            try {
                JSONObject jsonObject = ((FuelJson) ((Result) FuelJsonKt.responseJson(RequestFactory.Convenience.DefaultImpls.get$default(Fuel.INSTANCE, url, (List) null, i, (Object) null)).getThird()).get()).obj().getJSONObject("data");
                jsonObject.getInt("last_page");
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                Iterable $this$forEach$iv2 = RangesKt.until(i2, jsonArray.length());
                IntIterator it = $this$forEach$iv2.iterator();
                while (it.hasNext()) {
                    int i3 = it.nextInt();
                    JSONObject item = jsonArray.getJSONObject(i3);
                    String coverUrl = item.getString("cover");
                    String bookUrl = String.valueOf(item.getLong("id"));
                    String title = item.getString("name");
                    JSONObject jsonObject2 = jsonObject;
                    Intrinsics.checkNotNullExpressionValue(coverUrl, "coverUrl");
                    Intrinsics.checkNotNullExpressionValue(title, "title");
                    JSONArray jsonArray2 = jsonArray;
                    String url2 = url;
                    try {
                        Book $this$search_u24lambda_u242_u24lambda_u241_u24lambda_u240 = new Book(coverUrl, bookUrl, title, "", "");
                        $this$search_u24lambda_u242_u24lambda_u241_u24lambda_u240.setSourceId(INSTANCE.getSourceId());
                        list.add($this$search_u24lambda_u242_u24lambda_u241_u24lambda_u240);
                        jsonObject = jsonObject2;
                        jsonArray = jsonArray2;
                        url = url2;
                    } catch (Exception e) {
                        e = e;
                        e.printStackTrace();
                        i = 2;
                        i2 = 0;
                    }
                }
            } catch (Exception e2) {
                e = e2;
            }
            i = 2;
            i2 = 0;
        }
        return new Pair<>(list, 1);
    }

    public AudioUrlExtractor getAudioUrlExtractor() {
        return AudioUrlDirectExtractor.INSTANCE;
    }

    public List<CategoryMenu> getCategoryMenus() {
        return CollectionsKt.listOf(new CategoryMenu[]{new CategoryMenu("图书", CollectionsKt.listOf(new CategoryTab[]{new CategoryTab("经典必读", "book::1314"), new CategoryTab("国学经典", "book::1320"), new CategoryTab("文学文艺", "book::1306"), new CategoryTab("少年读物", "book::1305"), new CategoryTab("儿童文学", "book::1304"), new CategoryTab("心理哲学", "book::1310"), new CategoryTab("育儿心经", "book::1309"), new CategoryTab("家庭健康", "book::1311"), new CategoryTab("青春励志", "book::1307"), new CategoryTab("历史小说", "book::1312"), new CategoryTab("商业财经", "book::1315"), new CategoryTab("科技科普", "book::1313"), new CategoryTab("故事会", "book::1303"), new CategoryTab("红色岁月", "book::1316"), new CategoryTab("社会观察", "book::1318"), new CategoryTab("音乐戏曲", "book::1317"), new CategoryTab("相声评书", "book::1319")})), new CategoryMenu("专辑", CollectionsKt.listOf(new CategoryTab[]{new CategoryTab("健康养生", "album::4"), new CategoryTab("休闲娱乐", "album::5"), new CategoryTab("财经科技", "album::6"), new CategoryTab("广播节目", "album::7"), new CategoryTab("人文社科", "album::8"), new CategoryTab("少儿学堂", "album::9"), new CategoryTab("文史军事", "album::10"), new CategoryTab("投资理财", "album::11"), new CategoryTab("亲子教育", "album::12"), new CategoryTab("时尚生活", "album::13"), new CategoryTab("汽车知识", "album::14"), new CategoryTab("发展创业", "album::15"), new CategoryTab("婚恋情感", "album::16"), new CategoryTab("自我提升", "album::17"), new CategoryTab("商业资讯", "album::18"), new CategoryTab("新闻热点", "album::19")}))});
    }

    public Category getCategoryList(String url) throws JSONException {
        String type;
        String categoryId;
        String str;
        Intrinsics.checkNotNullParameter(url, "url");
        if (StringsKt.contains$default(url, "::", false, 2, (Object) null)) {
            List array = StringsKt.split$default(url, new String[]{"::"}, false, 0, 6, (Object) null);
            String type2 = (String) array.get(0);
            String categoryId2 = (String) array.get(1);
            str = "https://api.bookan.com.cn/voice/" + type2 + "/list?instance_id=" + instance_id + "&page=1&category_id=" + categoryId2 + "&num=24";
            type = type2;
            categoryId = categoryId2;
        } else {
            MatchResult matchResultFind$default = Regex.find$default(new Regex("voice/(.+)/list"), url, 0, 2, (Object) null);
            Intrinsics.checkNotNull(matchResultFind$default);
            String type3 = (String) matchResultFind$default.getGroupValues().get(1);
            MatchResult matchResultFind$default2 = Regex.find$default(new Regex("category_id=(.+)&num"), url, 0, 2, (Object) null);
            Intrinsics.checkNotNull(matchResultFind$default2);
            String categoryId3 = (String) matchResultFind$default2.getGroupValues().get(1);
            type = type3;
            categoryId = categoryId3;
            str = url;
        }
        String _url = str;
        JSONObject data = ((FuelJson) ((Result) FuelJsonKt.responseJson(RequestFactory.Convenience.DefaultImpls.get$default(Fuel.INSTANCE, _url, (List) null, 2, (Object) null)).getThird()).get()).obj().getJSONObject("data");
        int currentPage = data.getInt("current_page");
        int totalPage = data.getInt("last_page");
        String nextUrl = "https://api.bookan.com.cn/voice/" + type + "/list?instance_id=" + instance_id + "&page=" + (currentPage + 1) + "&category_id=" + categoryId + "&num=24";
        ArrayList list = new ArrayList();
        JSONArray jsonArray = data.getJSONArray("list");
        Iterable $this$forEach$iv = RangesKt.until(0, jsonArray.length());
        int $i$f$forEach = 0;
        Iterator it = $this$forEach$iv.iterator();
        while (it.hasNext()) {
            int element$iv = ((IntIterator) it).nextInt();
            Iterable $this$forEach$iv2 = $this$forEach$iv;
            JSONObject item = jsonArray.getJSONObject(element$iv);
            int $i$f$forEach2 = $i$f$forEach;
            String coverUrl = item.getString("cover");
            Iterator it2 = it;
            String bookUrl = String.valueOf(item.getLong("id"));
            String title = item.getString("name");
            String status = "共 " + item.getInt("total") + " 章";
            Intrinsics.checkNotNullExpressionValue(coverUrl, "coverUrl");
            Intrinsics.checkNotNullExpressionValue(title, "title");
            Book $this$getCategoryList_u24lambda_u244_u24lambda_u243 = new Book(coverUrl, bookUrl, title, "", "");
            $this$getCategoryList_u24lambda_u244_u24lambda_u243.setStatus(status);
            String status2 = INSTANCE.getSourceId();
            $this$getCategoryList_u24lambda_u244_u24lambda_u243.setSourceId(status2);
            list.add($this$getCategoryList_u24lambda_u244_u24lambda_u243);
            $this$forEach$iv = $this$forEach$iv2;
            $i$f$forEach = $i$f$forEach2;
            it = it2;
        }
        return new Category(list, currentPage, totalPage, url, nextUrl);
    }

    public boolean isMultipleEpisodePages() {
        return true;
    }

    public void reset() {
        pageList.clear();
    }

    public BookDetail getBookDetailInfo(String bookUrl, boolean loadEpisodes, boolean loadFullPages) throws JSONException, InterruptedException {
        Intrinsics.checkNotNullParameter(bookUrl, "bookUrl");
        ArrayList episodes = new ArrayList();
        if (loadEpisodes) {
            String bookId = bookUrl;
            String str = "https://api.bookan.com.cn/voice/album/units?album_id=";
            String url = "https://api.bookan.com.cn/voice/album/units?album_id=" + bookId + "&page=1&num=20&order=1";
            int i = 2;
            JSONObject data = ((FuelJson) ((Result) FuelJsonKt.responseJson(RequestFactory.Convenience.DefaultImpls.get$default(Fuel.INSTANCE, url, (List) null, 2, (Object) null)).getThird()).get()).obj().getJSONObject("data");
            int totalPage = data.getInt("last_page");
            JSONArray list = data.getJSONArray("list");
            Intrinsics.checkNotNullExpressionValue(list, "list");
            episodes.addAll(getEpisodes(list));
            if (loadFullPages) {
                if (totalPage > 1) {
                    CollectionsKt.addAll(pageList, new IntRange(2, totalPage));
                    while (true) {
                        ArrayList<Integer> arrayList = pageList;
                        if (arrayList.size() <= 0) {
                            break;
                        }
                        Integer numRemove = arrayList.remove(0);
                        Intrinsics.checkNotNullExpressionValue(numRemove, "pageList.removeAt(0)");
                        int page = numRemove.intValue();
                        MyExtKt.notifyLoadingEpisodes(page + " / " + totalPage);
                        String nextUrl = str + bookId + "&page=" + page + "&num=20&order=1";
                        JSONArray jsonArray = ((FuelJson) ((Result) FuelJsonKt.responseJson(RequestFactory.Convenience.DefaultImpls.get$default(Fuel.INSTANCE, nextUrl, (List) null, i, (Object) null)).getThird()).get()).obj().getJSONObject("data").getJSONArray("list");
                        Intrinsics.checkNotNullExpressionValue(jsonArray, "jsonArray");
                        episodes.addAll(getEpisodes(jsonArray));
                        Thread.sleep(Random.Default.nextLong(100L, 500L));
                        bookId = bookId;
                        str = str;
                        url = url;
                        i = 2;
                    }
                }
                MyExtKt.notifyLoadingEpisodes((String) null);
            }
        }
        return new BookDetail(episodes, (String) null, (String) null, (String) null, 0, (String) null, 62, (DefaultConstructorMarker) null);
    }

    private final List<Episode> getEpisodes(JSONArray jsonArray) throws JSONException {
        ArrayList episodes = new ArrayList();
        Iterable $this$forEach$iv = RangesKt.until(0, jsonArray.length());
        IntIterator it = $this$forEach$iv.iterator();
        while (it.hasNext()) {
            int element$iv = it.nextInt();
            JSONObject obj = jsonArray.getJSONObject(element$iv);
            String title = obj.getString("title");
            String file = obj.getString("file");
            Intrinsics.checkNotNullExpressionValue(title, "title");
            Intrinsics.checkNotNullExpressionValue(file, "file");
            episodes.add(new Episode(title, file));
        }
        return episodes;
    }
}
