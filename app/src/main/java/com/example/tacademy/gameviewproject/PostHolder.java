package com.example.tacademy.gameviewproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tacademy.gameviewproject.ui.ImageProc;

/**
 * Created by Tacademy on 2017-02-02.
 */

public class PostHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView title;
    Context context;
//    ImageProc.getInstance().getImageLoader(context);

    public PostHolder(View itemView) {
        super(itemView);

        title = (TextView)itemView.findViewById(R.id.titleName);
        imageView = (ImageView)itemView.findViewById(R.id.cell_imageView);
    }

    // 데이터 설정
    public void bindOnPost (String text, String url)
    {
        title.setText(text);

        ImageProc.getInstance().drawImage(url, imageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);//무조건 꽉 채우기

    }


}
