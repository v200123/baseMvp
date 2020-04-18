package com.example.newlocation02.fragment


import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.newlocation02.R
import com.example.newlocation02.mvp.IPlayMusicView
import com.example.newlocation02.mvp.presenter.PlayMusicPresenter
import com.lc.basemvp.BaseFragment
import com.lc.basemvp.out
import com.lc.newlocation.click
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.fragment_playmusic.*
import java.util.*

class PlayMusicFragment : BaseFragment<IPlayMusicView, PlayMusicPresenter>() {

    private val musicPlay by lazy { MediaPlayer()}
    private val list = listOf<String>("蔡健雅 - 双栖动物.mp3", "Galen Crew - lifter.mp3")
    private var position = 0
    private var openFd:AssetFileDescriptor?= null
    override fun createPresenter(): PlayMusicPresenter = PlayMusicPresenter()

    override fun getLayoutId(): Int = R.layout.fragment_playmusic



    override fun initView() {
        button5.click {"我被点击了".out()}
        test_01.apply { click { "开始计时了".out()
            start()
        }
        }
        button.setOnClickListener { musicPlay.start()
           progressBar.max =  musicPlay.duration
            Timer().schedule(object : TimerTask() {
             override fun run() {
                 (mContext as AppCompatActivity).runOnUiThread {
                     progressBar.progress = musicPlay.currentPosition
                     showStatus.text = "当前的时间${musicPlay.currentPosition}"
                 }
             }

         },0,1000)
        }
        goto_Indicator.click { Navigation.findNavController(it).navigate(R.id.action_playMusicFragment_to_createIndicator) }
        button2.setOnClickListener { musicPlay.pause() }
        button4.setOnClickListener {
            val assetManager = mContext.assets
            openFd = assetManager.openFd(
                list[if (++position % list.size - 1 == 0) position else {
                    position = 0
                    0
                }]
            )
            with(musicPlay) {
                reset()
                setDataSource(openFd!!.fileDescriptor, openFd!!.startOffset, openFd!!.length)
                prepare()
            }

        }


    }

    override fun initData() {
//        val assetManager = mContext.assets
//        val openFd = assetManager.openFd(list[position])
//        with(musicPlay) {
//            setDataSource(openFd.fileDescriptor, openFd.startOffset, openFd.length)
//            prepare()
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        musicPlay.release()
        openFd?.close()
    }

    override fun showMsg(msg: String) {
    }
}