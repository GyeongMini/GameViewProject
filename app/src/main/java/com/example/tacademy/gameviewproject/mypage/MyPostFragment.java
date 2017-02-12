package com.example.tacademy.gameviewproject.mypage;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tacademy.gameviewproject.R;
import com.example.tacademy.gameviewproject.model.ReViewPost;
import com.example.tacademy.gameviewproject.model.ReViewPostViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * 내가 글쓴 목록
 */

public class MyPostFragment extends Fragment {
    Context context;

    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    FirebaseRecyclerAdapter firebaseRecyclerAdapter;
    StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mypage_mypost_fragment, container, false);


        recyclerView = (RecyclerView)view.findViewById(R.id.mypage_mypost_recyclerview);

        gridLayoutManager = new GridLayoutManager(getContext(),2);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

        Query query =
                FirebaseDatabase.getInstance().getReference().child("posts").limitToLast(10);

        // 아답터 생성
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ReViewPost, ReViewPostViewHolder>(
                // 데이터 4개를 맞춰야함
                ReViewPost.class,
                R.layout.cell_mypage_mypost_cardview,
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
