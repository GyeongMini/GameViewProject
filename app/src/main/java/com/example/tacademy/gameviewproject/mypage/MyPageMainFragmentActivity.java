package com.example.tacademy.gameviewproject.mypage;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tacademy.gameviewproject.R;
import com.example.tacademy.gameviewproject.model.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyPageMainFragmentActivity extends BaseActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    Fragment fragment = new Fragment();

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_main_fragment);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.mypagecontainer);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        // 탭 레이아웃글자의 색상
        tabLayout.setTabTextColors(getResources().getColor(R.color.fragmentHome),getResources().getColor(R.color.fragmentReview));


    }


    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.my_page_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    // fragment 생성
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if( position == 0){
                fragment = new MyPostFragment();
            }else if(position == 1){
                fragment = new MyLikeFragment();
            }else if(position ==2){
                fragment = new MyFanFragment();
            }else if(position ==3){
                fragment = new MyActivityFragment();
            }
            return fragment;
        }

        // fragment 갯수
        @Override
        public int getCount() {

            return 4;
        }

        // fragment 의 이름
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "내가쓴글";
                case 1:
                    return "좋아요";
                case 2:
                    return "팬";
                case 3:
                    return "내역";
            }
            return null;
        }
    }

    class ViewHolder {
        @BindView(R.id.mypageprofile)
        CircleImageView mypageprofile;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
