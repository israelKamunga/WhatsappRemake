package com.isy_code.whatsappremake.Utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class TabRowItem(
    var title : String,
    var icon : ImageVector,
    var screen : @Composable ()->Unit
)
