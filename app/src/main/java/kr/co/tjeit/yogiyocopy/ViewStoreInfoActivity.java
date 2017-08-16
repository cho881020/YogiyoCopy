package kr.co.tjeit.yogiyocopy;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_store_info);
        // 인텐트에 첨부된 데이터를 실제로 받아오는 문장.
        mStoreData = (StoreData) getIntent().getSerializableExtra("가게데이터");
        // 이 화면에 들어올때는 반드시 "가게데이터"라고 메모해서, StoreData 객체를 보내줘야함.
        bindViews();
        setupEvents();
        setValues();
    }


    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

        storeNameTxt.setText(mStoreData.getStoreName());
    }


    @Override
    public void bindViews() {

        this.starImg5 = (ImageView) findViewById(R.id.starImg5);
        this.starImg4 = (ImageView) findViewById(R.id.starImg4);
        this.starImg3 = (ImageView) findViewById(R.id.starImg3);
        this.starImg2 = (ImageView) findViewById(R.id.starImg2);
        this.starImg1 = (ImageView) findViewById(R.id.starImg1);
        this.storeNameTxt = (TextView) findViewById(R.id.storeNameTxt);
    }
}
