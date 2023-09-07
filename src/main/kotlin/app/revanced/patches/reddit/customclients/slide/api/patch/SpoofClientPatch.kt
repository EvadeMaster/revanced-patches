package app.revanced.patches.reddit.customclients.slide.api.patch

import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.fingerprint.method.impl.MethodFingerprintResult
import app.revanced.patcher.patch.annotation.CompatiblePackage
import app.revanced.patcher.patch.annotation.Patch
import app.revanced.patches.reddit.customclients.AbstractSpoofClientPatch
import app.revanced.patches.reddit.customclients.boostforreddit.api.fingerprints.GetClientIdFingerprint

@Patch(
    name = "Slide client spoof",
    description = "Spoofs the client in order to allow logging in. " +
            "The OAuth application type has to be \"Installed app\" " +
            "and the redirect URI has to be set to \"http://www.ccrama.me\".",
    compatiblePackages = [CompatiblePackage("me.ccrama.redditslide")]
)
object SpoofClientPatch : AbstractSpoofClientPatch(
    "http://www.ccrama.me", listOf(GetClientIdFingerprint)
) {
    private val clientId by clientIdOption

    override fun List<MethodFingerprintResult>.patchClientId(context: BytecodeContext) {
        first().mutableMethod.addInstructions(
            0,
    """
                     const-string v0, "$clientId"
                     return-object v0
                """
        )
    }
}
