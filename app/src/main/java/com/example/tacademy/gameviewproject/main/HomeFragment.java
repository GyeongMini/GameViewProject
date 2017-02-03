package com.example.tacademy.gameviewproject.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tacademy.gameviewproject.R;
import com.example.tacademy.gameviewproject.ui.ImageProc;

/**
 * 02.01 ( 박경민 )
 * 페이저를 활용 하여 이미지 좌우로 넘기기
 */

public class HomeFragment extends Fragment {
    // 생성
    Context context;
    ViewPager viewPager;
    TextView curDot;
    MyPagerAdapter myPagerAdapter;

    String[] poster =
            {
                    "http://postfiles12.naver.net/20151001_59/ews1016_1443638863998JpeAd_JPEG/%B7%B9%C1%F6%B4%F8%C6%AE%C0%CC%BA%ED6-2.jpg?type=w2",
                    "http://postfiles9.naver.net/20151104_232/ereda_14466029312851WMhd_JPEG/%B7%B9%C1%F6%B4%F8%C6%AE%C0%CC%BA%ED_%288%29.jpg?type=w1",
                    "http://cfile18.uf.daum.net/image/202C5F4F5015DE65315D64",
                    "http://cfile89.uf.daum.net/image/136F1B10ABD9BB7B510261"
            };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        viewPager = (ViewPager)view.findViewById(R.id.viewPager);
        curDot = (TextView)view.findViewById(R.id.curDot);
        myPagerAdapter = new MyPagerAdapter();
        ImageProc.getInstance().getImageLoader(context);// 초기화

        viewPager.setAdapter(myPagerAdapter);

        // 뷰 페이저에 이벤트 등록 , 페이지 변환 감지 한다.
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Log.i("UI", position + " : " + positionOffset + "," +positionOffsetPixels);
                changeDot(position);
            }
            @Override
            public void onPageSelected(int position) {
//                Toast.makeText(MainActivity.this, position+"번 선택",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onPageScrollStateChanged(int state) {
//                Log.i("UI", "변경 : " + state);

            }
        });

        return view;
    }

    // 핸들러를 이용하여 자동적으로 페이지 변환
//    int pagerCurPage;
//    Handler ackHandler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what){
//                case 0:{
//                    pagerCurPage++;
//                    int page = pagerCurPage % poster.length;
//                    viewPager.setCurrentItem(page);
//                    ackHandler.sendEmptyMessageDelayed(0,1000*2);
//                }
//                break;
//            }
//        }
//    };



    // 하단 페이지 도트 변경
    public void changeDot(int position){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i<poster.length; i++){
            if(i == position){
                sb.append("● ");

            }else{
                sb.append("○ ");
            }
            // 버퍼를 스트링으로 만들고 앞뒤공백을 제거해서 화면에 반영한다
            // 버퍼->String -> 공백제거-> 화면 반영
            curDot.setText(sb.toString().trim());
        }
    }


    // 페이저 아답터
    class MyPagerAdapter extends PagerAdapter {
        // 뷰의 갯수
        @Override
        public int getCount() {
            return poster.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // super.destroyItem(container, position, object);
            ((ViewPager)container).removeView((ImageView)object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
                Log.i("UI", "뷰페이저 호출 " + position);
                // position => 요청 페이지 요청 페이지 별로 뷰를 생성해서 처리!
                // 요청 페이지 해당하는 url 획득
                String url = poster[position];
                // 이미지 뷰 생성
                ImageView imageView = new ImageView(context);
                // 이미지 세팅
                ImageProc.getInstance().drawImage(url, imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);//무조건 꽉 채우기
            ((ViewPager)container).addView(imageView);
            return imageView; //super.instantiateItem(container, position);
        }

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
