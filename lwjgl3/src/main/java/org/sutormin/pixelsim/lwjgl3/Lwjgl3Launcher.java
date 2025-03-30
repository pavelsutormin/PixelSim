package org.sutormin.pixelsim.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.glutils.HdpiMode;
import org.sutormin.pixelsim.Main;

/** Launches the desktop (LWJGL3) application. */
public class Lwjgl3Launcher {
    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return; // This handles macOS support and helps on Windows.
        createApplication();
    }

    private static Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new Main(), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("PixelSim");
        configuration.setHdpiMode(HdpiMode.Pixels);
        configuration.useVsync(true);
        configuration.setForegroundFPS(
            Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1);
        configuration.setWindowedMode(
            Lwjgl3ApplicationConfiguration.getDisplayMode().width,
            Lwjgl3ApplicationConfiguration.getDisplayMode().height);
        configuration.setResizable(true);
        configuration.setWindowIcon("logo.png");
        return configuration;
    }
}
