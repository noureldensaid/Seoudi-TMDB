package com.movieDetails.data.utils

import java.text.NumberFormat
import java.util.Locale

fun formatRuntime(runtimeMin: Int?): String {
    if (runtimeMin == null || runtimeMin <= 0) return "—"
    val h = runtimeMin / 60
    val m = runtimeMin % 60
    return if (h > 0) "${h}h ${m}m" else "${m}m"
}

fun formatMoney(value: Int?): String {
    if (value == null) return "—"
    if (value == 0) return "$0"
    val nf = NumberFormat.getNumberInstance(Locale.US)
    return "$" + nf.format(value)
}