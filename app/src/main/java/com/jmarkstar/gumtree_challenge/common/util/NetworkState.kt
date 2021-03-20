package com.jmarkstar.gumtree_challenge.common.util

import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities

interface NetworkState {
    var isConnected: Boolean
    var network: Network?
    var networkCapabilities: NetworkCapabilities?
    var linkProperties: LinkProperties?
}
