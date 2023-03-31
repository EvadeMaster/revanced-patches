package app.revanced.patches.viabus.misc.monochrome.patch

import app.revanced.patcher.annotation.Description
import app.revanced.patcher.annotation.Name
import app.revanced.patcher.annotation.Version
import app.revanced.patcher.data.ResourceContext
import app.revanced.patcher.patch.PatchResult
import app.revanced.patcher.patch.PatchResultError
import app.revanced.patcher.patch.PatchResultSuccess
import app.revanced.patcher.patch.ResourcePatch
import app.revanced.patcher.patch.annotations.Patch
import java.io.FileWriter
import java.nio.file.Files

@Name("force-monochrome-icon")
@Description("Force monochrome icon for apps that don't support it.")
@MonochromePatchCompatibility
@Version("0.0.1")
class MonochromeIconPatch : ResourcePatch {
    override fun execute(context: ResourceContext): PatchResult {
        val resDirectory = context["res"]
        if (!resDirectory.isDirectory) return PatchResultError("The res folder can not be found.")

        val mipmapV33Directory = resDirectory.resolve("mipmap-anydpi-v26")
        if (!mipmapV33Directory.isDirectory) Files.createDirectories(mipmapV33Directory.toPath())

        FileWriter(mipmapV33Directory.resolve("ic_launcher.xml")).use {
            it.write(
                "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                        "<adaptive-icon\n" +
                        "  xmlns:android=\"http://schemas.android.com/apk/res/android\">\n" +
                        "    <background android:drawable=\"@color/ic_launcher_background\" />\n" +
                        "    <foreground android:drawable=\"@mipmap/ic_launcher_foreground\" />\n" +
                        "    <monochrome android:drawable=\"@mipmap/ic_launcher_foreground\" />\n" +
                        "</adaptive-icon>"
            )
        }

        FileWriter(mipmapV33Directory.resolve("ic_launcher_round.xml")).use {
            it.write(
                "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                        "<adaptive-icon\n" +
                        "  xmlns:android=\"http://schemas.android.com/apk/res/android\">\n" +
                        "    <background android:drawable=\"@color/ic_launcher_background\" />\n" +
                        "    <foreground android:drawable=\"@mipmap/ic_launcher_foreground\" />\n" +
                        "    <monochrome android:drawable=\"@mipmap/ic_launcher_foreground\" />\n" +
                        "</adaptive-icon>"
            )
        }

        return PatchResultSuccess()
    }
}