package com.blidaoui.postanalyzer.presentation.common

import android.content.Intent
import android.widget.Toast
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ShareResultDialog(
    text: String,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("مشاركة النتيجة") },
        text = { Text("اختر تطبيق للمشاركة") },
        confirmButton = {
            Button(onClick = {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, text)
                    putExtra(Intent.EXTRA_SUBJECT, "تحليل المنشور بواسطة BliDaoui")
                }
                context.startActivity(Intent.createChooser(intent, "مشاركة عبر"))
                onDismiss()
            }) {
                Text("مشاركة")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("إلغاء")
            }
        }
    )
}

@Composable
fun SaveResultDialog(
    onDismiss: () -> Unit,
    onSave: () -> Unit
) {
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("حفظ النتيجة") },
        text = { Text("تم حفظ النتيجة محلياً في سجل التحليلات") },
        confirmButton = {
            Button(onClick = {
                onSave()
                Toast.makeText(context, "تم الحفظ بنجاح", Toast.LENGTH_SHORT).show()
                onDismiss()
            }) {
                Text("موافق")
            }
        }
    )
}
