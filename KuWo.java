package com.github.eprendre.sources_by_eprendre;

import com.github.eprendre.tingshu.extensions.MyExtKt;
import com.github.eprendre.tingshu.sources.AudioUrlCustomExtractor;
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
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.RangesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: KuWo.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016J \u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0004H\u0016J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\nH\u0016J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0016J\b\u0010\u001a\u001a\u00020\nH\u0016J\b\u0010\u001b\u001a\u00020\nH\u0016J\b\u0010\u001c\u001a\u00020\nH\u0016J\b\u0010\u001d\u001a\u00020\nH\u0016J\b\u0010\u001e\u001a\u00020\u0004H\u0016J\b\u0010\u001f\u001a\u00020\u0004H\u0016J\b\u0010 \u001a\u00020!H\u0016J*\u0010\"\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\u0018\u0012\u0004\u0012\u00020\f0#2\u0006\u0010%\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/github/eprendre/sources_by_eprendre/KuWo;", "Lcom/github/eprendre/tingshu/sources/TingShu;", "()V", "hasMoreEpisodes", "", "fetchEpisodes", "Ljava/util/ArrayList;", "Lcom/github/eprendre/tingshu/utils/Episode;", "Lkotlin/collections/ArrayList;", "albumId", "", "page", "", "getAudioUrlExtractor", "Lcom/github/eprendre/tingshu/sources/AudioUrlExtractor;", "getBookDetailInfo", "Lcom/github/eprendre/tingshu/utils/BookDetail;", "bookUrl", "loadEpisodes", "loadFullPages", "getCategoryList", "Lcom/github/eprendre/tingshu/utils/Category;", "url", "getCategoryMenus", "", "Lcom/github/eprendre/tingshu/utils/CategoryMenu;", "getDesc", "getName", "getSourceId", "getUrl", "isMultipleEpisodePages", "isWebViewNotRequired", "reset", "", "search", "Lkotlin/Pair;", "Lcom/github/eprendre/tingshu/utils/Book;", "keywords", "CustomSources"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class KuWo extends TingShu {
    public static final KuWo INSTANCE = new KuWo();
    private static boolean hasMoreEpisodes;

    private KuWo() {
    }

    public String getSourceId() {
        return "502efedf0613460a9967d9e86ce2b24c";
    }

    public String getUrl() {
        return "http://kuwo.cn/downtingshu";
    }

    public String getName() {
        return "酷我畅听";
    }

    public String getDesc() {
        return "推荐指数:5星 ⭐⭐⭐⭐⭐";
    }

    public Pair<List<Book>, Integer> search(String keywords, int page) throws JSONException, UnsupportedEncodingException {
        Intrinsics.checkNotNullParameter(keywords, "keywords");
        String encodedKeywords = URLEncoder.encode(keywords, "utf-8");
        String url = "http://baby.kuwo.cn/tingshu/api/search/Search?rn=10&type=album&version=8.5.6.1&wd=" + encodedKeywords + "&pn=" + page + "&kweexVersion=1.0.2";
        JSONObject data = ((FuelJson) ((Result) FuelJsonKt.responseJson(RequestFactory.Convenience.DefaultImpls.get$default(Fuel.INSTANCE, url, (List) null, 2, (Object) null)).getThird()).get()).obj().getJSONObject("data");
        int pageCount = (int) Math.ceil(data.getInt("total") / 10);
        ArrayList list = new ArrayList();
        JSONArray iData = data.getJSONArray("data");
        Iterable $this$forEach$iv = RangesKt.until(0, iData.length());
        int $i$f$forEach = 0;
        for (Iterator it = $this$forEach$iv.iterator(); it.hasNext(); it = it) {
            int element$iv = ((IntIterator) it).nextInt();
            JSONObject item = iData.getJSONObject(element$iv);
            int albumId = item.getInt("albumId");
            String coverUrl = item.getString("coverImg");
            String encodedKeywords2 = encodedKeywords;
            String url2 = url;
            String bookUrl = "http://search.kuwo.cn/r.s?stype=albuminfo&loginUid=0&loginSid=null&prod=kwplayer_ar_9.1.7.0&bkprod=kwbook_ar_9.1.7.0&source=kwplayer_ar_9.1.7.0_t18.apk&bksource=kwbook_ar_9.1.7.0_t18.apk&corp=kuwo&albumid=" + albumId + "&pn=0&rn=3000&show_copyright_off=1&vipver=MUSIC_8.2.0.0_BCS17&mobi=1&sortby=3&iskwbook=1";
            String title = item.getString("albumName");
            JSONObject data2 = data;
            String artist = "播音: " + item.getString("artistName");
            Iterable $this$forEach$iv2 = $this$forEach$iv;
            String status = "共 " + item.getInt("songTotal") + " 章";
            String intro = item.getString("title");
            int $i$f$forEach2 = $i$f$forEach;
            Intrinsics.checkNotNullExpressionValue(coverUrl, "coverUrl");
            Intrinsics.checkNotNullExpressionValue(title, "title");
            Book $this$search_u24lambda_u241_u24lambda_u240 = new Book(coverUrl, bookUrl, title, "", artist);
            $this$search_u24lambda_u241_u24lambda_u240.setStatus(status);
            Intrinsics.checkNotNullExpressionValue(intro, "intro");
            $this$search_u24lambda_u241_u24lambda_u240.setIntro(intro);
            $this$search_u24lambda_u241_u24lambda_u240.setSourceId(INSTANCE.getSourceId());
            list.add($this$search_u24lambda_u241_u24lambda_u240);
            encodedKeywords = encodedKeywords2;
            url = url2;
            data = data2;
            iData = iData;
            $this$forEach$iv = $this$forEach$iv2;
            $i$f$forEach = $i$f$forEach2;
        }
        return new Pair<>(list, Integer.valueOf(pageCount));
    }

    public boolean isWebViewNotRequired() {
        return true;
    }

    public AudioUrlExtractor getAudioUrlExtractor() {
        AudioUrlCustomExtractor.INSTANCE.setUp(new Function1<String, String>() { // from class: com.github.eprendre.sources_by_eprendre.KuWo.getAudioUrlExtractor.1
            public final String invoke(String url) throws JSONException {
                Intrinsics.checkNotNullParameter(url, "url");
                MatchResult matchResultFind$default = Regex.find$default(new Regex("https://tsm\\.kuwo\\.cn/anti\\.s\\?useless=/resource/&format=mp3&rid=MUSIC_(\\d+)&response=res&type=convert_url"), url, 0, 2, (Object) null);
                Intrinsics.checkNotNull(matchResultFind$default);
                String rid = (String) matchResultFind$default.getGroupValues().get(1);
                String u = "https://mobi.kuwo.cn/mobi.s?f=web&source=kwplayercar_ar_6.0.0.9_B_jiakong_vh.apk&from=PC&type=convert_url_with_sign&br=128kmp3&rid=" + rid + "&&user=C_APK_guanwang_12609069939969033731";
                String result = ((FuelJson) ((Result) FuelJsonKt.responseJson(RequestFactory.Convenience.DefaultImpls.get$default(Fuel.INSTANCE, u, (List) null, 2, (Object) null).header(new Pair[]{TuplesKt.to("User-Agent", MyExtKt.getDesktopUA())})).getThird()).get()).obj().getJSONObject("data").getString("url");
                Intrinsics.checkNotNullExpressionValue(result, "result");
                return result;
            }
        });
        return AudioUrlCustomExtractor.INSTANCE;
    }

    public List<CategoryMenu> getCategoryMenus() {
        CategoryMenu menu1 = new CategoryMenu("小说", CollectionsKt.listOf(new CategoryTab[]{new CategoryTab("付费排行", "http://baby.kuwo.cn/tingshu/api/page/boutique/getBoutiqueData?pt=1&rn=100&version=8.5.6.1&pn=1&kweexVersion=1.0.2"), new CategoryTab("免费排行", "http://baby.kuwo.cn/tingshu/api/page/boutique/getBoutiqueData?pt=2&rn=100&version=8.5.6.1&pn=1&kweexVersion=1.0.2"), new CategoryTab("都市传说", "http://baby.kuwo.cn/tingshu/api/filter/albums?sortType=tsScore&classifyId=42&rn=20&categoryId=2&pn=1&kweexVersion=1.0.2"), new CategoryTab("玄幻奇幻", "http://baby.kuwo.cn/tingshu/api/filter/albums?sortType=tsScore&classifyId=44&rn=20&categoryId=2&pn=1&kweexVersion=1.0.2"), new CategoryTab("悬疑推理", "http://baby.kuwo.cn/tingshu/api/filter/albums?sortType=tsScore&classifyId=45&rn=20&categoryId=2&pn=1&kweexVersion=1.0.2"), new CategoryTab("现代言情", "http://baby.kuwo.cn/tingshu/api/filter/albums?sortType=tsScore&classifyId=41&rn=20&categoryId=2&pn=1&kweexVersion=1.0.2"), new CategoryTab("武侠仙侠", "http://baby.kuwo.cn/tingshu/api/filter/albums?sortType=tsScore&classifyId=48&rn=20&categoryId=2&pn=1&kweexVersion=1.0.2"), new CategoryTab("穿越架空", "http://baby.kuwo.cn/tingshu/api/filter/albums?sortType=tsScore&classifyId=52&rn=20&categoryId=2&pn=1&kweexVersion=1.0.2"), new CategoryTab("经典小说", "http://baby.kuwo.cn/tingshu/api/filter/albums?sortType=tsScore&classifyId=64&rn=20&categoryId=2&pn=1&kweexVersion=1.0.2"), new CategoryTab("青春校园", "http://baby.kuwo.cn/tingshu/api/filter/albums?sortType=tsScore&classifyId=55&rn=20&categoryId=2&pn=1&kweexVersion=1.0.2"), new CategoryTab("历史军事", "http://baby.kuwo.cn/tingshu/api/filter/albums?sortType=tsScore&classifyId=56&rn=20&categoryId=2&pn=1&kweexVersion=1.0.2"), new CategoryTab("科幻竞技", "http://baby.kuwo.cn/tingshu/api/filter/albums?sortType=tsScore&classifyId=57&rn=20&categoryId=2&pn=1&kweexVersion=1.0.2"), new CategoryTab("古代言情", "http://baby.kuwo.cn/tingshu/api/filter/albums?sortType=tsScore&classifyId=207&rn=20&categoryId=2&pn=1&kweexVersion=1.0.2"), new CategoryTab("能力提升", "http://baby.kuwo.cn/tingshu/api/filter/albums?sortType=tsScore&classifyId=82&rn=20&categoryId=4&pn=1&kweexVersion=1.0.2"), new CategoryTab("人文艺术", "http://baby.kuwo.cn/tingshu/api/filter/albums?sortType=tsScore&classifyId=77&rn=20&categoryId=4&pn=1&kweexVersion=1.0.2"), new CategoryTab("国学文化", "http://baby.kuwo.cn/tingshu/api/filter/albums?sortType=tsScore&classifyId=78&rn=20&categoryId=4&pn=1&kweexVersion=1.0.2"), new CategoryTab("成功法则", "http://baby.kuwo.cn/tingshu/api/filter/albums?sortType=tsScore&classifyId=79&rn=20&categoryId=4&pn=1&kweexVersion=1.0.2"), new CategoryTab("外语精通", "http://baby.kuwo.cn/tingshu/api/filter/albums?sortType=tsScore&classifyId=76&rn=20&categoryId=4&pn=1&kweexVersion=1.0.2"), new CategoryTab("养生健康", "http://baby.kuwo.cn/tingshu/api/filter/albums?sortType=tsScore&classifyId=81&rn=20&categoryId=4&pn=1&kweexVersion=1.0.2"), new CategoryTab("酷我读书", "http://baby.kuwo.cn/tingshu/api/filter/albums?sortType=tsScore&classifyId=211&rn=20&categoryId=4&pn=1&kweexVersion=1.0.2"), new CategoryTab("国学经典", "http://baby.kuwo.cn/tingshu/api/filter/albums?sortType=tsScore&classifyId=117&rn=20&categoryId=9&pn=1&kweexVersion=1.0.2"), new CategoryTab("历史小说", "http://baby.kuwo.cn/tingshu/api/filter/albums?sortType=tsScore&classifyId=181&rn=20&categoryId=9&pn=1&kweexVersion=1.0.2"), new CategoryTab("纪实档案", "http://baby.kuwo.cn/tingshu/api/filter/albums?sortType=tsScore&classifyId=118&rn=20&categoryId=9&pn=1&kweexVersion=1.0.2"), new CategoryTab("历史传奇", "http://baby.kuwo.cn/tingshu/api/filter/albums?sortType=tsScore&classifyId=119&rn=20&categoryId=9&pn=1&kweexVersion=1.0.2"), new CategoryTab("人物传奇", "http://baby.kuwo.cn/tingshu/api/filter/albums?sortType=tsScore&classifyId=120&rn=20&categoryId=9&pn=1&kweexVersion=1.0.2"), new CategoryTab("文化讲堂", "http://baby.kuwo.cn/tingshu/api/filter/albums?sortType=tsScore&classifyId=121&rn=20&categoryId=9&pn=1&kweexVersion=1.0.2"), new CategoryTab("百家讲坛", "http://baby.kuwo.cn/tingshu/api/filter/albums?sortType=tsScore&classifyId=212&rn=20&categoryId=9&pn=1&kweexVersion=1.0.2")}));
        return CollectionsKt.listOf(menu1);
    }

    public Category getCategoryList(String url) throws JSONException {
        int pageCount;
        String nextUrl;
        int currentPage;
        int pageCount2;
        Intrinsics.checkNotNullParameter(url, "url");
        MatchResult matchResultFind$default = Regex.find$default(new Regex("pn=(\\d+)"), url, 0, 2, (Object) null);
        Intrinsics.checkNotNull(matchResultFind$default);
        int currentPage2 = Integer.parseInt((String) matchResultFind$default.getGroupValues().get(1));
        JSONObject data = ((FuelJson) ((Result) FuelJsonKt.responseJson(RequestFactory.Convenience.DefaultImpls.get$default(Fuel.INSTANCE, url, (List) null, 2, (Object) null)).getThird()).get()).obj().getJSONObject("data");
        if (data.has("pageInfo")) {
            int total = data.getJSONObject("pageInfo").getInt("total");
            int pageCount3 = (int) Math.ceil(total / 100);
            pageCount = pageCount3;
        } else {
            int total2 = data.getInt("total");
            int pageCount4 = (int) Math.ceil(total2 / 20);
            pageCount = pageCount4;
        }
        if (currentPage2 < pageCount) {
            nextUrl = new Regex("pn=(\\d+)").replace(url, "pn=" + (currentPage2 + 1));
        } else {
            nextUrl = "";
        }
        ArrayList list = new ArrayList();
        String str = "intro";
        String str2 = "songTotal";
        String str3 = "http://search.kuwo.cn/r.s?stype=albuminfo&loginUid=0&loginSid=null&prod=kwplayer_ar_9.1.7.0&bkprod=kwbook_ar_9.1.7.0&source=kwplayer_ar_9.1.7.0_t18.apk&bksource=kwbook_ar_9.1.7.0_t18.apk&corp=kuwo&albumid=";
        String str4 = "albumId";
        String nextUrl2 = nextUrl;
        if (data.has("topDatas")) {
            JSONArray topDatas = data.getJSONArray("topDatas");
            Iterable $this$forEach$iv = RangesKt.until(0, topDatas.length());
            int $i$f$forEach = 0;
            IntIterator it = $this$forEach$iv.iterator();
            while (it.hasNext()) {
                int element$iv = it.nextInt();
                Iterable $this$forEach$iv2 = $this$forEach$iv;
                int i = $i$f$forEach;
                JSONArray topDatas2 = topDatas;
                JSONObject item = topDatas.getJSONObject(element$iv).getJSONObject("albums");
                int albumId = item.getInt("albumId");
                String coverUrl = item.getString("img");
                int pageCount5 = pageCount;
                String bookUrl = "http://search.kuwo.cn/r.s?stype=albuminfo&loginUid=0&loginSid=null&prod=kwplayer_ar_9.1.7.0&bkprod=kwbook_ar_9.1.7.0&source=kwplayer_ar_9.1.7.0_t18.apk&bksource=kwbook_ar_9.1.7.0_t18.apk&corp=kuwo&albumid=" + albumId + "&pn=0&rn=3000&show_copyright_off=1&vipver=MUSIC_8.2.0.0_BCS17&mobi=1&sortby=3&iskwbook=1";
                String title = item.getString("name");
                int currentPage3 = currentPage2;
                String str5 = str2;
                String status = "共 " + item.getInt(str2) + " 章";
                String intro = item.getString("title");
                Intrinsics.checkNotNullExpressionValue(coverUrl, "coverUrl");
                Intrinsics.checkNotNullExpressionValue(title, "title");
                Book $this$getCategoryList_u24lambda_u243_u24lambda_u242 = new Book(coverUrl, bookUrl, title, "", "");
                $this$getCategoryList_u24lambda_u243_u24lambda_u242.setStatus(status);
                Intrinsics.checkNotNullExpressionValue(intro, "intro");
                $this$getCategoryList_u24lambda_u243_u24lambda_u242.setIntro(intro);
                $this$getCategoryList_u24lambda_u243_u24lambda_u242.setSourceId(INSTANCE.getSourceId());
                list.add($this$getCategoryList_u24lambda_u243_u24lambda_u242);
                $i$f$forEach = i;
                $this$forEach$iv = $this$forEach$iv2;
                topDatas = topDatas2;
                pageCount = pageCount5;
                currentPage2 = currentPage3;
                str2 = str5;
            }
            currentPage = currentPage2;
            pageCount2 = pageCount;
        } else {
            currentPage = currentPage2;
            pageCount2 = pageCount;
            JSONArray iData = data.getJSONArray("data");
            Iterable $this$forEach$iv3 = RangesKt.until(0, iData.length());
            int $i$f$forEach2 = 0;
            Iterator it2 = $this$forEach$iv3.iterator();
            while (it2.hasNext()) {
                int element$iv2 = ((IntIterator) it2).nextInt();
                Iterable $this$forEach$iv4 = $this$forEach$iv3;
                JSONObject item2 = iData.getJSONObject(element$iv2);
                JSONArray iData2 = iData;
                int albumId2 = item2.getInt(str4);
                int $i$f$forEach3 = $i$f$forEach2;
                String coverUrl2 = item2.getString("coverImg");
                String str6 = str4;
                String bookUrl2 = str3 + albumId2 + "&pn=0&rn=3000&show_copyright_off=1&vipver=MUSIC_8.2.0.0_BCS17&mobi=1&sortby=3&iskwbook=1";
                String title2 = item2.getString("albumName");
                String str7 = str3;
                Iterator it3 = it2;
                JSONObject data2 = data;
                String artist = "播音: " + item2.getString("artistName");
                String status2 = "共 " + item2.getInt("songTotal") + " 章";
                String intro2 = item2.getString("title");
                Intrinsics.checkNotNullExpressionValue(coverUrl2, "coverUrl");
                Intrinsics.checkNotNullExpressionValue(title2, "title");
                Book $this$getCategoryList_u24lambda_u245_u24lambda_u244 = new Book(coverUrl2, bookUrl2, title2, "", artist);
                $this$getCategoryList_u24lambda_u245_u24lambda_u244.setStatus(status2);
                Intrinsics.checkNotNullExpressionValue(intro2, str);
                $this$getCategoryList_u24lambda_u245_u24lambda_u244.setIntro(intro2);
                $this$getCategoryList_u24lambda_u245_u24lambda_u244.setSourceId(INSTANCE.getSourceId());
                list.add($this$getCategoryList_u24lambda_u245_u24lambda_u244);
                $this$forEach$iv3 = $this$forEach$iv4;
                iData = iData2;
                $i$f$forEach2 = $i$f$forEach3;
                str = str;
                str4 = str6;
                str3 = str7;
                it2 = it3;
                data = data2;
            }
        }
        return new Category(list, currentPage, pageCount2, url, nextUrl2);
    }

    public boolean isMultipleEpisodePages() {
        return true;
    }

    public void reset() {
        hasMoreEpisodes = false;
    }

    public BookDetail getBookDetailInfo(String bookUrl, boolean loadEpisodes, boolean loadFullPages) throws JSONException, InterruptedException {
        List groupValues;
        Intrinsics.checkNotNullParameter(bookUrl, "bookUrl");
        ArrayList episodes = new ArrayList();
        if (loadEpisodes) {
            MatchResult matchResultFind$default = Regex.find$default(new Regex(".+albumid=(\\d+)&pn=.+"), bookUrl, 0, 2, (Object) null);
            String albumId = (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null) ? null : (String) groupValues.get(1);
            if (albumId != null) {
                episodes.addAll(fetchEpisodes(albumId, 0));
                int page = 0 + 1;
                if (loadFullPages) {
                    hasMoreEpisodes = true;
                    while (hasMoreEpisodes) {
                        Thread.sleep(Random.Default.nextLong(200L, 600L));
                        ArrayList<Episode> arrayListFetchEpisodes = fetchEpisodes(albumId, page);
                        if (arrayListFetchEpisodes.isEmpty()) {
                            MyExtKt.notifyLoadingEpisodes((String) null);
                            hasMoreEpisodes = false;
                        } else {
                            page++;
                            MyExtKt.notifyLoadingEpisodes(String.valueOf(page));
                            episodes.addAll(arrayListFetchEpisodes);
                        }
                    }
                }
            }
        }
        return new BookDetail(episodes, (String) null, (String) null, (String) null, 0, (String) null, 62, (DefaultConstructorMarker) null);
    }

    private final ArrayList<Episode> fetchEpisodes(String albumId, int page) throws JSONException {
        ArrayList<Episode> arrayList = new ArrayList<>();
        String url = "http://search.kuwo.cn/r.s?stype=albuminfo&loginUid=0&loginSid=null&prod=kwplayer_ar_9.1.7.0&bkprod=kwbook_ar_9.1.7.0&source=kwplayer_ar_9.1.7.0_t18.apk&bksource=kwbook_ar_9.1.7.0_t18.apk&corp=kuwo&albumid=" + albumId + "&pn=" + page + "&rn=200&show_copyright_off=1&vipver=MUSIC_8.2.0.0_BCS17&mobi=1&sortby=3&iskwbook=1";
        JSONArray data = ((FuelJson) ((Result) FuelJsonKt.responseJson(RequestFactory.Convenience.DefaultImpls.get$default(Fuel.INSTANCE, url, (List) null, 2, (Object) null)).getThird()).get()).obj().getJSONArray("musiclist");
        Iterable $this$forEach$iv = RangesKt.until(0, data.length());
        IntIterator it = $this$forEach$iv.iterator();
        while (it.hasNext()) {
            int element$iv = it.nextInt();
            JSONObject item = data.getJSONObject(element$iv);
            String name = item.getString("name");
            String musicrid = item.getString("musicrid");
            String bookUrl = url;
            Intrinsics.checkNotNullExpressionValue(name, "name");
            arrayList.add(new Episode(name, "https://tsm.kuwo.cn/anti.s?useless=/resource/&format=mp3&rid=MUSIC_" + musicrid + "&response=res&type=convert_url"));
            url = bookUrl;
        }
        return arrayList;
    }
}
