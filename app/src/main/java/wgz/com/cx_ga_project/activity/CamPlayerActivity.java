package wgz.com.cx_ga_project.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.uniview.airimos.Player;
import com.uniview.airimos.listener.OnLockPtzListener;
import com.uniview.airimos.listener.OnPtzCommandListener;
import com.uniview.airimos.listener.OnQueryResourceListener;
import com.uniview.airimos.listener.OnStartLiveListener;
import com.uniview.airimos.listener.OnStopLiveListener;
import com.uniview.airimos.listener.OnUnLockPtzListener;
import com.uniview.airimos.manager.ServiceManager;
import com.uniview.airimos.obj.QueryCondition;
import com.uniview.airimos.obj.ResourceInfo;
import com.uniview.airimos.parameter.LockPtzParam;
import com.uniview.airimos.parameter.PtzCommandParam;
import com.uniview.airimos.parameter.QueryResourceParam;
import com.uniview.airimos.parameter.StartLiveParam;
import com.uniview.airimos.protocol.PresetInfo;
import com.uniview.airimos.service.KeepaliveService;
import com.uniview.airimos.thread.RecvStreamThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;
import wgz.com.cx_ga_project.R;
import wgz.com.cx_ga_project.adapter.CamsIDAdapter;
import wgz.com.cx_ga_project.adapter.MyRecyclerArrayAdapter;
import wgz.com.cx_ga_project.base.BaseActivity;
import wgz.com.cx_ga_project.util.SomeUtil;
import wgz.datatom.com.utillibrary.util.LogUtil;

public class CamPlayerActivity extends BaseActivity implements KeepaliveService.OnKeepaliveListener {

    @Bind(R.id.fab2)
    FloatingActionButton fab2;
    @Bind(R.id.id_cams)
    TextView idCams;
    @Bind(R.id.cam_recyclerview)
    EasyRecyclerView recyclerview;
    private Player mPlayer;
    private KeepaliveService mService = null;
    private boolean mBound = false;
    private boolean mRequireLogout = false;
    private RecvStreamThread mRecvStreamThread = null;
    private String mCameraCode;
    private List<PresetInfo> mPresetInfos;
    private ArrayAdapter mAdapter;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.video_view)
    SurfaceView mSurfaceView;
    @Bind(R.id.content_cam_player)
    LinearLayout rootview;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    CamsIDAdapter adapter;
    List<String> camsIDs = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_cam_player;
    }

    @Override
    public void initView() {
        mSurfaceView.getHolder().addCallback(new MySurfaceCallback());
        toolbar.setTitle("监控播放");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter = new CamsIDAdapter(this));
        adapter.setOnItemClickListener(new MyRecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View itemView) {
                TextView id = (TextView) itemView.findViewById(R.id.id_item_camID);
                stopLive();
                //startLive(id.getText().toString());
                mCameraCode = id.getText().toString();
                SomeUtil.showSnackBar(rootview,"选中摄像头: "+id.getText().toString());
            }
        });




        RxView.clicks(fab).throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        startLive(mCameraCode);
                    }
                });
        RxView.clicks(fab2).throttleFirst(300, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        queryCameraRes();
                    }
                });


        //初始化一个Player对象
        mPlayer = new Player();
        mPlayer.AVInitialize(mSurfaceView.getHolder());
    }

    /**
     * 查询摄像机资源
     */
    public void queryCameraRes() {
        try {
            camsIDs.clear();
            adapter.clear();
            OnQueryResourceListener listener = new OnQueryResourceListener() {
                @Override
                public void onQueryResourceResult(long errorCode, String errorDesc, List<ResourceInfo> resList) {
                    if (null == resList) {
                        return;
                    }
                    StringBuffer stringBuffer = new StringBuffer();
                    int size = resList.size();
                    for (int i = 0; i < size; i++) {
                        stringBuffer.append(resList.get(i).getResCode() + ",");
                        stringBuffer.append(resList.get(i).getResName() + ",");
                        stringBuffer.append(resList.get(i).getResType() + ",");
                        stringBuffer.append(resList.get(i).getResSubType() + ",");
                        stringBuffer.append(resList.get(i).getIsOnline() + "\n");

                        //找到第一个在线的摄像机
                        if (resList.get(i).getResType() == ResourceInfo.ResType.CAMERA && resList.get(i).getIsOnline()) {
                            mCameraCode = resList.get(i).getResCode().trim();
                            camsIDs.add(resList.get(i).getResCode().trim());

                        }
                    }
                    LogUtil.e("cam列表：" + stringBuffer.toString());
                    // idCams.setText("cam列表：" + stringBuffer.toString());
                    adapter.addAll(camsIDs);
                }
            };


            //查询摄像机资源参数
            QueryResourceParam param = new QueryResourceParam("", "", new QueryCondition(0, 100, true));

            //调用查询资源接口
            ServiceManager.queryResource(param, listener);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    class MySurfaceCallback implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {

            LogUtil.e("------ surfaceCreated -----");
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            LogUtil.e("------ surfaceChanged -----");
            if (null != mPlayer) {
                mPlayer.changeDisplaySize(width, height);
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            LogUtil.e("------ surfaceDestroyed -----");
        }
    }

    /**
     * 启动实况
     *
     * @param cameraCode 摄像机编码
     */
    public void startLive(String cameraCode) {
        try {
            //启动实况结果监听
            OnStartLiveListener listener = new OnStartLiveListener() {
                @Override
                public void onStartLiveResult(long errorCode, String errorDesc, String playSession) {

                    if (errorCode == 0) {
                        //将播放回话设给Player
                        mPlayer.setPlaySession(playSession);

                        if (null != mRecvStreamThread) {
                            mPlayer.AVStopPlay();
                            mRecvStreamThread.interrupt();
                            mRecvStreamThread = null;
                        }

                        //启动播放
                        mPlayer.AVStartPlay();

                        mRecvStreamThread = new RecvStreamThread(mPlayer, playSession);
                        mRecvStreamThread.start();
                    } else {
                        //Toast.makeText(PtzActivity.this,errorDesc,Toast.LENGTH_SHORT).show();
                    }

                }
            };

            //设置实况的参数
            StartLiveParam param = new StartLiveParam();
            param.setUseSecondStream(true);
            param.setCameraCode(cameraCode);
            param.setResolution(2);     //4CIF分辨率
            param.setFramerate(15);
            param.setBitrate(32 * 8);

            //启动实况接口调用
            ServiceManager.startLive(param, listener);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 停止实况
     */
    public void stopLive() {
        if (null != mRecvStreamThread) {
            mRecvStreamThread.interrupt();
        }

        try {
            OnStopLiveListener listener = new OnStopLiveListener() {
                @Override
                public void onStopLiveResult(long errorCode, String errorDesc) {
                    //errorCode为0表示成功
                    if (errorCode == 0) {
                        //Toast.makeText(PtzActivity.this,"停止实况成功",Toast.LENGTH_SHORT).show();
                    } else {
                        // Toast.makeText(PtzActivity.this,errorDesc,Toast.LENGTH_SHORT).show();
                    }
                }
            };
            //停止实况接口调用
            ServiceManager.stopLive(mPlayer.getPlaySession(), listener);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //停止Player播放
        mPlayer.AVStopPlay();
    }

    /**
     * 云台控制
     */
    public void ptzCommand(String cameraCode, int directionCode) {

        //云台命令参数
        PtzCommandParam param = new PtzCommandParam();
        param.setCameraCode(cameraCode);
        param.setCmd(directionCode);
        param.setSpeed1(5);
        param.setSpeed2(5);

        try {
            OnPtzCommandListener listener = new OnPtzCommandListener() {
                @Override
                public void onPtzCommandResult(long errorCode, String errorDesc) {
                    if (errorCode != 0) {
                        //Toast.makeText(PtzActivity.this, errorDesc, Toast.LENGTH_SHORT).show();
                    }
                }
            };
            //云台控制接口
            ServiceManager.ptzCommand(param, listener);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 云台锁定
     */
    public void lockPtz(String cameraCode) {

        LockPtzParam param = new LockPtzParam();
        param.setCameraCode(cameraCode);

        try {
            OnLockPtzListener listener = new OnLockPtzListener() {
                @Override
                public void onLockPtzResult(long errorCode, String errorDesc) {
                    if (errorCode != 0) {
                        //Toast.makeText(PtzActivity.this, errorDesc, Toast.LENGTH_SHORT).show();
                    } else {
                        //Toast.makeText(PtzActivity.this,"云台锁定成功",Toast.LENGTH_SHORT).show();
                    }
                }
            };
            //锁定云台接口调用
            ServiceManager.lockPtz(param, listener);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * 云台解锁
     */
    public void unLockPtz(String cameraCode) {

        LockPtzParam param = new LockPtzParam();
        param.setCameraCode(cameraCode);

        try {
            OnUnLockPtzListener listener = new OnUnLockPtzListener() {
                @Override
                public void onUnLockPtzResult(long errorCode, String errorDesc) {
                    if (errorCode != 0) {
                        //Toast.makeText(PtzActivity.this, errorDesc, Toast.LENGTH_SHORT).show();
                    } else {
                        //Toast.makeText(PtzActivity.this, "云台解锁成功", Toast.LENGTH_SHORT).show();
                    }
                }
            };
            //解锁云台接口调用
            ServiceManager.unLockPtz(param, listener);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //启动保活服务。若不想用提供的保活服务类，可通过每十秒调用保活接口ServiceManager.keepalive实现保活
        if (!mBound) {
            Intent intent = new Intent(this, KeepaliveService.class);
            startService(intent);
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        }
    }


    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            KeepaliveService.KeepaliveBinder binder = (KeepaliveService.KeepaliveBinder) service;
            mService = binder.getService();
            mService.start(CamPlayerActivity.this);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    @Override
    protected void onStop() {
        //解除保活服务的绑定
        if (mBound) {
            unbindService(mConnection);
            mBound = false;

            if (mRequireLogout) {
                Intent serviceIntent = new Intent(CamPlayerActivity.this, KeepaliveService.class);
                stopService(serviceIntent);
            }
        }

        super.onStop();
    }


    @Override
    public void onKeepaliveFailed() {

        mRequireLogout = true;
        Intent intent = new Intent();
        intent.setClass(CamPlayerActivity.this, LoginActivity.class);
        startActivity(intent);
        Toast.makeText(CamPlayerActivity.this, "保活失败，已退出", Toast.LENGTH_LONG).show();
        finish();
    }
}
