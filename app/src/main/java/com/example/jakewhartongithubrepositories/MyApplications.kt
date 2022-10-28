package com.example.jakewhartongithubrepositories

import android.app.Application
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplications : Application() {
    override fun onCreate() {
        super.onCreate()
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ ->
            ClassicsFooter(
                context
            ).setSpinnerStyle(SpinnerStyle.Translate);
        }
    }
}