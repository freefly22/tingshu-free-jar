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
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: YunTuYouSheng.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J \u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\bH\u0016J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0016J\b\u0010\u0019\u001a\u00020\bH\u0016J\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00172\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\bH\u0016J\b\u0010\u001f\u001a\u00020\bH\u0016J\b\u0010 \u001a\u00020\bH\u0016J\b\u0010!\u001a\u00020\u0011H\u0016J\b\u0010\"\u001a\u00020\u0011H\u0016J\b\u0010#\u001a\u00020$H\u0016J*\u0010%\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020'0\u0017\u0012\u0004\u0012\u00020\u00050&2\u0006\u0010(\u001a\u00020\b2\u0006\u0010)\u001a\u00020\u0005H\u0016R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006*"}, d2 = {"Lcom/github/eprendre/sources_by_eprendre/YunTuYouSheng;", "Lcom/github/eprendre/tingshu/sources/TingShu;", "()V", "pageList", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "wechatID", "", "getWechatID", "()Ljava/lang/String;", "getAudioUrlExtractor", "Lcom/github/eprendre/tingshu/sources/AudioUrlExtractor;", "getBookDetailInfo", "Lcom/github/eprendre/tingshu/utils/BookDetail;", "bookUrl", "loadEpisodes", "", "loadFullPages", "getCategoryList", "Lcom/github/eprendre/tingshu/utils/Category;", "url", "getCategoryMenus", "", "Lcom/github/eprendre/tingshu/utils/CategoryMenu;", "getDesc", "getEpisodes", "Lcom/github/eprendre/tingshu/utils/Episode;", "jsonArray", "Lorg/json/JSONArray;", "getName", "getSourceId", "getUrl", "isMultipleEpisodePages", "isWebViewNotRequired", "reset", "", "search", "Lkotlin/Pair;", "Lcom/github/eprendre/tingshu/utils/Book;", "keywords", "page", "CustomSources"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class YunTuYouSheng extends TingShu {
    public static final YunTuYouSheng INSTANCE = new YunTuYouSheng();
    private static final String wechatID = "07955551-706c-4259-9aa0-db4627dfca57";
    private static final ArrayList<Integer> pageList = new ArrayList<>();

    private YunTuYouSheng() {
    }

    public final String getWechatID() {
        return wechatID;
    }

    public String getSourceId() {
        return "ab0a5474cd6a40e3ba65045addad390a";
    }

    public String getUrl() {
        return "http://yuntuwechat.yuntuys.com/home";
    }

    public String getName() {
        return "云图有声";
    }

    public String getDesc() {
        return "推荐指数:5星 ⭐⭐⭐⭐⭐\n有文化的人听这个😭";
    }

    public boolean isWebViewNotRequired() {
        return true;
    }

    public Pair<List<Book>, Integer> search(String keywords, int page) throws JSONException, UnsupportedEncodingException {
        Intrinsics.checkNotNullParameter(keywords, "keywords");
        String encodedKeywords = URLEncoder.encode(keywords, "utf-8");
        String url = "http://open-service.yuntuys.com/api/w_ys/book/search/wechat:" + wechatID + "/" + encodedKeywords + "?pageSize=20&pageNum=" + page;
        ArrayList list = new ArrayList();
        JSONObject jsonObject = ((FuelJson) ((Result) FuelJsonKt.responseJson(RequestFactory.Convenience.DefaultImpls.get$default(Fuel.INSTANCE, url, (List) null, 2, (Object) null)).getThird()).get()).obj();
        int totalPage = jsonObject.getJSONObject("data").getInt("totalPage");
        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("list");
        Iterable $this$forEach$iv = RangesKt.until(0, jsonArray.length());
        int $i$f$forEach = 0;
        IntIterator it = $this$forEach$iv.iterator();
        while (it.hasNext()) {
            int element$iv = it.nextInt();
            JSONObject item = jsonArray.getJSONObject(element$iv);
            String coverUrl = item.getString("cover");
            String bookUrl = String.valueOf(item.getLong("bookId"));
            String encodedKeywords2 = encodedKeywords;
            String title = item.getString("bookName");
            String url2 = url;
            String artist = item.getString("anchorName");
            String author = item.getString("authorName");
            JSONObject jsonObject2 = jsonObject;
            JSONArray jsonArray2 = jsonArray;
            Iterable $this$forEach$iv2 = $this$forEach$iv;
            String status = "共 " + item.getInt("chapters") + " 章";
            String intro = item.getString("summary");
            Intrinsics.checkNotNullExpressionValue(coverUrl, "coverUrl");
            Intrinsics.checkNotNullExpressionValue(title, "title");
            Intrinsics.checkNotNullExpressionValue(author, "author");
            Intrinsics.checkNotNullExpressionValue(artist, "artist");
            Book $this$search_u24lambda_u241_u24lambda_u240 = new Book(coverUrl, bookUrl, title, author, artist);
            $this$search_u24lambda_u241_u24lambda_u240.setStatus(status);
            Intrinsics.checkNotNullExpressionValue(intro, "intro");
            $this$search_u24lambda_u241_u24lambda_u240.setIntro(intro);
            $this$search_u24lambda_u241_u24lambda_u240.setSourceId(INSTANCE.getSourceId());
            list.add($this$search_u24lambda_u241_u24lambda_u240);
            encodedKeywords = encodedKeywords2;
            url = url2;
            jsonObject = jsonObject2;
            jsonArray = jsonArray2;
            $this$forEach$iv = $this$forEach$iv2;
            $i$f$forEach = $i$f$forEach;
        }
        return new Pair<>(list, Integer.valueOf(totalPage));
    }

    public AudioUrlExtractor getAudioUrlExtractor() {
        return AudioUrlDirectExtractor.INSTANCE;
    }

    public List<CategoryMenu> getCategoryMenus() {
        return CollectionsKt.listOf(new CategoryMenu[]{new CategoryMenu("特色", CollectionsKt.listOf(new CategoryTab[]{new CategoryTab("四史专栏", "585"), new CategoryTab("远读重洋", "571"), new CategoryTab("儿童防疫", "122"), new CategoryTab("听见真知", "124"), new CategoryTab("豆瓣高分", "125"), new CategoryTab("广播剧", "126"), new CategoryTab("影视同期", "127"), new CategoryTab("云图学院", "421")})), new CategoryMenu("经典文学", CollectionsKt.listOf(new CategoryTab[]{new CategoryTab("世界名著", "131"), new CategoryTab("中国文学", "132"), new CategoryTab("国学经典", "134"), new CategoryTab("外国文学", "133"), new CategoryTab("诗词散文", "135"), new CategoryTab("人物传记", "136")})), new CategoryMenu("畅销小说", CollectionsKt.listOf(new CategoryTab[]{new CategoryTab("国风古韵", "137"), new CategoryTab("青春校园", "138"), new CategoryTab("科学幻想", "139"), new CategoryTab("官场商战", "140"), new CategoryTab("军事谍战", "141"), new CategoryTab("悬疑推理", "142"), new CategoryTab("现代都市", "143"), new CategoryTab("怪奇物语", "144"), new CategoryTab("侠义江湖", "335")})), new CategoryMenu("职场财经", CollectionsKt.listOf(new CategoryTab[]{new CategoryTab("创业学院", "148"), new CategoryTab("职场指南", "149"), new CategoryTab("商界大咖", "150"), new CategoryTab("金融理财", "151")})), new CategoryMenu("少儿教育", CollectionsKt.listOf(new CategoryTab[]{new CategoryTab("儿童文学", "152"), new CategoryTab("童话名著", "153"), new CategoryTab("国学启蒙", "154"), new CategoryTab("儿歌故事", "155"), new CategoryTab("百科知识", "156"), new CategoryTab("亲子教育", "157")})), new CategoryMenu("文化艺术", CollectionsKt.listOf(new CategoryTab[]{new CategoryTab("民俗文化", "161"), new CategoryTab("世界之窗", "163"), new CategoryTab("哲学思想", "164")})), new CategoryMenu("历史风云", CollectionsKt.listOf(new CategoryTab[]{new CategoryTab("古代历史", "165"), new CategoryTab("近现代史", "166"), new CategoryTab("世界历史", "167"), new CategoryTab("传奇史话", "168")})), new CategoryMenu("军事文学", CollectionsKt.listOf(new CategoryTab[]{new CategoryTab("军事纪实", "173"), new CategoryTab("战争烽火", "174")})), new CategoryMenu("军政人物", CollectionsKt.listOf(new CategoryTab[]{new CategoryTab("革命先驱", "176"), new CategoryTab("政治领袖", "177")})), new CategoryMenu("健康养生", CollectionsKt.listOf(new CategoryTab[]{new CategoryTab("养生保健", "181"), new CategoryTab("养颜减肥", "182"), new CategoryTab("食疗课堂", "183"), new CategoryTab("孕产育儿", "184")})), new CategoryMenu("情感生活", CollectionsKt.listOf(new CategoryTab[]{new CategoryTab("心理健康", "185"), new CategoryTab("婚恋家庭", "186"), new CategoryTab("心灵励志", "187"), new CategoryTab("生活百科", "188"), new CategoryTab("娱乐休闲", "189")}))});
    }

    public Category getCategoryList(String url) throws JSONException {
        String nextUrl;
        Intrinsics.checkNotNullParameter(url, "url");
        String categoryUrl = StringsKt.contains$default(url, "http", false, 2, (Object) null) ? url : "http://open-service.yuntuys.com/api/w_ys/book/getBookListByType/wechat:" + wechatID + "/" + url + "?pageNum=1&pageSize=20";
        ArrayList list = new ArrayList();
        JSONObject data = ((FuelJson) ((Result) FuelJsonKt.responseJson(RequestFactory.Convenience.DefaultImpls.get$default(Fuel.INSTANCE, categoryUrl, (List) null, 2, (Object) null)).getThird()).get()).obj().getJSONObject("data");
        int currentPage = data.getInt("pageNumber");
        int totalPage = data.getInt("totalPage");
        if (currentPage == totalPage) {
            nextUrl = "";
        } else {
            String typeId = StringsKt.contains$default(url, "http", false, 2, (Object) null) ? (String) StringsKt.split$default(StringsKt.substringAfterLast$default(url, "/", (String) null, 2, (Object) null), new String[]{"?pageNum="}, false, 0, 6, (Object) null).get(0) : url;
            nextUrl = "http://open-service.yuntuys.com/api/w_ys/book/getBookListByType/wechat:" + wechatID + "/" + typeId + "?pageNum=" + (currentPage + 1) + "&pageSize=20";
        }
        JSONArray jsonArray = data.getJSONArray("list");
        Iterable $this$forEach$iv = RangesKt.until(0, jsonArray.length());
        int $i$f$forEach = 0;
        Iterator it = $this$forEach$iv.iterator();
        while (it.hasNext()) {
            int element$iv = ((IntIterator) it).nextInt();
            JSONObject item = jsonArray.getJSONObject(element$iv);
            String coverUrl = item.getString("cover");
            Iterable $this$forEach$iv2 = $this$forEach$iv;
            String bookUrl = String.valueOf(item.getLong("bookId"));
            int $i$f$forEach2 = $i$f$forEach;
            String title = item.getString("bookName");
            Iterator it2 = it;
            String artist = item.getString("anchorName");
            String author = item.getString("authorName");
            JSONArray jsonArray2 = jsonArray;
            String status = "共 " + item.getInt("chapters") + " 章";
            String intro = item.getString("summary");
            Intrinsics.checkNotNullExpressionValue(coverUrl, "coverUrl");
            Intrinsics.checkNotNullExpressionValue(title, "title");
            Intrinsics.checkNotNullExpressionValue(author, "author");
            Intrinsics.checkNotNullExpressionValue(artist, "artist");
            Book $this$getCategoryList_u24lambda_u243_u24lambda_u242 = new Book(coverUrl, bookUrl, title, author, artist);
            $this$getCategoryList_u24lambda_u243_u24lambda_u242.setStatus(status);
            Intrinsics.checkNotNullExpressionValue(intro, "intro");
            $this$getCategoryList_u24lambda_u243_u24lambda_u242.setIntro(intro);
            $this$getCategoryList_u24lambda_u243_u24lambda_u242.setSourceId(INSTANCE.getSourceId());
            list.add($this$getCategoryList_u24lambda_u243_u24lambda_u242);
            $this$forEach$iv = $this$forEach$iv2;
            $i$f$forEach = $i$f$forEach2;
            it = it2;
            jsonArray = jsonArray2;
            categoryUrl = categoryUrl;
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
            String str = "/";
            String url = "http://open-service.yuntuys.com/api/w_ys/book/getChapters/wechat:" + wechatID + "/" + bookId + "/true/asc?pageSize=200&pageNum=1";
            JSONObject pageQuery = ((FuelJson) ((Result) FuelJsonKt.responseJson(RequestFactory.Convenience.DefaultImpls.get$default(Fuel.INSTANCE, url, (List) null, 2, (Object) null)).getThird()).get()).obj().getJSONObject("data").getJSONObject("pageQuery");
            int totalPage = pageQuery.getInt("totalPage");
            JSONArray list = pageQuery.getJSONArray("list");
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
                        String nextUrl = "http://open-service.yuntuys.com/api/w_ys/book/getChapters/wechat:" + wechatID + str + bookId + "/true/asc?pageSize=200&pageNum=" + page;
                        String bookId2 = bookId;
                        JSONArray jsonArray = ((FuelJson) ((Result) FuelJsonKt.responseJson(RequestFactory.Convenience.DefaultImpls.get$default(Fuel.INSTANCE, nextUrl, (List) null, 2, (Object) null)).getThird()).get()).obj().getJSONObject("data").getJSONObject("pageQuery").getJSONArray("list");
                        Intrinsics.checkNotNullExpressionValue(jsonArray, "jsonArray");
                        episodes.addAll(getEpisodes(jsonArray));
                        Thread.sleep(Random.Default.nextLong(100L, 500L));
                        bookId = bookId2;
                        str = str;
                        url = url;
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
            JSONObject item = jsonArray.getJSONObject(element$iv);
            String name = item.getString("name");
            String audioUrl = item.getString("audioUrl");
            Intrinsics.checkNotNullExpressionValue(name, "name");
            Intrinsics.checkNotNullExpressionValue(audioUrl, "audioUrl");
            episodes.add(new Episode(name, audioUrl));
        }
        return episodes;
    }
}
