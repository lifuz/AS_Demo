package com.prd.testpicupload.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.prd.testpicupload.R;


/**
 * 获取图片路径的选择popupWindow
 * <p/>
 * 作者：李富 on 2016/2/23.
 * 邮箱：lifuzz@163.com
 */
public class SelectPicPopup extends PopupWindow {

    private Button btn_take_photo, btn_pic_photo;
    private View menuView;

    public SelectPicPopup(Context context, View.OnClickListener itemOnClick) {
        super(context);

        LayoutInflater inflater = LayoutInflater.from(context);

        menuView = inflater.inflate(R.layout.pic_alert_dialog, null);

        //初始化组件
        btn_pic_photo = (Button) menuView.findViewById(R.id.btn_pick_photo);
        btn_take_photo = (Button) menuView.findViewById(R.id.btn_take_photo);

        //设置监听事件
        btn_pic_photo.setOnClickListener(itemOnClick);
        btn_take_photo.setOnClickListener(itemOnClick);

        //设置SelectPicPopup的view
        this.setContentView(menuView);

        //设置SelectPicPopup的宽和高
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        //设置SelectPicPopup弹出窗体可点击
        this.setFocusable(true);

        //设置SelectPicPopup弹出窗体背景为办透明
        ColorDrawable colorDrawable = new ColorDrawable(0xb0000000);
        this.setBackgroundDrawable(colorDrawable);


        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        menuView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int height = menuView.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }

                return true;
            }
        });

    }
}
