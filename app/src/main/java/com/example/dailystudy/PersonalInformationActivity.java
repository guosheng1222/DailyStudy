package com.example.dailystudy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.util.DialogUtil;
import com.example.util.OnItemSelectedListener;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PersonalInformationActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView titlename;
    private ImageView p_img;
    private TextView p_name;
    private TextView p_phone;
    private CheckBox p_boy, p_girl;
    private SharedPreferences.Editor edit;
    private SharedPreferences sp;
    private Button mButton;
    private String[] items = {"相册", "拍照"};
    int mWidth;
    private File file;

    private static final int OPEN_CAMERA = 100;
    private static final int OPEN_GALLERY = 101;
    private static final int CROP = 102;

    private String imagepath;
    private CheckBox p_boy,p_girl;
    private Button exist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        sp = getSharedPreferences("user", MODE_PRIVATE);
        edit = sp.edit();
        //修改标题名
        titlename = (TextView) findViewById(R.id.titlename);
        titlename.setText("个人信息");

        p_img = (ImageView) findViewById(R.id.p_img);
        p_name =(TextView)  findViewById(R.id.p_name);
        p_phone = (TextView) findViewById(R.id.p_phone);
        p_boy = (CheckBox) findViewById(R.id.p_boy);
        p_girl = (CheckBox) findViewById(R.id.p_girl);

        String name = getIntent().getStringExtra("name");
        String sex = getIntent().getStringExtra("sex");
        String img = getIntent().getStringExtra("img");

        Glide.with(PersonalInformationActivity.this).load(img).into(p_img);
        p_name.setText(name);
        p_phone.setText(name);
        initData();
        if (sex.equals("1")) {
            p_boy.setChecked(true);
        } else if (sex.equals("2")) {
            p_girl.setChecked(true);
        }

        p_boy.setOnClickListener(this);
        p_girl.setOnClickListener(this);
        p_img.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.p_boy:
                p_girl.setChecked(false);
                edit.putString("sex", "1");
                edit.commit();
                break;
            case R.id.p_girl:
                p_boy.setChecked(false);
                edit.putString("sex", "2");
                edit.commit();
                break;
            case R.id.p_img:
                showDialog();
                break;
            case R.id.exist:
                SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.clear();
                edit.commit();
                finish();
                break;
        }
    }

    private OnItemSelectedListener onIllegalListener = new OnItemSelectedListener() {
        @Override
        public void getSelectedItem(String content) {
            if (content.equals("相册")) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, OPEN_GALLERY);
            } else if (content.equals("拍照")) {
                file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),
                        System.currentTimeMillis() + ".png");
                // 隐式意图打开系统界面 --要求回传
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // 存到什么位置
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(intent, OPEN_CAMERA);
            }
        }
    };

    private void showDialog() {
        DialogUtil.showItemSelectDialog(PersonalInformationActivity.this, mWidth
                , onIllegalListener
                , "相册"
                , "拍照"
        );//可填添加任意多个Item呦
    }

    private void initData() {
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        mWidth = dm.widthPixels;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OPEN_CAMERA) {
            // 图片怎么取出
            // imageView.setImageURI(Uri.fromFile(file));
            crop(Uri.fromFile(file));
        } else if (requestCode == OPEN_GALLERY) {
            // 相册应用通过putData设置的图片的uri，所以我们这样拿
            Uri uri = data.getData();
            // imageView.setImageURI(uri);
            crop(uri);
        } else {
            if (requestCode == CROP) {
                // 直接拿到一张图片
                final Bitmap bitmap = data.getParcelableExtra("data");
                imagepath = System.currentTimeMillis() + ".png";
                File picFile = new File(Environment.getExternalStorageDirectory(),
                        imagepath);
                // 把bitmap放置到文件中
                // format 格式
                // quality 质量
                try {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(
                            picFile));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                HttpUtils httpUtils = new HttpUtils();
                // 必须post get 1k
                // 请求方式 请求地址 请求参数 回调
                RequestParams params = new RequestParams();
                params.addBodyParameter("files", picFile);
                httpUtils.send(HttpRequest.HttpMethod.POST, "http://169.254.195.242:8080/imageupload/servlet/UploadServlet",
                        params, new RequestCallBack<String>() {
                            @Override
                            public void onFailure(HttpException arg0,
                                                  String arg1) {
                                Toast.makeText(PersonalInformationActivity.this, "上传失败" + arg1, Toast.LENGTH_SHORT)
                                        .show();
                            }
                            @Override
                            public void onSuccess(ResponseInfo<String> arg0) {
                                Toast.makeText(PersonalInformationActivity.this, "上传成功", Toast.LENGTH_SHORT)
                                        .show();
                                p_img.setImageBitmap(bitmap);

                            }
                        });
                p_img.setImageBitmap(bitmap);
            }
        }
    }

    // 手机自带裁剪功能--调用系统裁剪的意图
    public void crop(Uri uri) {
        // 定义图片裁剪意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置是否裁剪
        intent.putExtra("crop", "true");
        // 裁剪框的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 设置输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        // 设置图片格式
        intent.putExtra("outputFormat", "JPEG");
        // 是否返回数据
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP);
    }
}
