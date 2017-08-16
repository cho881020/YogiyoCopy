package kr.co.tjeit.yogiyocopy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.yogiyocopy.MainActivity;
import kr.co.tjeit.yogiyocopy.R;
import kr.co.tjeit.yogiyocopy.adapter.StoreAdapter;
import kr.co.tjeit.yogiyocopy.data.StoreData;

/**
 * Created by user on 2017-08-03.
 */

public class StoreListFragment extends Fragment {

    private ListView storeListView;
    public static List<StoreData> storeDataList = new ArrayList<>();
    StoreAdapter storeAdapter;

    // 생명주기


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Frag", "프래그먼트 생성됨");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_store_list, container, false);
        storeListView = (ListView) v.findViewById(R.id.storeListView);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 우리가 사용하게 될 메쏘드
        storeDataList.add(new StoreData("https://s3.ap-northeast-2.amazonaws.com/slws3/imgs/tje_practice/kyochon_logo.jpg", "교촌치킨-대학로점", 4.2f, 1200, 2330, 15000, true, "123-45-67890", "교촌치킨대학로점"));
        storeDataList.add(new StoreData("https://s3.ap-northeast-2.amazonaws.com/slws3/imgs/tje_practice/one_logo.jpg", "원할머니보쌈-종로5가점", 3.8f, 1100, 300, 25000, false, "654-78-32145", "원할머니보쌈종로점"));
        storeDataList.add(new StoreData("https://www.yogiyo.co.kr/media/restaurant_logos/%EB%96%A1%EB%B3%B6%EC%9D%B4%EC%88%9C%EB%8C%80%EC%84%B8%ED%8A%B801_20131128_FoodAD_crop_200x200_yjnTv3z.jpg", "스쿨스토어-종로점", 4.2f, 1200, 2330, 15000, true, "321-45-78962", "스쿨스토어종로점"));
        storeDataList.add(new StoreData("https://www.yogiyo.co.kr/media/restaurant_logos/%EB%B3%B8%EB%8F%84%EC%8B%9C%EB%9D%BD_20150825_Franchise%EC%9D%B4%EB%AF%B8%EC%A7%80%EC%95%BD%EC%A0%95%EC%84%9C_crop_200x200_zY4cB53.jpg", "본도시락-서울시청점", 3.8f, 1100, 300, 25000, false, "654-32-78945", "본도시락서울시청점"));
        storeDataList.add(new StoreData("https://www.yogiyo.co.kr/media/restaurant_logos/%EC%89%AC%EB%A6%BC%ED%94%84_%ED%94%BC%EC%9E%9001_20131128_FoodAD_crop_200x200.jpg", "훼미리피자", 4.2f, 1200, 2330, 15000, true, "213-45-78945", "훼미리피자종로점"));
        storeDataList.add(new StoreData("https://www.yogiyo.co.kr/media/restaurant_logos/%ED%83%95%EC%88%98%EC%9C%A103_20131128_FoodAD_crop_200x200_Rn9zt25.jpg", "남경-남대문시장점", 3.8f, 1100, 300, 25000, false, "321-45-32165", "남경남대문시장점"));


        storeAdapter = new StoreAdapter(getActivity(), storeDataList);
        storeListView.setAdapter(storeAdapter);

    }
}




