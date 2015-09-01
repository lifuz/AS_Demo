package com.lifuz.testdraw.chart;

import android.content.Context;
import android.content.Intent;

/**
 * 作者：李富 on 2015/8/25.
 * 邮箱：lifuzz@163.com
 */
public interface AChartAbstract {

    /**
     * 获取一个当前类型图标的Intent实例
     */
    public Intent getIntent(Context context);

}
