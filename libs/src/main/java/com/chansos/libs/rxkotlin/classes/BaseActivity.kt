/*
 * Copyright (c) 2018. Create and edit by ChangedenChan.
 */

package com.chansos.libs.rxkotlin.classes

import android.os.Bundle
import android.view.Menu
import com.chansos.libs.rxkotlin.Kt
import com.chansos.libs.rxkotlin.interfaces.Autowire
import com.chansos.libs.rxkotlin.interfaces.Clickable
import com.chansos.libs.rxkotlin.interfaces.Initializable
import com.chansos.libs.rxkotlin.utils.LogUtils
import com.chansos.libs.rxkotlin.utils.ObjectUtils
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * Activity的基类
 * */
abstract class BaseActivity : RxAppCompatActivity(), Clickable,
    Initializable, Autowire {
    lateinit var self: BaseActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        self = this
        autowire()
        setContentView(getLayoutResId())
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Kt.App.add(self)
        initialize()
    }

    override fun onDestroy() {
        super.onDestroy()
        Kt.run {
            App.remove(self)
            UI.removeLoadingDialog(self)
            Handler.destory(self)
        }
        ObjectUtils.destory(self)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onMenuOpened(featureId: Int, menu: Menu?): Boolean {
        if (menu != null && menu.javaClass.simpleName == "MenuBuilder") {
            try {
                menu.javaClass.getDeclaredMethod("setOptionalIconsVisible", Boolean::class.java).run {
                    isAccessible = true
                    invoke(menu, true)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return super.onMenuOpened(featureId, menu)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        LogUtils.d("${this.javaClass.simpleName}:onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        LogUtils.d("${this.javaClass.simpleName}:onRestoreInstanceState")
    }
}