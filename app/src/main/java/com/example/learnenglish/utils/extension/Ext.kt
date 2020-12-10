package com.example.learnenglish.utils.extension

import android.content.Intent
import android.os.Bundle

/**
 * Converts an intent into a [Bundle] suitable for use as fragment arguments.
 */
fun intentToFragmentArguments(intent: Intent?): Bundle {
    val arguments = Bundle()
    if (intent == null) {
        return arguments
    }

    intent.data?.let {
        arguments.putParcelable("_uri", it)
    }

    intent.extras?.let {
        arguments.putAll(intent.extras)
    }

    return arguments
}