package com.izhangqian.checkintool.bean

class HomeItemBean(itemType: Int) {
    var itemType = itemType

    companion object {
        val VIEW_TYPE_DEFAULT = 0
        val VIEW_TYPE_PDD = 1;
    }
}