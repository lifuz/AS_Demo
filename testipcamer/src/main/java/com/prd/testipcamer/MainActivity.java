package com.prd.testipcamer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.prd.testipcamer.service.BridgeService;
import com.prd.testipcamer.utils.ContentCommon;
import com.prd.testipcamer.utils.SystemValue;

import vstc2.nativecaller.NativeCaller;
public class MainActivity extends Activity implements
        BridgeService.IpcamClientInterface,BridgeService.CallBackMessageInterface {


    private static final String STR_MSG_PARAM = "msgparam";
    private static final String STR_DID = "did";
    private Intent intentbrod = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        intent.setClass(MainActivity.this, BridgeService.class);
        startService(intent);
        NativeCaller.PPPPInitialOther("ADCBBFAOPPJAHGJGBBGLFLAGDBJJHNJGGMBFBKHIBBNKOKLDHOBHCBOEHOKJJJKJBPMFLGCPPJMJAPDOIPNL");

        BridgeService.setCallBackMessage(this);

        intentbrod = new Intent("drop");

        login();

    }

    private void login() {

        Intent in = new Intent();
        String strUser = "admin";
        String strPwd = "888888";
        String strDid = "VSTB241006PXDWG";

        in.putExtra(ContentCommon.CAMERA_OPTION, ContentCommon.ADD_CAMERA);
        in.putExtra(ContentCommon.STR_CAMERA_ID, strDid);
        in.putExtra(ContentCommon.STR_CAMERA_USER, strUser);
        in.putExtra(ContentCommon.STR_CAMERA_PWD, strPwd);
        in.putExtra(ContentCommon.STR_CAMERA_TYPE, ContentCommon.CAMERA_TYPE_MJPEG);

        SystemValue.deviceName = strUser;
        SystemValue.deviceId = strDid;
        SystemValue.devicePass = strPwd;
        BridgeService.setIpcamClientInterface(this);

        NativeCaller.Init();
        new Thread(new StartPPPPThread()).start();

    }

    class StartPPPPThread implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(100);
                startCameraPPPP();
            } catch (Exception e) {

            }
        }
    }
    private void startCameraPPPP() {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
        }
        int result = NativeCaller.StartPPPP(SystemValue.deviceId, SystemValue.deviceName,
                SystemValue.devicePass,1,"");
        Log.i("ip", "result:" + result);
    }


    @Override
    public void BSMsgNotifyData(String did, int type, int param) {

        Log.d("ip", "type:" + type + " param:" + param);
        Bundle bd = new Bundle();
        Message msg = PPPPMsgHandler.obtainMessage();
        msg.what = type;
        bd.putInt(STR_MSG_PARAM, param);
        bd.putString(STR_DID, did);
        msg.setData(bd);
        PPPPMsgHandler.sendMessage(msg);
        if (type == ContentCommon.PPPP_MSG_TYPE_PPPP_STATUS) {
            intentbrod.putExtra("ifdrop", param);
            sendBroadcast(intentbrod);
        }

    }

    private Handler PPPPMsgHandler = new Handler() {
        public void handleMessage(Message msg) {

            Bundle bd = msg.getData();
            int msgParam = bd.getInt(STR_MSG_PARAM);
            int msgType = msg.what;
            Log.i("aaa", "===="+msgType+"--msgParam:"+msgParam);
            String did = bd.getString(STR_DID);
            switch (msgType) {
                case ContentCommon.PPPP_MSG_TYPE_PPPP_STATUS:
                    int resid;
                    switch (msgParam) {
                        case ContentCommon.PPPP_STATUS_CONNECTING://0
                            resid = R.string.pppp_status_connecting;

                            break;
                        case ContentCommon.PPPP_STATUS_CONNECT_FAILED://3
                            resid = R.string.pppp_status_connect_failed;

                            break;
                        case ContentCommon.PPPP_STATUS_DISCONNECT://4
                            resid = R.string.pppp_status_disconnect;

                            break;
                        case ContentCommon.PPPP_STATUS_INITIALING://1
                            resid = R.string.pppp_status_initialing;

                            break;
                        case ContentCommon.PPPP_STATUS_INVALID_ID://5
                            resid = R.string.pppp_status_invalid_id;

                            break;
                        case ContentCommon.PPPP_STATUS_ON_LINE://2 在线状态
                            resid = R.string.pppp_status_online;
                            //摄像机在线之后读取摄像机类型
                            String cmd="get_status.cgi?loginuse=admin&loginpas=" + SystemValue.devicePass
                                    + "&user=admin&pwd=" + SystemValue.devicePass;
                            NativeCaller.TransferMessage(did, cmd, 1);

                            Intent intent = new Intent(MainActivity.this,
                                    PlayActivity.class);
                            startActivity(intent);
                            break;
                        case ContentCommon.PPPP_STATUS_DEVICE_NOT_ON_LINE://6
                            resid = R.string.device_not_on_line;

                            break;
                        case ContentCommon.PPPP_STATUS_CONNECT_TIMEOUT://7
                            resid = R.string.pppp_status_connect_timeout;

                            break;
                        case ContentCommon.PPPP_STATUS_CONNECT_ERRER://8
                            resid =R.string.pppp_status_pwd_error;

                            break;
                        default:
                            resid = R.string.pppp_status_unknown;
                    }

                    Log.e("tag",getString(resid));

                    if (msgParam == ContentCommon.PPPP_STATUS_ON_LINE) {
                        NativeCaller.PPPPGetSystemParams(did,
                                ContentCommon.MSG_TYPE_GET_PARAMS);
                    }
                    if (msgParam == ContentCommon.PPPP_STATUS_INVALID_ID
                            || msgParam == ContentCommon.PPPP_STATUS_CONNECT_FAILED
                            || msgParam == ContentCommon.PPPP_STATUS_DEVICE_NOT_ON_LINE
                            || msgParam == ContentCommon.PPPP_STATUS_CONNECT_TIMEOUT
                            || msgParam == ContentCommon.PPPP_STATUS_CONNECT_ERRER) {
                        NativeCaller.StopPPPP(did);
                    }
                    break;
                case ContentCommon.PPPP_MSG_TYPE_PPPP_MODE:
                    break;

            }

        }
    };

    @Override
    public void BSSnapshotNotify(String did, byte[] bImage, int len) {

    }

    @Override
    public void callBackUserParams(String did, String user1, String pwd1, String user2, String pwd2, String user3, String pwd3) {

    }

    @Override
    public void CameraStatus(String did, int status) {

    }

    @Override
    public void CallBackGetStatus(String did, String resultPbuf, int cmd) {

    }
}
