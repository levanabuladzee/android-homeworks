package abuladze.levan.homework5

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    lateinit var dvd: ImageView
    lateinit var dvdLayout: ConstraintLayout
    var h: Float = 0f
    var w: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dvd = findViewById(R.id.dvd)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        dvdLayout = findViewById(R.id.dvdLayout)
        h = dvdLayout.height.toFloat()
        w = dvdLayout.width.toFloat()
        println("height = $h")
        println("width = $w")
        val handler = Handler(Looper.getMainLooper())
        handler.post(object : Runnable {
            override fun run() {
                valueAnimator(dvd)
                handler.postDelayed(this, 2000L * 7)
            }
        })
    }

    private fun valueAnimator(view: View) {
        val dvdWidth = dvd.width.toFloat()
        val dvdHeight = dvd.height.toFloat()

        val vax1 = ValueAnimator.ofFloat(0f, w - (dvdWidth * 1.5f))
        val vay1 = ValueAnimator.ofFloat(0f, h / 2 - (dvdHeight * 1.5f))

        val vax2 = ValueAnimator.ofFloat(w - (dvdWidth * 1.5f), 0f)
        val vay2 = ValueAnimator.ofFloat(h / 2 - (dvdHeight * 1.5f), h - dvdHeight)

        val vax3 = ValueAnimator.ofFloat(0f, -(w - (dvdWidth * 1.5f)))
        val vay3 = ValueAnimator.ofFloat(h - dvdHeight, h / 2)

        val vax4 = ValueAnimator.ofFloat(-(w - (dvdWidth * 1.5f)), w - (dvdWidth * 1.5f))
        val vay4 = ValueAnimator.ofFloat(h / 2, h / 2 - (dvdHeight * 2.0f))

        val vax5 = ValueAnimator.ofFloat(w - (dvdWidth * 1.5f), -(w - (dvdWidth * 1.5f)))
        val vay5 = ValueAnimator.ofFloat(h / 2 - (dvdHeight * 2.0f), 0f)

        val vax6 = ValueAnimator.ofFloat(-(w - (dvdWidth * 1.5f)), -150f)
        val vay6 = ValueAnimator.ofFloat(0f, h - dvdHeight)

        val vax7 = ValueAnimator.ofFloat(-150f, 0f)
        val vay7 = ValueAnimator.ofFloat(h - dvdHeight, 0f)
        val duration = 2000L

        vax1.duration = duration
        vay1.duration = duration

        vax2.duration = duration
        vax2.startDelay = duration
        vay2.duration = duration
        vay2.startDelay = duration

        vax3.duration = duration
        vax3.startDelay = duration * 2
        vay3.duration = duration
        vay3.startDelay = duration * 2

        vax4.duration = duration
        vax4.startDelay = duration * 3
        vay4.duration = duration
        vay4.startDelay = duration * 3

        vax5.duration = duration
        vax5.startDelay = duration * 4
        vay5.duration = duration
        vay5.startDelay = duration * 4

        vax6.duration = duration
        vax6.startDelay = duration * 5
        vay6.duration = duration
        vay6.startDelay = duration * 5

        vax7.duration = duration
        vax7.startDelay = duration * 6
        vay7.duration = duration
        vay7.startDelay = duration * 6

        vax1.addUpdateListener {
            view.translationX = it.animatedValue as Float
        }
        vay1.addUpdateListener {
            view.translationY = it.animatedValue as Float
            dvd.setImageResource(R.drawable.ic_dvdlogo_01)
        }

        vax2.addUpdateListener {
            view.translationX = it.animatedValue as Float
        }
        vay2.addUpdateListener {
            view.translationY = it.animatedValue as Float
            dvd.setImageResource(R.drawable.ic_dvdlogo_02)
        }

        vax3.addUpdateListener {
            view.translationX = it.animatedValue as Float
        }
        vay3.addUpdateListener {
            view.translationY = it.animatedValue as Float
            dvd.setImageResource(R.drawable.ic_dvdlogo_03)
        }

        vax4.addUpdateListener {
            view.translationX = it.animatedValue as Float
        }
        vay4.addUpdateListener {
            view.translationY = it.animatedValue as Float
            dvd.setImageResource(R.drawable.ic_dvdlogo_04)
        }

        vax5.addUpdateListener {
            view.translationX = it.animatedValue as Float
        }
        vay5.addUpdateListener {
            view.translationY = it.animatedValue as Float
            dvd.setImageResource(R.drawable.ic_dvdlogo_05)
        }

        vax6.addUpdateListener {
            view.translationX = it.animatedValue as Float
        }
        vay6.addUpdateListener {
            view.translationY = it.animatedValue as Float
            dvd.setImageResource(R.drawable.ic_dvdlogo_06)
        }

        vax7.addUpdateListener {
            view.translationX = it.animatedValue as Float
        }
        vay7.addUpdateListener {
            view.translationY = it.animatedValue as Float
            dvd.setImageResource(R.drawable.ic_dvdlogo_07)
        }

        vax1.start()
        vay1.start()
        vax2.start()
        vay2.start()
        vax3.start()
        vay3.start()
        vax4.start()
        vay4.start()
        vax5.start()
        vay5.start()
        vax6.start()
        vay6.start()
        vax7.start()
        vay7.start()
    }
}