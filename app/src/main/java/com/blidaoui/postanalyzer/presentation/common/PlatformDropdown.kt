package com.blidaoui.postanalyzer.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.blidaoui.postanalyzer.domain.model.Platform

@Composable
fun PlatformDropdown(
    selectedPlatform: Platform,
    onSelect: (Platform) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(selectedPlatform.label)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            Platform.entries.forEach { platform ->
                DropdownMenuItem(
                    text = { Text(platform.label) },
                    onClick = {
                        onSelect(platform)
                        expanded = false
                    }
                )
            }
        }
    }
}
