package com.zaid.zavi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.zaid.zavi.theme.ZaviDecorTheme
import com.zaid.zavi.ui.register_screen.RegisterScreen
import com.zaid.zavi.utils.NetworkMonitor
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZaviDecorTheme {
                // A surface container using the 'background' color from the theme
//                ZaviApp(networkMonitor = networkMonitor)
                RegisterScreen()
//                  LoginScreen()
            }

        }
    }
}
