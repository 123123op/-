package dev.sora.protohax.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.view.WindowCompat
import com.google.accompanist.adaptive.calculateDisplayFeatures
import dev.sora.protohax.MyApplication
import dev.sora.protohax.R
import dev.sora.protohax.relay.service.AppService
import dev.sora.protohax.ui.components.PHaxApp
import dev.sora.protohax.ui.theme.MyApplicationTheme
import dev.sora.protohax.util.ContextUtils.readStringOrDefault
import dev.sora.protohax.util.ContextUtils.writeString
import dev.sora.relay.cheat.module.CheatModule
import dev.sora.relay.cheat.module.ModuleManager
import dev.sora.relay.game.GameSession


class MainActivity : ComponentActivity() {
    public lateinit var session: GameSession
    public  lateinit var cm:CheatModule
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MyApplicationTheme {
                val windowSize = calculateWindowSizeClass(this)
                val displayFeatures = calculateDisplayFeatures(this)

                PHaxApp(
                    windowSize = windowSize,
                    displayFeatures = displayFeatures
                )
            }
        }

    }

    companion object {
        private const val KEY_TARGET_PACKAGE_CACHE = "TARGET_PACKAGE"

        var targetPackage: String
            get() = MyApplication.instance.readStringOrDefault(KEY_TARGET_PACKAGE_CACHE, "")
            set(value) = MyApplication.instance.writeString(KEY_TARGET_PACKAGE_CACHE, value)
    }
}