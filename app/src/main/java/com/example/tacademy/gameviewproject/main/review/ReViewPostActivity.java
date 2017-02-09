package com.example.tacademy.gameviewproject.main.review;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tacademy.gameviewproject.R;
import com.example.tacademy.gameviewproject.model.BaseActivity;
import com.example.tacademy.gameviewproject.model.ReViewPost;
import com.example.tacademy.gameviewproject.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.miguelbcr.ui.rx_paparazzo.RxPaparazzo;
import com.miguelbcr.ui.rx_paparazzo.entities.size.SmallSize;
import com.squareup.picasso.Picasso;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 리뷰 작성 페이지 생성
 */

public class ReViewPostActivity extends BaseActivity {

    EditText title, content;
    ImageView imageupload;
    Button reviewsave;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    // SweetAlertDialog 를 사용하기위해 전역 선언
    SweetAlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_post_main);

        title = (EditText) findViewById(R.id.title);
        content = (EditText) findViewById(R.id.content);
        reviewsave = (Button) findViewById(R.id.reviewsave);
        // 2017.02.09 리뷰 작성시 이미지 업로드
        imageupload = (ImageView) findViewById(R.id.imageupload);
        reviewsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSendPost();
            }
        });
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void onSendPost() {
        final String title_str = title.getText().toString();
        final String content_str = content.getText().toString();
        // 제목, 내용이 존재해야 함
        if (TextUtils.isEmpty(title_str)) {
            title.setError("필수 입력값입니다. ");
            return;
        }
        if (TextUtils.isEmpty(content_str)) {
            content.setError("필수 입력값입니다. ");
            return;
        }
        // 비속어 처리( 필터링 )
        // 입력을 못하게 막아야함 ( 편집 불가 )
        setEditable(false); // 입력 불가능 ( 비활성화 )
        // 회원이 맞는지 체크 => id
        // okldBCjxq6dStzIaOXRMa898CSH2
        Log.i("CHAT", FirebaseAuth.getInstance().getCurrentUser().getUid());
        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        // 1. uid가 존재하는지 체크
        databaseReference.child("users")
                .child(uid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // 게시물 & 채팅 같은걸 이걸로 받는다
                        User user = dataSnapshot.getValue(User.class);

                        if (user == null) { // 없던지 ( 없으면 불가 )
                            // 2. 없으면 불가
                            Toast.makeText(ReViewPostActivity.this, "회원이 아닙니다. ", Toast.LENGTH_SHORT).show();
                            setEditable(true); // 편집 가능하게는 해준다
                            finish();
                        } else { // 있던지 ( 글작성 업로드 )
                            // 3. 있으면 이후 작업 진행
                            // 로딩 시작
//                            showProgress("글 업로드 중");
                            // 글 작성 업로드
                            String key = databaseReference.child("posts").push().getKey();
                            ReViewPost post = new ReViewPost(title_str, content_str, uid, user.getId());
                            // Post class에 추가 해준다!
                            Map<String, Object> postMap = post.toPostMap();
                            // 여러 가지에 업데이트 방식으로 한번에 데이터를 삽입
                            // String(키), Object(값)
                            Map<String, Object> updates = new HashMap<String, Object>();
                            // 포스트라는 줄기에 하나의 고유한 키가 들어가야 한다.
                            // 객체를 고대로 넣으면 안되고 map 을 넣어야 한다! 이럴경우 post 하나를 더 준비 한다.
                            updates.put("/posts/" + key, postMap);
                            // 내글( 나만의 고유한 글이다. )
                            updates.put("/user-posts/" + uid + "/" + key, postMap);

                            // 추가
                            databaseReference.updateChildren(updates, new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                    if (databaseError != null) {
                                        Log.i("CHAT", "오류" + databaseError.getMessage());
                                    }
                                    // 입력 잠금 해제
                                    setEditable(true); // 입력 가능 ( 활성화 )
                                    // 로딩 닫기
//                                    hideProgress();
                                    // 화면 닫힘
                                    finish();
                                }
                            });
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    public void onPhoto(View view){
        Toast.makeText(ReViewPostActivity.this,"사진버튼을 클릭 하셨습니다.",Toast.LENGTH_SHORT).show();
        alert =
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("사진선택")
                        .setContentText("사진 선택 방법을 고르시오")
                        .setConfirmText("카메라")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                onCamera();

                            }
                        })
                        .setCancelText("갤러리")
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                onGallery();
                            }
                        });
//        alert.setCancelable(true);
        alert.show();
    }

    public void onCamera(){
        Toast.makeText(this,"카메라 버튼을 클릭 하셨습니다.",Toast.LENGTH_SHORT).show();
        // 크롭작업을 하기 위해 옵션 설정(편집)
        UCrop.Options options = new UCrop.Options();
        options.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        options.setMaxBitmapSize(1024*1024*2); // 2MB 언더

        RxPaparazzo.takeImage(this)
                .size(new SmallSize())     // 사이즈(SmallSize, ScreenSize, OriginalSize,CustomMaxSize)
                .crop(options)              // 편집
                .useInternalStorage()       // 내부저장 (않쓰면 외부 공용 공간에 앱이름으로 생성됨)
                .usingCamera()              // 카메라 사용
                .subscribeOn(Schedulers.io())   // IO
                .observeOn(AndroidSchedulers.mainThread())  // 스레드 설정
                .subscribe(response -> {    // 결과 처리
                    // 실패 처리
                    if (response.resultCode() != RESULT_OK) {
                        //response.targetUI().showUserCanceled();
                        return;
                    }
                    Log.i("camera", response.data());
                    loadImage(response.data());
                    //response.targetUI().loadImage(response.data());
                });
    }

    public void onGallery(){
        Toast.makeText(this, "갤러리 버튼을 클릭 하셨습니다.", Toast.LENGTH_SHORT).show();
        UCrop.Options options= new UCrop.Options();
        options.setToolbarColor(ContextCompat.getColor(this,R.color.colorPrimary));
        options.setMaxBitmapSize(1024 * 1024 * 2); // 2MB 언더

        RxPaparazzo.takeImage(this)
                .size(new SmallSize())
                .crop(options)
                .useInternalStorage()
                .usingGallery()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response ->{
                    if ( response.resultCode() != RESULT_OK){
                        return;
                    }
                    Log.i("PH", response.data());
                    loadImage(response.data());
                });
    }

    public void loadImage(String path){
        alert.dismissWithAnimation();
        // 이미지 뷰에 이미지를 세팅
        String url = "file://" + path;
        Picasso.with(this).setLoggingEnabled(true);
        Picasso.with(this).setIndicatorsEnabled(true);
        Picasso.with(this).invalidate(url);
        Picasso.with(this).load(url).into(imageupload);
        // 파일 업로드 ===========================================
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference root =
                storage.getReferenceFromUrl("gs://gameview-27ed8.appspot.com");

        // 내uri 가 등록되는 최초 경로
        Uri uri = Uri.fromFile(new File(path));

        // 내 프로필 사진의 경로
        String uploadName = "profile/" + uri.getLastPathSegment();
        // 기둥에 가지 등록
        StorageReference riversRef = root.child("images/" + uri.getLastPathSegment());

        // 업로드
        UploadTask uploadTask = riversRef.putFile(uri);
        // 이벤트 등록 및 처리
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // 실패
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                Log.i("KK 1", downloadUrl.toString());
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                Log.i("KK 2", taskSnapshot.toString());
            }
        });
    }
    // url 사진 링크를 ? bitmap 으로 바꿔줌
    public Bitmap getBitmapFromURL(String src) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(src);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally{
            if(connection!=null)connection.disconnect();
        }
    }

    // 입력을 못하게 막아야 하는 함수를 빼서 만듬!!
    public void setEditable(boolean flag) {
        title.setEnabled(flag);
        content.setEnabled(flag);
        if (flag) {
            reviewsave.setVisibility(View.VISIBLE);
        } else
            reviewsave.setVisibility(View.GONE);
    }
}


