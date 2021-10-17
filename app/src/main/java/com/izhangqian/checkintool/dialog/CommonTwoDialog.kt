package com.izhangqian.checkintool.dialog

class CommonTwoDialog {
    fun build() : CommonTwoDialog {
        return this
    }

    class Builder() {
        var title : String = ""
        var postive : String = "确定"
        var negtive : String = "取消"
        fun setPositive(text: String = "确定", callback : () -> Unit) {
            postive = text
        }

        fun setNegTive(text : String = "取消", callback: () -> Unit) {
            negtive = text
        }
    }
}