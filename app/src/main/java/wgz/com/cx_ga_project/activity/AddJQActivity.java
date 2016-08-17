package wgz.com.cx_ga_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.jakewharton.rxbinding.view.RxView;
import com.jude.easyrecyclerview.EasyRecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import wgz.com.cx_ga_project.R;
import wgz.com.cx_ga_project.adapter.AddPictureAdapter;
import wgz.com.cx_ga_project.adapter.MyRecyclerArrayAdapter;
import wgz.com.cx_ga_project.app;
import wgz.com.cx_ga_project.base.BaseActivity;
import wgz.com.cx_ga_project.fragment.PhotoPickerFragment;
import wgz.com.cx_ga_project.util.SomeUtil;

import static wgz.com.cx_ga_project.activity.PickPhotoActivity.HTTP_URL;

public class AddJQActivity extends BaseActivity {

    @Bind(R.id.content_add_jq)
    LinearLayout rootview;
    @Bind(R.id.uploadPic_fab)
    FloatingActionButton uploadPicFab;
    private AddPictureAdapter adapter;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.id_callbackJQ)
    EditText idCallbackJQ;
    @Bind(R.id.addPicRV)
    EasyRecyclerView addPicRV;
    List<String> paths = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_addjq;
    }

    @Override
    public void initView() {
        toolbar.setTitle("警情回传");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addPicRV.setLayoutManager(new GridLayoutManager(this, 4));
        addPicRV.setAdapter(adapter = new AddPictureAdapter(this));
        adapter.addAll(initdata());
        adapter.setOnItemClickListener(new MyRecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View itemView) {
                if (position + 1 == adapter.getCount()) {
                    //PickPhotoActivity.actionStart(AddJQActivity.this, 9, null, null);
                    Intent intent = new Intent(AddJQActivity.this, PickPhotoActivity.class);
                    intent.putExtra(PhotoPickerFragment.EXTRA_SELECT_COUNT, 9);
                    intent.putExtra(PhotoPickerFragment.EXTRA_DEFAULT_SELECTED_LIST, "");
                    intent.putExtra(HTTP_URL, "");
                    startActivityForResult(intent, 0);
                }
            }
        });
        RxView.clicks(uploadPicFab).throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                       UpLoadPicture();
                        UpLoadPictures(paths);
                    }
                });

    }

    /**
     * 上传多张图片
     * @param paths
     */
    private void UpLoadPictures(List<String> paths) {
        List<File> files = new ArrayList<>();
        for (String path : paths){
            File file = new File(path);
            files.add(file);
        }
        app.apiService.uploadFilesWithParts(SomeUtil.filesToMultipartBodyParts(files))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {

                    }
                });

    }

    /**
     * 上传单个图片
     *
     */
    private void UpLoadPicture() {

        String path = "";
        File file = new File(path);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),file);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("pciture",file.getName(),requestBody);

            //execute the request
        /**
         * normal way
         */
            // Call<ResponseBody> call = app.apiService.uploadPic();
            //call.enqueue(callback);

        /**
         * use Rxjava
         */
        app.apiService.uploadPic("uploadpic",body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                    }
                });

    }

    private List<String> initdata() {

        paths.add("end");
       /* List<String> list = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            list.add("" + i);
        }*/
        return paths;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data.getStringExtra("result").equals("addpic")) {
            adapter.clear();
            paths.clear();
            paths = data.getStringArrayListExtra("paths");
            initdata();
            adapter.addAll(paths);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
