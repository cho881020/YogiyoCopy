package kr.co.tjeit.yogiyocopy;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

import kr.co.tjeit.yogiyocopy.data.OrderData;

public class ViewOrderInfoActivity extends BaseActivity {

    OrderData mOrderData = null;

    private android.widget.TextView storeNameTxt;
    private android.widget.TextView areaTxt;
    private android.widget.TextView costTxt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_info);
        mOrderData = (OrderData) getIntent().getSerializableExtra("주문데이터");
        bindViews();
        setupEvents();
        setValues();
    }



    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {
        storeNameTxt.setText(mOrderData.getOrderStore().getStoreName());
        areaTxt.setText(mOrderData.getLocation());
        String costStr = String.format(Locale.KOREA, "%,d원", mOrderData.getCost());
        costTxt.setText(costStr);
    }

    @Override
    public void bindViews() {

        this.costTxt = (TextView) findViewById(R.id.costTxt);
        this.areaTxt = (TextView) findViewById(R.id.areaTxt);
        this.storeNameTxt = (TextView) findViewById(R.id.storeNameTxt);
    }
}
