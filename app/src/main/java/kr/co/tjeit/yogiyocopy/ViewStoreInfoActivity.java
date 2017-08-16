package kr.co.tjeit.yogiyocopy;

import android.os.Bundle;
import android.view.View;
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
    private TextView corpNameTxt;
    private TextView corpNumTxt;

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

        // 정보 탭에 최소 배달 금액을 설정하는 부분.
        // setValue 상단에 이미 최소배달금액을 표시하는 부분이 있다.
        // 그 부분에서 String 변수로 만들어서 내용을 표시하고 있으므로
        // 그 변수를 그대로 setText하는걸로 마무리.
        minDeliveryCostInTabTxt.setText(minDeliveryCostStr);

        // mStoreData가 가진 openTime과 closeTime을 참조하여
        // 여는 시간:분 ~ 닫는 시간:분 을 표기하는 부분.

        // 시간 : /100으로 계산, 분 : %100으로 계산. => 4개의 변수 생성.
        // 여는시간 , 여는 분, 닫는시간, 닫는 분
        int openHour = mStoreData.getOpenTime() / 100; // openTime : 1220 => 시간? 12 분? 20
        int openMinute = mStoreData.getOpenTime() % 100;
        int closeHour = mStoreData.getCloseTime() / 100;
        int closeMinute = mStoreData.getCloseTime() % 100;

        // 열고닫는시간과분을, 무조건 두자리 정수로 표현하여 - 으로 연결하는 format
        // 일반적으로 정수를 표현하고싶을땐 %d. 그런데 자리수를 두자리로 하기 위해 %02d로 변경.
        // %02d => 정수를 무조건 두자리 (5=>05)
        // 기본형 : %d 정수, %f 소수.
        // cf. %.1f => f는 소수.  .1 => 소수점 뒤에 1자리만.
        // 02 => 0이 2개. .1 => .뒤에1개
        // 9:00 => 09:00
        String openCloseStr = String.format(Locale.KOREA, "%02d:%02d - %02d:%02d", openHour, openMinute, closeHour, closeMinute);
        openAndCloseTimeTxt.setText(openCloseStr);

        // 세스코 가입 여부 표시
        if (mStoreData.isCesco()) {
            isCescoImg.setVisibility(View.VISIBLE);
            isCescoTxt.setText("세스코멤버스 사업장");
        } else {
            isCescoImg.setVisibility(View.GONE);
            isCescoTxt.setText("-");
        }

        corpNameTxt.setText(mStoreData.getCorpName());
        corpNumTxt.setText(mStoreData.getCorpNumber());

    }


    @Override
    public void bindViews() {
        this.storeTabHost = (TabHost) findViewById(R.id.storeTabHost);
        this.tabcontent = (FrameLayout) findViewById(android.R.id.tabcontent);
        this.tab3 = (LinearLayout) findViewById(R.id.tab3);
        this.corpNumTxt = (TextView) findViewById(R.id.corpNumTxt);
        this.corpNameTxt = (TextView) findViewById(R.id.coprNameTxt);
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
