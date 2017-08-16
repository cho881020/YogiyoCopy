package kr.co.tjeit.yogiyocopy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import kr.co.tjeit.yogiyocopy.adapter.OrderAdapter;
import kr.co.tjeit.yogiyocopy.adapter.StoreAdapter;
import kr.co.tjeit.yogiyocopy.data.OrderData;
import kr.co.tjeit.yogiyocopy.data.StoreData;

public class MainActivity extends BaseActivity {

    TabHost myTabHost;

    private ListView storeListView;
    List<StoreData> storeDataList = new ArrayList<>();
    StoreAdapter storeAdapter;

    private ListView orderListView;
    List<OrderData> orderDataList = new ArrayList<>();
    OrderAdapter myOrderAdapter;
    private android.widget.TabWidget tabs;
    private android.widget.LinearLayout tab1;
    private android.widget.LinearLayout tab2;
    private android.widget.LinearLayout tab3;
    private android.widget.LinearLayout tab4;
    private android.widget.FrameLayout tabcontent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        setupEvents();
        setValues();

    }


    @Override
    public void setupEvents() {

        storeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, ViewStoreInfoActivity.class);
                intent.putExtra("가게데이터", storeDataList.get(position));
                startActivity(intent);
            }
        });

        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, ViewOrderInfoActivity.class);
                intent.putExtra("주문데이터", orderDataList.get(position));
                startActivity(intent);
            }
        });

    }

    @Override
    public void setValues() {
        makeTabHost();
        addListDatas();

        storeAdapter = new StoreAdapter(MainActivity.this, storeDataList);
        storeListView.setAdapter(storeAdapter);

        myOrderAdapter = new OrderAdapter(MainActivity.this, orderDataList);
        orderListView.setAdapter(myOrderAdapter);

    }

    private void addListDatas() {

        storeDataList.add(new StoreData("https://s3.ap-northeast-2.amazonaws.com/slws3/imgs/tje_practice/kyochon_logo.jpg", "교촌치킨-대학로점", 4.2f, 1200, 2330, 15000, true));
        storeDataList.add(new StoreData("https://s3.ap-northeast-2.amazonaws.com/slws3/imgs/tje_practice/one_logo.jpg", "원할머니보쌈-종로5가점", 3.8f, 1100, 300, 25000, false));
        storeDataList.add(new StoreData("https://www.yogiyo.co.kr/media/restaurant_logos/%EB%96%A1%EB%B3%B6%EC%9D%B4%EC%88%9C%EB%8C%80%EC%84%B8%ED%8A%B801_20131128_FoodAD_crop_200x200_yjnTv3z.jpg", "스쿨스토어-종로점", 4.2f, 1200, 2330, 15000, true));
        storeDataList.add(new StoreData("https://www.yogiyo.co.kr/media/restaurant_logos/%EB%B3%B8%EB%8F%84%EC%8B%9C%EB%9D%BD_20150825_Franchise%EC%9D%B4%EB%AF%B8%EC%A7%80%EC%95%BD%EC%A0%95%EC%84%9C_crop_200x200_zY4cB53.jpg", "본도시락-서울시청점", 3.8f, 1100, 300, 25000, false));
        storeDataList.add(new StoreData("https://www.yogiyo.co.kr/media/restaurant_logos/%EC%89%AC%EB%A6%BC%ED%94%84_%ED%94%BC%EC%9E%9001_20131128_FoodAD_crop_200x200.jpg", "훼미리피자", 4.2f, 1200, 2330, 15000, true));
        storeDataList.add(new StoreData("https://www.yogiyo.co.kr/media/restaurant_logos/%ED%83%95%EC%88%98%EC%9C%A103_20131128_FoodAD_crop_200x200_Rn9zt25.jpg", "남경-남대문시장점", 3.8f, 1100, 300, 25000, false));


        orderDataList.add(new OrderData(storeDataList.get(0),
                Calendar.getInstance(), "종로 3가", 15000));
        orderDataList.add(new OrderData(storeDataList.get(0),
                Calendar.getInstance(), "종로 1가", 10000));
        orderDataList.add(new OrderData(storeDataList.get(0),
                Calendar.getInstance(), "을지로 3가", 30000));

//        원할머니 보쌈 2번, 훼미리피자 1번, 스쿨스토어 2번


        orderDataList.add(new OrderData(storeDataList.get(1),
                Calendar.getInstance(), "종로 3가", 25000));
        orderDataList.add(new OrderData(storeDataList.get(1),
                Calendar.getInstance(), "종로 1가", 15000));
        orderDataList.add(new OrderData(storeDataList.get(4),
                Calendar.getInstance(), "을지로 3가", 33000));
        orderDataList.add(new OrderData(storeDataList.get(2),
                Calendar.getInstance(), "종로 3가", 11000));
        orderDataList.add(new OrderData(storeDataList.get(2),
                Calendar.getInstance(), "종로 1가", 12000));

    }

    private void makeTabHost() {
        TabHost.TabSpec spec1 = myTabHost.newTabSpec("tab1").setIndicator("가게목록");
        spec1.setContent(R.id.tab1);
        myTabHost.addTab(spec1);


        TabHost.TabSpec spec2 = myTabHost.newTabSpec("tab2").setIndicator("주문내역");
        spec2.setContent(R.id.tab2);
        myTabHost.addTab(spec2);

        TabHost.TabSpec spec3 = myTabHost.newTabSpec("tab3").setIndicator("더보기");
        spec3.setContent(R.id.tab3);
        myTabHost.addTab(spec3);

        TabHost.TabSpec spec4 = myTabHost.newTabSpec("tab4").setIndicator("프로필");
        spec4.setContent(R.id.tab4);
        myTabHost.addTab(spec4);
    }


    @Override
    public void bindViews() {
        myTabHost = (TabHost) findViewById(R.id.myTabHost);
        orderListView = (ListView) findViewById(R.id.orderListView);
        storeListView = (ListView) findViewById(R.id.storeListView);


        myTabHost.setup();
    }
}






