package com.example.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.adapter.HotProjectAdapter;
import com.example.base.BaseData;
import com.example.base.BaseFragment;
import com.example.bean.HomeContent;
import com.example.dailystudy.HDetailsActivity;
import com.example.dailystudy.R;
import com.example.dailystudy.RoolViewDetailsActivity;
import com.example.manager.ChangeHideManager;
import com.example.util.GlideUtils;
import com.example.util.UrlUtils;
import com.example.view.GlideImageLoader;
import com.example.view.JudgeShowView;
import com.example.view.MyGridView;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/1/11.
 */
public class HomePagerFragment extends BaseFragment {

    public String data;
    private HomeContent homeContent = null;
    private Banner banner;
    private RecyclerView home_rlv;
    private TextView adlist_tv1, adlist_tv2, adlist_tv3, adlist_tv4, adlist_tv5, adlist_tv6;
    private ImageView adlist_img, adlist_img1, adlist_img2;
    private MyGridView hot_mygv;
    private ImageView top1, top2;
    private RecyclerView recommend_rlv, study_rlv;
    private ArrayList<String> imagelist;
    private View view;

    /**
     * 成功视图
     *
     * @param statusCurrent
     * @return
     */
    @Override
    protected View setSuccessView(int statusCurrent) {
        view = initview();
        ChangeHideManager.changeVisible(view, statusCurrent);

        RoolViewPager(homeContent);
        //分类
        HotCategory();
        //AD List
       ADList();
        //热门课程
      HotProject();
        //小编推荐
       Recommend();
        //大家都在学
       Study();

        return view;
    }

    //大家都在学
    private void Study() {
        final List<HomeContent.DataBean.IndexothersBean> indexothers = homeContent.getData().getIndexothers();
        study_rlv.setLayoutManager(new LinearLayoutManager(getActivity()));
        CommonAdapter<HomeContent.DataBean.IndexothersBean> commonAdapter= new CommonAdapter<HomeContent.DataBean.IndexothersBean>(getActivity(), R.layout.recommend, indexothers) {
            @Override
            protected void convert(ViewHolder holder, HomeContent.DataBean.IndexothersBean indexothersBean, int position) {
                ImageView project_img = (ImageView) holder.getConvertView().findViewById(R.id.project_img);
                TextView project_name = (TextView) holder.getConvertView().findViewById(R.id.project_name);
                TextView school_name = (TextView) holder.getConvertView().findViewById(R.id.school_name);
                TextView project_price = (TextView) holder.getConvertView().findViewById(R.id.project_price);
                TextView user_num = (TextView) holder.getConvertView().findViewById(R.id.user_num);
                GlideUtils.loadImageView(getActivity(), indexothers.get(position).getCourse_pic(), project_img);
                project_name.setText(indexothers.get(position).getCourse_name());
                school_name.setText(indexothers.get(position).getSchool_name());
                project_price.setText(indexothers.get(position).getCourse_price() + "");
                if (project_price.getText().equals("0.00")) {
                    project_price.setText("免费");
                } else if (!project_price.getText().equals("0.00")) {
                    project_price.setTextColor(getResources().getColor(R.color.red));
                    project_price.setText("¥" + indexothers.get(position).getCourse_price());
                }
                user_num.setText(indexothers.get(position).getUsercount() + "人正在学习");
            }
        };
        study_rlv.setAdapter(commonAdapter);
        commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(getActivity(), HDetailsActivity.class);
                intent.putExtra("url", indexothers.get(position).getCid());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {

                return false;
            }
        });


    }

    //小编推荐
    private void Recommend() {
        final List<HomeContent.DataBean.IndexrecommendBean.TopBean> top = homeContent.getData().getIndexrecommend().getTop();
        GlideUtils.loadImageView(getActivity(), top.get(0).getCourse_pic(), top1);
        top1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HDetailsActivity.class);
                intent.putExtra("url", top.get(0).getCid());
                startActivity(intent);
            }
        });
        GlideUtils.loadImageView(getActivity(), top.get(1).getCourse_pic(), top2);
        top2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HDetailsActivity.class);
                intent.putExtra("url", top.get(1).getCid());
                startActivity(intent);
            }
        });
        recommend_rlv.setLayoutManager(new LinearLayoutManager(getActivity()));
        final List<HomeContent.DataBean.IndexrecommendBean.ListviewBean> listview = homeContent.getData().getIndexrecommend().getListview();
        CommonAdapter<HomeContent.DataBean.IndexrecommendBean.ListviewBean> commonAdapter=new CommonAdapter<HomeContent.DataBean.IndexrecommendBean.ListviewBean>(getActivity(), R.layout.recommend, listview) {
            @Override
            protected void convert(ViewHolder holder, HomeContent.DataBean.IndexrecommendBean.ListviewBean listviewBean, int position) {
                ImageView project_img = (ImageView) holder.getConvertView().findViewById(R.id.project_img);
                TextView project_name = (TextView) holder.getConvertView().findViewById(R.id.project_name);
                TextView school_name = (TextView) holder.getConvertView().findViewById(R.id.school_name);
                TextView project_price = (TextView) holder.getConvertView().findViewById(R.id.project_price);
                TextView user_num = (TextView) holder.getConvertView().findViewById(R.id.user_num);
                GlideUtils.loadImageView(getActivity(), listview.get(position).getCourse_pic(), project_img);
                project_name.setText(listview.get(position).getCourse_name());
                school_name.setText(listview.get(position).getSchool_name());
                project_price.setText(listview.get(position).getCourse_price() + "");
                if (project_price.getText().equals("0.00")) {
                    project_price.setText("免费");
                } else if (!project_price.getText().equals("0.00")) {
                    project_price.setTextColor(getResources().getColor(R.color.red));
                    project_price.setText("¥" + listview.get(position).getCourse_price());
                }
                user_num.setText(listview.get(position).getUsercount() + "人正在学习");
            }
        };
        recommend_rlv.setAdapter(commonAdapter);
        commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(getActivity(), HDetailsActivity.class);
                intent.putExtra("url", listview.get(position).getCid());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

    }

    //热门课程
    private void HotProject() {
        final List<HomeContent.DataBean.HotcourseBean> hotcourse = homeContent.getData().getHotcourse();
        hot_mygv.setAdapter(new HotProjectAdapter(getActivity(), hotcourse));
        hot_mygv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), HDetailsActivity.class);
                intent.putExtra("url", hotcourse.get(position).getCid());
                startActivity(intent);
            }
        });
    }

    //AD List
    private void ADList() {
        final List<HomeContent.DataBean.AdlistBean> adlist = homeContent.getData().getAdlist();
        adlist_tv1.setText(adlist.get(0).getName());
        adlist_tv2.setText(adlist.get(0).getTitle());
        GlideUtils.loadImageView(getActivity(), adlist.get(0).getImg(), adlist_img);
        adlist_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HDetailsActivity.class);
                intent.putExtra("url", adlist.get(0).getUrl());
                startActivity(intent);
            }
        });
        adlist_tv3.setText(adlist.get(1).getName());
        adlist_tv4.setText(adlist.get(1).getTitle());
        GlideUtils.loadImageView(getActivity(), adlist.get(1).getImg(), adlist_img1);
        adlist_img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HDetailsActivity.class);
                intent.putExtra("url", adlist.get(1).getUrl());
                startActivity(intent);
            }
        });
        adlist_tv5.setText(adlist.get(2).getName());
        adlist_tv6.setText(adlist.get(2).getTitle());
        GlideUtils.loadImageView(getActivity(), adlist.get(2).getImg(), adlist_img2);
        adlist_img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HDetailsActivity.class);
                intent.putExtra("url", adlist.get(2).getUrl());
                startActivity(intent);
            }
        });
    }

    //轮播图
    private void RoolViewPager(final HomeContent homeContent) {
        imagelist = new ArrayList<>();
        for (int i = 0; i < this.homeContent.getData().getSlider().size(); i++) {
            imagelist.add(this.homeContent.getData().getSlider().get(i).getImg());
        }
        banner.setImages(imagelist);
        banner.isAutoPlay(true);
        banner.setImageLoader(new GlideImageLoader());
        banner.setDelayTime(1500);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.start();
        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                if(position==2||position==4){
                    Intent intent = new Intent(getActivity(), RoolViewDetailsActivity.class);
                    intent.putExtra("url", homeContent.getData().getSlider().get(position-1).getUrl());
                    startActivity(intent);
                }
                if(position==3||position==5){
                    Intent intent = new Intent(getActivity(), HDetailsActivity.class);
                    intent.putExtra("url", homeContent.getData().getSlider().get(position-1).getUrl());
                    startActivity(intent);
                }
            }
        });
    }

    //分类
    private void HotCategory() {
        List<HomeContent.DataBean.HotcategoryBean> hotcategory = homeContent.getData().getHotcategory();
        final ArrayList<String> category_imglist = new ArrayList<>();
        for (int i = 0; i < hotcategory.size() - 1; i++) {
            category_imglist.add(hotcategory.get(i).getImg());
        }
        final ArrayList<String> category_namelist = new ArrayList<>();
        for (int i = 0; i < hotcategory.size() - 1; i++) {
            category_namelist.add(hotcategory.get(i).getCname());
        }
        home_rlv.setLayoutManager(new GridLayoutManager(getActivity(), 3, GridLayout.VERTICAL, true));
        home_rlv.setAdapter(new CommonAdapter<String>(getActivity(), R.layout.hot_category, category_imglist) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                ImageView category_img = (ImageView) holder.getConvertView().findViewById(R.id.category_img);
                TextView name = (TextView) holder.getConvertView().findViewById(R.id.category_name);
                name.setText(category_namelist.get(position));
                GlideUtils.loadImageView(getActivity(), category_imglist.get(position), category_img);
            }
        });
    }

    //控件ID
    private View initview() {
        view = View.inflate(getActivity(), R.layout.fragment_homepage, null);
        banner = (Banner) view.findViewById(R.id.home_banner);
        home_rlv = (RecyclerView) view.findViewById(R.id.home_rlv);
        adlist_tv1 = (TextView) view.findViewById(R.id.adlist_tv1);
        adlist_tv2 = (TextView) view.findViewById(R.id.adlist_tv2);
        adlist_img = (ImageView) view.findViewById(R.id.adlist_img);
        adlist_tv3 = (TextView) view.findViewById(R.id.adlist_tv3);
        adlist_tv4 = (TextView) view.findViewById(R.id.adlist_tv4);
        adlist_img1 = (ImageView) view.findViewById(R.id.adlist_img1);
        adlist_tv5 = (TextView) view.findViewById(R.id.adlist_tv5);
        adlist_tv6 = (TextView) view.findViewById(R.id.adlist_tv6);
        adlist_img2 = (ImageView) view.findViewById(R.id.adlist_img2);
        hot_mygv = (MyGridView) view.findViewById(R.id.hot_mygv);
        top1 = (ImageView) view.findViewById(R.id.top1);
        top2 = (ImageView) view.findViewById(R.id.top2);
        recommend_rlv = (RecyclerView) view.findViewById(R.id.recommend_rlv);
        study_rlv = (RecyclerView) view.findViewById(R.id.study_rlv);
        return view;
    }

    @Override
    protected View setDifferentView(int status) {
        view = View.inflate(getActivity(), R.layout.fragment_homepage, null);
        ChangeHideManager.changeVisible(view, status);
        LinearLayout no_network = (LinearLayout) view.findViewById(R.id.no_network);
        no_network.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoad();
            }
        });
        return view;
    }

    @Override
    public void onLoad() {
        new BaseData() {
            @Override
            public void setResultData(String data) {
                Gson gson = new Gson();
                homeContent = gson.fromJson(data, HomeContent.class);

                judgeShowView.setViewStatus(JudgeShowView.StatusType.STATUS_SUCCESS);
            }
            @Override
            public void setResultError() {
                judgeShowView.setViewStatus(JudgeShowView.StatusType.STATUS_NO_NETWORK);
            }
        }.getData(getActivity(), UrlUtils.homeurl, "", 0, BaseData.NOTIME);
    }
}
