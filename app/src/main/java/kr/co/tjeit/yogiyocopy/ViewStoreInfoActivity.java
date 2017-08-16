package kr.co.tjeit.yogiyocopy;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import kr.co.tjeit.yogiyocopy.data.StoreData;

public class ViewStoreInfoActivity extends BaseActivity {

    // 멤버변수 선언 공간
    StoreData mStoreData = null;
    private android.widget.TextView storeNameTxt;
    private android.widget.ImageView starImg1;
    private android.widget.ImageView starImg2;
    private android.widget.ImageView starImg3;
    private android.widget.ImageView starImg4;
    private android.widget.ImageView starImg5;
    private ImageView storeProfileImgView;
    private TextView avgRatingTxt;
    private TextView minDeliveryCostTxt;
    private android.widget.TabWidget tabs;
    private android.widget.LinearLayout tab1;
    private android.widget.LinearLayout tab2;
    private android.widget.LinearLayout tab3;
    private android.widget.FrameLayout tabcontent;
    private android.widget.TabHost storeTabHost;
    private TextView openAndCloseTimeTxt;
    private ImageView isCescoImg;
    private TextView isCescoTxt;
    private TextView minDeliveryCostInTabTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_store_info);
        // 인텐트에 첨부된 데이터를 실제로 받아오는 문장.
        mStoreData = (StoreData) getIntent().getSerializableExtra("가게데이터");
        // 이 화면에 들어올때는 반드시 "가게데이터"라고 메모해서, StoreData 객체를 보내줘야함.
        bindViews();
        makeTabHost();
        setupEvents();
        setValues();
    }

    private void makeTabHost() {
        // TabHost를 사용하고 싶을땐 반드시 setup메쏘드부터 실행하자.
        storeTabHost.setup();

        // TabHost에 사용될 TabSpec들을 만들어준다.

        TabHost.TabSpec spec1 = storeTabHost.newTabSpec("tab1").setIndicator("메뉴");
        spec1.setContent(R.id.tab1);
        storeTabHost.addTab(spec1);


        TabHost.TabSpec spec2 = storeTabHost.newTabSpec("tab2").setIndicator("리뷰");
        spec2.setContent(R.id.tab2);
        storeTabHost.addTab(spec2);

        TabHost.TabSpec spec3 = storeTabHost.newTabSpec("tab3").setIndicator("정보");
        spec3.setContent(R.id.tab3);
        storeTabHost.addTab(spec3);

    }


    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

        storeNameTxt.setText(mStoreData.getStoreName());

        String minDeliveryCostStr = String.format(Locale.KOREA, "%,d원", mStoreData.getMinCost());
        minDeliveryCostTxt.setText(minDeliveryCostStr);

//        avgRatingTxt.setText(mStoreData.getAvgRating()+"");

        // 소수점도 String.format을 이용해 표기
        // %f 기본 소수. => %.?f => ?들어간 숫자만큼 소수점 자리수 표기
        String avgRatingStr = String.format(Locale.KOREA, "%.1f", mStoreData.getAvgRating());
        avgRatingTxt.setText(avgRatingStr);

        // 평점에 맞는 별 표시 부분

        // 별 다섯개를 리스트로 저장해두는 부분.
        // 왜 리스트? 0~마지막까지 돌면서 하나하나 별모양을 바꿔주려고 하는데,
        // 이를 지원하기에 제일 편한 자료구조가 List라서.
        List<ImageView> stars = new ArrayList<>();
        stars.add(starImg1);
        stars.add(starImg2);
        stars.add(starImg3);
        stars.add(starImg4);
        stars.add(starImg5);

        // 모든 별들을 빼내서 회색으로 바꿔주는 반복문.
        // "모든" 걸 빼낼땐, for문의 다른형태로 하는게 편함.
        for (ImageView iv : stars) {
            iv.setImageResource(R.drawable.gray_star);
        }

        // 몇개의 별이 노랗게 칠해져야 하는지 구하는 부분.
        // 단순히 소수점 자리를 버리는것으로 대처.
        // 소수점자리를 버리는 제일 간단한 방법은 int로 캐스팅.
        // for문을 돌때도 int를 쓰는게 편하니까 int로 캐스팅.
        int rating = (int) mStoreData.getAvgRating();


        // 구해진 노랗게  칠할 별의 갯수만큼
        // 실제로 별을 칠해주는 부분.
        // 노란별은 끝까지 칠해지지 않는 경우도 있음.
        // 그러므로 전통적인 int i를 이용한 for문을 돌림.
        for (int i = 0; i < rating; i++) {
            stars.get(i).setImageResource(R.drawable.fill_star);
        }


        // 가게의 프로필 사진을 표시하는 부분
        Glide.with(mContext).load(mStoreData.getImagePath()).into(storeProfileImgView);

        // with => 어디서 글라이드를 쓸건지
        // load => 어떤 이미지를 불러올건지 (경로)
        // into => 어느 이미지뷰에다가 집어넣을건지

    }


    @Override
    public void bindViews() {
        this.storeTabHost = (TabHost) findViewById(R.id.storeTabHost);
        this.tabcontent = (FrameLayout) findViewById(android.R.id.tabcontent);
        this.tab3 = (LinearLayout) findViewById(R.id.tab3);
        this.minDeliveryCostInTabTxt = (TextView) findViewById(R.id.minDeliveryCostInTabTxt);
        this.isCescoTxt = (TextView) findViewById(R.id.isCescoTxt);
        this.isCescoImg = (ImageView) findViewById(R.id.isCescoImg);
        this.openAndCloseTimeTxt = (TextView) findViewById(R.id.openAndCloseTimeTxt);
        this.tab2 = (LinearLayout) findViewById(R.id.tab2);
        this.tab1 = (LinearLayout) findViewById(R.id.tab1);
        this.tabs = (TabWidget) findViewById(android.R.id.tabs);
        this.minDeliveryCostTxt = (TextView) findViewById(R.id.minDeliveryCostTxt);
        this.avgRatingTxt = (TextView) findViewById(R.id.avgRatingTxt);
        this.starImg5 = (ImageView) findViewById(R.id.starImg5);
        this.starImg4 = (ImageView) findViewById(R.id.starImg4);
        this.starImg3 = (ImageView) findViewById(R.id.starImg3);
        this.starImg2 = (ImageView) findViewById(R.id.starImg2);
        this.starImg1 = (ImageView) findViewById(R.id.starImg1);
        this.storeProfileImgView = (ImageView) findViewById(R.id.storeProfileImgView);
        this.storeNameTxt = (TextView) findViewById(R.id.storeNameTxt);
    }
}
