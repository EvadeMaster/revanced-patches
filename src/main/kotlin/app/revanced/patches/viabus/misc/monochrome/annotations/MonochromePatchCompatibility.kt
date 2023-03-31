package app.revanced.patches.viabus.misc.monochrome.annotations

import app.revanced.patcher.annotation.Compatibility
import app.revanced.patcher.annotation.Package

@Compatibility([Package("com.indyzalab.transitia")])
@Target(AnnotationTarget.CLASS)
internal annotation class MonochromePatchCompatibility