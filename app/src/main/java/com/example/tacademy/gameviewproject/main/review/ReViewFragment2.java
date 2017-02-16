//package com.example.tacademy.gameviewproject.main.review;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.OrientationHelper;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.StaggeredGridLayoutManager;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import com.example.tacademy.gameviewproject.PostHolder;
//import com.example.tacademy.gameviewproject.R;
//import com.example.tacademy.gameviewproject.ui.ImageProc;
//
///**
// * 리뷰 메인 화면
// * 카드뷰와 리사이클 뷰를 사용 함
// */
//
//public class ReViewFragment2 extends Fragment {
//    ImageView imageView;
//    Context context;
//    RecyclerView recyclerview;
//    MyAdapter myAdapter = new MyAdapter();
//    GridLayoutManager gridLayoutManager;
//    StaggeredGridLayoutManager staggeredGridLayoutManager;
//
//    String[] txt = {"경민아","정신차려라","경민아","정신차려라","경민아","정신차려라","경민아","정신차려라","경민아","정신차려라","경민아","정신차려라"};
//
//    String[] poster =
//            {
//                    "http://postfiles12.naver.net/20151001_59/ews1016_1443638863998JpeAd_JPEG/%B7%B9%C1%F6%B4%F8%C6%AE%C0%CC%BA%ED6-2.jpg?type=w2",
//                    "http://postfiles9.naver.net/20151104_232/ereda_14466029312851WMhd_JPEG/%B7%B9%C1%F6%B4%F8%C6%AE%C0%CC%BA%ED_%288%29.jpg?type=w1",
//                    "http://cfile18.uf.daum.net/image/202C5F4F5015DE65315D64",
//                    "http://cfile89.uf.daum.net/image/136F1B10ABD9BB7B510261",
//                    "http://postfiles12.naver.net/20151001_59/ews1016_1443638863998JpeAd_JPEG/%B7%B9%C1%F6%B4%F8%C6%AE%C0%CC%BA%ED6-2.jpg?type=w2",
//                    "http://postfiles9.naver.net/20151104_232/ereda_14466029312851WMhd_JPEG/%B7%B9%C1%F6%B4%F8%C6%AE%C0%CC%BA%ED_%288%29.jpg?type=w1",
//                    "http://cfile18.uf.daum.net/image/202C5F4F5015DE65315D64",
//                    "http://cfile89.uf.daum.net/image/136F1B10ABD9BB7B510261",
//                    "http://postfiles12.naver.net/20151001_59/ews1016_1443638863998JpeAd_JPEG/%B7%B9%C1%F6%B4%F8%C6%AE%C0%CC%BA%ED6-2.jpg?type=w2",
//                    "http://postfiles9.naver.net/20151104_232/ereda_14466029312851WMhd_JPEG/%B7%B9%C1%F6%B4%F8%C6%AE%C0%CC%BA%ED_%288%29.jpg?type=w1",
//                    "http://cfile18.uf.daum.net/image/202C5F4F5015DE65315D64",
//                    "http://cfile89.uf.daum.net/image/136F1B10ABD9BB7B510261"
//            };
//
//    @Override
//    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState)
//    {
//        ImageProc.getInstance().getImageLoader(context); // 초기화
//        View view = inflater.inflate(R.layout.fragment_review,container,false);
//
////      fab 버튼을 눌럿을때 리뷰 작성 페이지로 이동 한다.
//        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), ReViewPostActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        recyclerview = (RecyclerView)view.findViewById(R.id.recyclerview);
//
//        // 고정 크기 그리드
//        gridLayoutManager = new GridLayoutManager(context, 2);
//        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
//
//        gridLayoutManager.setStackFromEnd(true);
//        gridLayoutManager.setReverseLayout(true);
//
//        recyclerview.setLayoutManager(gridLayoutManager);
//
//        recyclerview.setAdapter(myAdapter);
//
//        return view;
//    }
//
//    // 아답터 생성
//    class MyAdapter extends RecyclerView.Adapter{
//
//        @Override
//        public int getItemCount() {
//            return txt.length;
//        }
//        // ViewHoledr 생성
//        @Override
//        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View itemView =
//                    LayoutInflater
//                            .from(parent.getContext())
//                            .inflate(R.layout.review_list_cardview, parent, false);
//            PostHolder postHolder = new PostHolder(itemView);
//            return postHolder;
//        }
//
//        // ViewHoledr에 데이터를 설정(바인딩) 한다.
//        @Override
//        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//            ((PostHolder)holder).bindOnPost(txt[position], poster[position]);
//        }
//    }
//
//    // Context 생성
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        this.context = context;
//    }
//}