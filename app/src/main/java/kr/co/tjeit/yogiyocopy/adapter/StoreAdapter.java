package kr.co.tjeit.yogiyocopy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import kr.co.tjeit.yogiyocopy.R;
import kr.co.tjeit.yogiyocopy.data.StoreData;

/**
 * Created by user on 2017-08-02.
 */

public class StoreAdapter extends ArrayAdapter<StoreData> {

    Context mContext;
    List<StoreData> mList;
    LayoutInflater inf;

    public StoreAdapter(Context context, List<StoreData> list) {
        super(context, R.layout.store_list_item, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inf.inflate(R.layout.store_list_item, null);
            // 뷰를 새로 그려주는 작업
            // 재활용 하는 시점에는 들어오지 않는 부분
            // 절대 이 안에서 데이터 세팅 X
        }

        // 데이터를 찍어줄때 해야할 일들

        // 1. 데이터를 표시할 뷰들에 id 부여
        // 2. 표시할 데이터 가져오기
        // 저장된 위치? mList
        // 상황에 맞는 데이터 뽑아오기.
        // 1) 뽑아온다? => mList.get
        // 2) 상황에 맞는? => 각 줄의 번호에 맞는 데이터를 가져오기.
        // 3) 뽑아낸 데이터를 변수에 저장. 어떤 변수? 다루는 자료형

        StoreData data = mList.get(position);

        // 3. 데이터를 출력할 뷰들을 매핑
        // id를 부여한 객체들을 row.findViewById


        ImageView storeImg = (ImageView) row.findViewById(R.id.storeImg);
        ImageView starImg1 = (ImageView) row.findViewById(R.id.starImg1);
        ImageView starImg2 = (ImageView) row.findViewById(R.id.starImg2);
        ImageView starImg3 = (ImageView) row.findViewById(R.id.starImg3);
        ImageView starImg4 = (ImageView) row.findViewById(R.id.starImg4);
        ImageView starImg5 = (ImageView) row.findViewById(R.id.starImg5);
        TextView storeNameTxt = (TextView) row.findViewById(R.id.storeNameTxt);
        TextView avgRatingAndReviewCountTxt = (TextView) row.findViewById(R.id.avgRatingAndReviewCountTxt);
        TextView minDeliveryCostTxt = (TextView) row.findViewById(R.id.minDeliveryCostTxt);
        ImageView isCescoImg = (ImageView) row.findViewById(R.id.isCescoImg);
        TextView openAndCloseTimeTxt = (TextView) row.findViewById(R.id.openAndCloseTimeTxt);
        TextView payMethodTypeTxt = (TextView) row.findViewById(R.id.payMethodTypeTxt);

        // 4. 실제 데이터를 뷰에 세팅.
        // set?? 를 해준다. ?? 는 뷰의 종류와 상황에 따라 다르다.
        // 실제 데이터? data (StoreData) 변수
        // data가 가진 메쏘드들 중 게터를 활용하는 경우가 많다.

        Glide.with(mContext).load(data.getImagePath()).into(storeImg);

        storeNameTxt.setText(data.getStoreName());

        avgRatingAndReviewCountTxt.setText(data.getAvgRating()+"점 / " + data.getReviews().size() + "개의 리뷰");

        int openHour = data.getOpenTime() / 100;
        int openMinute = data.getOpenTime() % 100;
        int closeHour = data.getCloseTime() / 100;
        int closeMinute = data.getCloseTime() % 100;

        String openCloseStr = String.format("%02d:%02d - %02d:%02d", openHour,openMinute,closeHour,closeMinute);
        openAndCloseTimeTxt.setText(openCloseStr);


        // 최소 배달 금액 표시
        String minDeliveryCostStr = String.format("%,d원 이상 배달 가능", data.getMinCost());
        minDeliveryCostTxt.setText(minDeliveryCostStr);

        // 세스코 가입 여부 표시
        if (data.isCesco()) {
            isCescoImg.setVisibility(View.VISIBLE);
        }
        else {
            isCescoImg.setVisibility(View.INVISIBLE);
        }

        List<ImageView> stars = new ArrayList<>();
        stars.add(starImg1);
        stars.add(starImg2);
        stars.add(starImg3);
        stars.add(starImg4);
        stars.add(starImg5);

        for (ImageView iv : stars) {
            iv.setImageResource(R.drawable.gray_star);
        }


        int rating = (int) data.getAvgRating();
        for (int i=0 ; i < rating ; i++) {
            stars.get(i).setImageResource(R.drawable.fill_star);
        }


        return row;
    }
}










