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

import java.util.List;
import java.util.Locale;

import kr.co.tjeit.yogiyocopy.R;
import kr.co.tjeit.yogiyocopy.data.MenuData;

/**
 * Created by user on 2017-08-16.
 */

public class MenuAdapter extends ArrayAdapter<MenuData> {

    Context mContext;
    List<MenuData> mList;
    LayoutInflater inf;

    public MenuAdapter(Context context, List<MenuData> list) {
        super(context, R.layout.menu_list_item, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inf.inflate(R.layout.menu_list_item, null);
        }

        MenuData data = mList.get(position);

        ImageView menuImg = (ImageView) row.findViewById(R.id.menuImg);
        TextView menuNameTxt = (TextView) row.findViewById(R.id.menuNameTxt);
        TextView menuPriceTxt = (TextView) row.findViewById(R.id.menuPriceTxt);

        menuNameTxt.setText(data.getMenuName());

        Glide.with(mContext).load(data.getImageURL()).into(menuImg);

        String priceStr = String.format(Locale.KOREA, "%,d원", data.getPrice());
        menuPriceTxt.setText(priceStr);

        return row;
    }

//    @Override
//    public int getCount() {
//        // 실제로 데이터를 집어넣는 단계가 되면 반드시 삭제해야할 메쏘드.
//        // 삭제하지 않으면 강제로 화면에 띄우다가 nullException을 만나서 앱이 죽는다.
//        return 20;
//    }
}
