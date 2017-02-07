package com.example.tacademy.gameviewproject.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.tacademy.gameviewproject.R;
import com.example.tacademy.gameviewproject.main.review.ReViewPostActivity;
import com.example.tacademy.gameviewproject.model.ReViewPost;
import com.example.tacademy.gameviewproject.model.ReViewPostViewHolder;
import com.example.tacademy.gameviewproject.ui.ImageProc;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * 리뷰 메인 화면
 * 카드뷰와 리사이클 뷰를 사용 함
 */

public class ReViewFragment extends Fragment {
    ImageView imageView;
    Context context;

    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    FirebaseRecyclerAdapter firebaseRecyclerAdapter;

    StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        ImageProc.getInstance().getImageLoader(context); // 초기화
        View view = inflater.inflate(R.layout.fragment_review, container, false);

//      fab 버튼을 눌럿을때 리뷰 작성 페이지로 이동 한다.
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ReViewPostActivity.class);
                startActivity(intent);
            }
        });
        // 화면 세팅 구성
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        // 레이아웃 세팅
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);

        recyclerView.setLayoutManager(gridLayoutManager);

        // 쿼리가 수행 되어야 한다. ( limitTofirest( 10개 데이터 가져옴 ) 앞에서 부터 last 뒤부터
        Query query =
                FirebaseDatabase.getInstance().getReference().child("posts").limitToLast(10);
        // 아답터 생성
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ReViewPost, ReViewPostViewHolder>(
                // 데이터 4개를 맞춰야함
                ReViewPost.class,
                R.layout.review_list_cardview,
                ReViewPostViewHolder.class,
                // 쿼리 결과 가 들어간다.
                query
        ){
            // 레이아웃을 담기는 그릇, 데이터가 담기를 그릇, 필요한 인덱스
            @Override
            protected void populateViewHolder(ReViewPostViewHolder viewHolder, ReViewPost model, int position) {
                // 1. position 정보를 가지고 -> 데이터 획득( 참조 획득 )
                DatabaseReference databaseReference = getRef(position);
                // 2. viewHolder로 -> 이벤트 등록
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 상세보기로 이동
                    }
                });
                // 3. viewHolder로 -> 데이터 세팅(bindToPost)
                viewHolder.bindToPost(model, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);

        return view;
    }




    // Context 생성
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}