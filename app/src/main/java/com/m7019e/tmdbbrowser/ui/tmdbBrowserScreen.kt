package com.m7019e.tmdbbrowser.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.m7019e.tmdbbrowser.R

@Composable
fun TmdbBrowserApp() {
    Text(text = stringResource(id = R.string.app_name))
}

@Preview(showBackground = true)
@Composable
fun TmdbBrowserAppPreview() {
    TmdbBrowserApp()
}
