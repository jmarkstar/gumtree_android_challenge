package com.jmarkstar.gumtree_challenge.common.util

import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities

internal class NetworkStateImp : NetworkState {
    override var isConnected: Boolean = false
    override var network: Network? = null
    override var linkProperties: LinkProperties? = null
    override var networkCapabilities: NetworkCapabilities? = null
}
