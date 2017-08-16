package kr.co.tjeit.yogiyocopy.util;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.yogiyocopy.data.StoreData;

/**
 * Created by user on 2017-08-16.
 */

public class GlobalData {
    // 글로벌데이터 : 앱의 모든 화면에서 데이터를 공유할 필요가 있을때
    // 로그인 한 이후로 데이터가 변경될일이 거의 없을때
    // 메인액티비티에 나타나야하는 데이터들은 거의 글로벌데이터.
    public static List<StoreData> storeDataList = new ArrayList<>();

}
