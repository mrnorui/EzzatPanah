package academy.nouri.mvp.ui.download

import academy.nouri.mvp.R
import academy.nouri.mvp.databinding.ActivityDownloaderBinding
import academy.nouri.mvp.utils.Utils
import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.downloader.Error
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

class DownloaderActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityDownloaderBinding

    //Other
    private val filePath = "https://dl2.soft98.ir/soft/h/HDCleaner.2.023.rar"
    private val fileName = "HDCleaner.2.023.rar"
    private var dirPath = ""
    private var downloadId = 0
    private var isPause = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDownloaderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Permission
            Dexter.withContext(this@DownloaderActivity)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {

                    }

                    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {}

                    override fun onPermissionRationaleShouldBeShown(p0: PermissionRequest?, p1: PermissionToken?) {

                    }
                })
            //Path
            dirPath = Utils.getRootDirPath(this@DownloaderActivity)
            //Download
            startBtn.setOnClickListener {
                downloadId = PRDownloader.download(filePath, dirPath, fileName)
                    .build()
                    .setOnStartOrResumeListener {
                        startBtn.visibility = View.GONE
                        downloadInfoLay.visibility = View.VISIBLE
                    }
                    .setOnPauseListener { }
                    .setOnCancelListener { downloadId = 0 }
                    .setOnProgressListener {
                        val progressPercent = it.currentBytes * 100 / it.totalBytes
                        downloaderProgress.progress = progressPercent.toInt()
                        downloaderSize.text = Utils.getProgressDisplay(it.currentBytes, it.totalBytes)
                    }
                    .start(object : OnDownloadListener {
                        override fun onDownloadComplete() {
                            Toast.makeText(
                                this@DownloaderActivity, "Completed :)",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        override fun onError(error: Error?) {
                            Log.e("DownloadErr", error?.serverErrorMessage.toString())
                        }
                    })
            }
            //Cancel
            downloaderCancel.setOnClickListener {
                PRDownloader.cancel(downloadId)
                startBtn.visibility = View.VISIBLE
                downloadInfoLay.visibility = View.GONE
            }
            //Pause
            downloaderPause.setOnClickListener {
                if (isPause) {
                    PRDownloader.pause(downloadId)
                    isPause = false
                    downloaderPause.text = "Resume"
                } else {
                    PRDownloader.resume(downloadId)
                    isPause = true
                    downloaderPause.text = "Pause"
                }
            }
        }
    }
}