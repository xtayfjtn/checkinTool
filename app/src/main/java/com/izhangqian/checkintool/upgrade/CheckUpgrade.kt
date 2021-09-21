package com.izhangqian.checkintool.upgrade

import android.content.Context
import android.content.Intent
import android.os.Environment
import androidx.core.content.FileProvider
import com.izhangqian.checkintool.MyApplication
import com.izhangqian.checkintool.utils.Logit
import com.izhangqian.checkintool.webrequest.api.ControlGetRep
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL

object CheckUpgrade {
    const val TAG = "CheckUpgrade"
    var filePath : String = ""
    fun checkForUpdate(context: Context) {
        Logit.i(TAG, "start get apk config")
        val disposable = ControlGetRep.getNewApkConfig()
            .subscribeOn(Schedulers.io())
            .subscribe({
                val pkgInfo = context.packageManager.getPackageInfo(context.packageName, 0)
                val version = pkgInfo.versionCode
                Logit.i(TAG, "local version : $version, remote: ${it.apkVersion}")
                if (version < it.apkVersion) {
                    getNewApk(it.apkUrl)
                }

        }, {
            it.printStackTrace()
            Logit.e(TAG, "get config error: ${it.message}")
            })
    }

    private fun getNewApk(url : String) {

//        httpApk(url)
        retrofitApk(url)
        installApk()
    }

    fun retrofitApk(url: String) {

        filePath = "${MyApplication.application!!.filesDir}${File.separator}1002.apk"
        val file = File(filePath)
        if (file.exists()) {
            file.delete()
        }
        val response = ControlGetRep.getNewApk(url).execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            var inStream: InputStream? = null
            var outStream: OutputStream? = null
            try {
                inStream = body.byteStream()
                outStream = file.outputStream()
                val lenth = body.contentLength()
                //当前已下载长度
                var currentLength = 0L
                //缓冲区
                val buff = ByteArray(1024)
                var len = inStream.read(buff)
                var percent = 0
                while (len != -1) {
                    outStream.write(buff, 0, len)
                    currentLength += len
                    Logit.i(TAG, "current: $currentLength , all: $lenth percent: ${(currentLength * 100 / lenth).toInt()}")
                    /*不要频繁的调用切换线程,否则某些手机可能因为频繁切换线程导致卡顿,
                    这里加一个限制条件,只有下载百分比更新了才切换线程去更新UI*/
//                    if((currentLength * 100/ contentLength).toInt()>percent){
//                        percent = (currentLength / contentLength * 100).toInt()
//                        //更新完成UI之后立刻切回IO线程
//                    }

                    len = inStream.read(buff)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                inStream?.close()
                outStream?.close()
            }
        }

//            .subscribeOn(Schedulers.io())
//            .subscribe(object : Observer<ResponseBody?> {
//                var process = 0
//                override fun onSubscribe(d: Disposable) {
//                    Logit.i(TAG, "on subscribe")
//                }
//
//                override fun onNext(t: ResponseBody) {
//                    Logit.i(TAG, "on next")
//                    val inputStream = t.byteStream()
//                    val maxx = t.contentLength()
//                    filePath =
//                        "${Environment.getExternalStorageDirectory()}${File.separator}110.apk"
//                    Logit.i(TAG, "file path $filePath")
//                    val file = File(filePath)
//                    if (!file.exists()) {
//                        file.createNewFile()
//                    }
//
//                    val os = FileOutputStream(file)
//                    var bytes = ByteArray(1024)
//                    var rendLength = 0
//                    var currentLength = 0
//                    while (((inputStream.read(bytes)).also { rendLength = it }) != -1) {
//                        os.write(bytes, 0, rendLength)
//                        currentLength += rendLength
//                        Logit.i(TAG, "here is progress $currentLength and $maxx")
//                    }
//                    inputStream.close()
//                    os.close()
//                }
//
//                override fun onError(e: Throwable) {
//                    e.printStackTrace()
//                    Logit.i(TAG, "on  error ${e.message}")
//                }
//
//                override fun onComplete() {
//                    Logit.i(TAG, "start installing")
//
//                    val apkFile = File(filePath)
//                    val intent = Intent(Intent.ACTION_VIEW)
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//                    val contentUri = FileProvider.getUriForFile(
//                        MyApplication.application!!,
//                        MyApplication.application!!.packageName.toString() + ".fileProvider",
//                        apkFile
//                    )
//                    intent.setDataAndType(contentUri, "application/vnd.android.package-archive")
//                    MyApplication.application!!.startActivity(intent)
//                }
//            })
//        }
    }

    fun httpApk(url : String) {
        // 下载文件
        val conn = URL(url).openConnection() as HttpURLConnection
        conn.connect();
        val inputStream = conn.inputStream
        val length = conn.contentLength

        filePath = "${MyApplication.application!!.filesDir}${File.separator}1002.apk"
        val file = File(filePath)
        if (file.exists()) {
            file.delete()
        }
        val fos = FileOutputStream(file);

        var count = 0;
        val buffer = ByteArray(1024)
        val mIsCancel = false
        while (!mIsCancel){
            var numread = inputStream.read(buffer);
            count += numread;
            Logit.i(TAG, "current: $count all: $length")
            // 计算进度条的当前位置
//            mProgress = (int) (((float)count/length) * 100);

            // 下载完成
            if (numread < 0){
                break;
            }
            fos.write(buffer, 0, numread);
        }
        fos.close();
        inputStream.close();
    }

    fun installApk() {
        val apkFile = File(filePath)
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        val contentUri = FileProvider.getUriForFile(MyApplication.application!!, MyApplication.application!!.packageName.toString() + ".fileProvider", apkFile)
        intent.setDataAndType(contentUri, "application/vnd.android.package-archive")
        MyApplication.application!!.startActivity(intent)

    }
}