package uz.lazydevv.instagramclone.extensions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

inline fun Modifier.clickableWithoutRipple(crossinline onClick: () -> Unit): Modifier {
    return composed {
        clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) {
            onClick()
        }
    }
}

inline fun Modifier.clickableOutlineRipple(crossinline onClick: () -> Unit): Modifier {
    return composed {
        clickable(
            indication = rememberRipple(bounded = false),
            interactionSource = remember { MutableInteractionSource() }
        ) {
            onClick()
        }
    }
}