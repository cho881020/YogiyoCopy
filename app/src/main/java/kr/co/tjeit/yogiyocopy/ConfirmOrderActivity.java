package kr.co.tjeit.yogiyocopy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

import kr.co.tjeit.yogiyocopy.data.MenuData;

public class ConfirmOrderActivity extends BaseActivity {

    private android.widget.Button minusBtn;
    private android.widget.TextView countTxt;
    private android.widget.Button plusBtn;

    MenuData mMenuData = null;
    private TextView menuNameTxt;
    private TextView menuPriceTxt;
    private TextView amountTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        mMenuData = (MenuData) getIntent().getSerializableExtra("메뉴데이터");
        bindViews();
        setupEvents();
        setValues();
    }


    @Override
    public void setupEvents() {
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 숫자가 늘어난다.
                // 1. 기존의 숫자를 알아내야함.
                //  1) TextView의 글자를 String 형태로 빼내야함.
                String currentStr = countTxt.getText().toString();
                //  2) 빼낸 String -> int 형태로 변환
                // Integer클래스의 parseInt메쏘드를 실행시키면
                // 들어간 파라미터를 분석하여 숫자로 뱉어준다.
                int count = Integer.parseInt(currentStr);
                // 2. 알아낸 int 변수의 값을 1 증가시킴.
                count++;
                // 3. 증가시킨 int를 다시 TextView에 넣어준다.
                String countStr = String.format(Locale.KOREA, "%d", count);
                countTxt.setText(countStr);

                // 3번문제
                // 증가된 count와 전달받은 메뉴데이터의 가격을 이용해
                // 총 금액을 계산.
                int amount = count * mMenuData.getPrice();
                String amountStr = String.format(Locale.KOREA, "%,d원", amount);
                amountTxt.setText(amountStr);

            }
        });

        // 마이너스 버튼 누르면 숫자가 줄어들게
        // 만약, 현재 숫자가 1이면 줄어들지 않게.

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentStr = countTxt.getText().toString();
                int count = Integer.parseInt(currentStr);

                // 현재 개수가 몇개인지 가져와 보고 검사.
                if (count > 1) {
                    count--;
                    String countStr = String.format(Locale.KOREA, "%d", count);
                    countTxt.setText(countStr);

                    int amount = count * mMenuData.getPrice();
                    String amountStr = String.format(Locale.KOREA, "%,d원", amount);
                    amountTxt.setText(amountStr);
                }

            }
        });

    }

    @Override
    public void setValues() {
        menuNameTxt.setText(mMenuData.getMenuName());
        String priceStr = String.format(Locale.KOREA, "%,d원", mMenuData.getPrice());
        menuPriceTxt.setText(priceStr);
        amountTxt.setText(priceStr);
    }


    @Override
    public void bindViews() {
        this.amountTxt = (TextView) findViewById(R.id.amountTxt);
        this.plusBtn = (Button) findViewById(R.id.plusBtn);
        this.countTxt = (TextView) findViewById(R.id.countTxt);
        this.minusBtn = (Button) findViewById(R.id.minusBtn);
        this.menuPriceTxt = (TextView) findViewById(R.id.menuPriceTxt);
        this.menuNameTxt = (TextView) findViewById(R.id.menuNameTxt);
    }
}
