package com.example.tacademy.gameviewproject.model;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tacademy.gameviewproject.R;
import com.example.tacademy.gameviewproject.ui.ImageProc;

/**
 * Created by Tacademy on 2017-02-06.
 */public class ReViewPostViewHolder extends RecyclerView.ViewHolder
{
    ImageView profile, star, cell_imageView;
    TextView nickName, star_count, title, content;
    String imgurl;
    public ReViewPostViewHolder(View itemView) {
        super(itemView);
//        profile     = (ImageView)itemView.findViewById(R.id.profile);
//        star        = (ImageView)itemView.findViewById(R.id.star);
//        nickName    = (TextView)itemView.findViewById(R.id.nickName);
//        star_count  = (TextView)itemView.findViewById(R.id.star_count);
        title       = (TextView)itemView.findViewById(R.id.title);
        content     = (TextView)itemView.findViewById(R.id.content);
        cell_imageView = (ImageView)itemView.findViewById(R.id.cell_imageView);
    }
    // 데이터를 개별 뷰에 설정 ( 만드는 거 )
    public void bindToPost(ReViewPost model, View.OnClickListener listener)
    {
//        profile.setOnClickListener(listener);
//        star.setOnClickListener(listener);
//        nickName.setText(model.getAuthor());
//        star_count.setText(""+model.getStart_count());
        title.setText(model.getTitle());
        content.setText(model.getContent());

        imgurl = model.getUrl();
        ImageProc.getInstance().drawImage(imgurl,cell_imageView);
        cell_imageView.setScaleType(ImageView.ScaleType.FIT_XY);

//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//        Log.i("UI", "뷰페이저 호출 " + position);
//        // position => 요청 페이지 요청 페이지 별로 뷰를 생성해서 처리!
//        // 요청 페이지 해당하는 url 획득
//        String url = poster[position];
//        // 이미지 뷰 생성
//        ImageView imageView = new ImageView(context);
//        // 이미지 세팅
//        ImageProc.getInstance().drawImage(url, imageView);
//        imageView.setScaleType(ImageView.ScaleType.FIT_XY);//무조건 꽉 채우기
//        ((ViewPager)container).addView(imageView);
//        return imageView; //super.instantiateItem(container, position);

    }
}