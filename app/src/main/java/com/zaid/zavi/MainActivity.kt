package com.zaid.zavi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.auth.FirebaseAuth
import com.zaid.zavi.core.presentation.zavi_app.ZaviApp
import com.zaid.zavi.core.utils.NetworkMonitor
import com.zaid.zavi.theme.ZaviDecorTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZaviDecorTheme {

                // A surface container using the 'background' color from the theme
                ZaviApp(networkMonitor = networkMonitor,firebaseAuth = firebaseAuth)
            }
        }
    }
}
