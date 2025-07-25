package com.delacrixmorgan.kingscup.data.card.model

import kingscup.composeapp.generated.resources.Res
import kingscup.composeapp.generated.resources.rules_beLikeBillyDescription
import kingscup.composeapp.generated.resources.rules_beLikeBillyEmoji
import kingscup.composeapp.generated.resources.rules_beLikeBillyInfo
import kingscup.composeapp.generated.resources.rules_beLikeBillyLabel
import kingscup.composeapp.generated.resources.rules_iWillTellYouWhatDescription
import kingscup.composeapp.generated.resources.rules_iWillTellYouWhatEmoji
import kingscup.composeapp.generated.resources.rules_iWillTellYouWhatInfo
import kingscup.composeapp.generated.resources.rules_iWillTellYouWhatLabel
import kingscup.composeapp.generated.resources.rules_lastOneLaughingDescription
import kingscup.composeapp.generated.resources.rules_lastOneLaughingEmoji
import kingscup.composeapp.generated.resources.rules_lastOneLaughingInfo
import kingscup.composeapp.generated.resources.rules_lastOneLaughingLabel
import kingscup.composeapp.generated.resources.rules_leftHandDescription
import kingscup.composeapp.generated.resources.rules_leftHandEmoji
import kingscup.composeapp.generated.resources.rules_leftHandInfo
import kingscup.composeapp.generated.resources.rules_leftHandLabel
import kingscup.composeapp.generated.resources.rules_noFirstNamesDescription
import kingscup.composeapp.generated.resources.rules_noFirstNamesEmoji
import kingscup.composeapp.generated.resources.rules_noFirstNamesInfo
import kingscup.composeapp.generated.resources.rules_noFirstNamesLabel
import kingscup.composeapp.generated.resources.rules_noPhonesDescription
import kingscup.composeapp.generated.resources.rules_noPhonesEmoji
import kingscup.composeapp.generated.resources.rules_noPhonesInfo
import kingscup.composeapp.generated.resources.rules_noPhonesLabel
import kingscup.composeapp.generated.resources.rules_noPointingDescription
import kingscup.composeapp.generated.resources.rules_noPointingEmoji
import kingscup.composeapp.generated.resources.rules_noPointingInfo
import kingscup.composeapp.generated.resources.rules_noPointingLabel
import kingscup.composeapp.generated.resources.rules_noSwearingDescription
import kingscup.composeapp.generated.resources.rules_noSwearingEmoji
import kingscup.composeapp.generated.resources.rules_noSwearingInfo
import kingscup.composeapp.generated.resources.rules_noSwearingLabel
import kingscup.composeapp.generated.resources.rules_photographerDescription
import kingscup.composeapp.generated.resources.rules_photographerEmoji
import kingscup.composeapp.generated.resources.rules_photographerInfo
import kingscup.composeapp.generated.resources.rules_photographerLabel
import kingscup.composeapp.generated.resources.rules_tRexArmsDescription
import kingscup.composeapp.generated.resources.rules_tRexArmsEmoji
import kingscup.composeapp.generated.resources.rules_tRexArmsInfo
import kingscup.composeapp.generated.resources.rules_tRexArmsLabel
import kingscup.composeapp.generated.resources.rules_toiletDescription
import kingscup.composeapp.generated.resources.rules_toiletEmoji
import kingscup.composeapp.generated.resources.rules_toiletInfo
import kingscup.composeapp.generated.resources.rules_toiletLabel
import kingscup.composeapp.generated.resources.rules_vikingDescription
import kingscup.composeapp.generated.resources.rules_vikingEmoji
import kingscup.composeapp.generated.resources.rules_vikingInfo
import kingscup.composeapp.generated.resources.rules_vikingLabel
import org.jetbrains.compose.resources.StringResource

sealed class Joker(
    id: String,
    emoji: StringResource,
    label: StringResource,
    description: StringResource,
    info: StringResource
) : Rule(id, emoji, label, description, info) {

    data object Viking : Rule(
        id = Viking::class.simpleName.toString(),
        emoji = Res.string.rules_vikingEmoji,
        label = Res.string.rules_vikingLabel,
        description = Res.string.rules_vikingDescription,
        info = Res.string.rules_vikingInfo
    )

    data object NoFirstNames : Rule(
        id = NoFirstNames::class.simpleName.toString(),
        emoji = Res.string.rules_noFirstNamesEmoji,
        label = Res.string.rules_noFirstNamesLabel,
        description = Res.string.rules_noFirstNamesDescription,
        info = Res.string.rules_noFirstNamesInfo
    )

    data object NoPointing : Rule(
        id = NoPointing::class.simpleName.toString(),
        emoji = Res.string.rules_noPointingEmoji,
        label = Res.string.rules_noPointingLabel,
        description = Res.string.rules_noPointingDescription,
        info = Res.string.rules_noPointingInfo
    )

    data object TRexArms : Rule(
        id = TRexArms::class.simpleName.toString(),
        emoji = Res.string.rules_tRexArmsEmoji,
        label = Res.string.rules_tRexArmsLabel,
        description = Res.string.rules_tRexArmsDescription,
        info = Res.string.rules_tRexArmsInfo
    )

    data object BeLikeBilly : Rule(
        id = BeLikeBilly::class.simpleName.toString(),
        emoji = Res.string.rules_beLikeBillyEmoji,
        label = Res.string.rules_beLikeBillyLabel,
        description = Res.string.rules_beLikeBillyDescription,
        info = Res.string.rules_beLikeBillyInfo
    )

    data object IWillTellYouWhat : Rule(
        id = IWillTellYouWhat::class.simpleName.toString(),
        emoji = Res.string.rules_iWillTellYouWhatEmoji,
        label = Res.string.rules_iWillTellYouWhatLabel,
        description = Res.string.rules_iWillTellYouWhatDescription,
        info = Res.string.rules_iWillTellYouWhatInfo
    )

    data object NoPhones : Rule(
        id = NoPhones::class.simpleName.toString(),
        emoji = Res.string.rules_noPhonesEmoji,
        label = Res.string.rules_noPhonesLabel,
        description = Res.string.rules_noPhonesDescription,
        info = Res.string.rules_noPhonesInfo
    )

    data object NoSwearing : Rule(
        id = NoSwearing::class.simpleName.toString(),
        emoji = Res.string.rules_noSwearingEmoji,
        label = Res.string.rules_noSwearingLabel,
        description = Res.string.rules_noSwearingDescription,
        info = Res.string.rules_noSwearingInfo
    )

    data object Photographer : Rule(
        id = Photographer::class.simpleName.toString(),
        emoji = Res.string.rules_photographerEmoji,
        label = Res.string.rules_photographerLabel,
        description = Res.string.rules_photographerDescription,
        info = Res.string.rules_photographerInfo
    )

    data object LastOneLaughing : Normal(
        id = LastOneLaughing::class.simpleName.toString(),
        emoji = Res.string.rules_lastOneLaughingEmoji,
        label = Res.string.rules_lastOneLaughingLabel,
        description = Res.string.rules_lastOneLaughingDescription,
        info = Res.string.rules_lastOneLaughingInfo
    )

    data object LeftHand : Normal(
        id = LeftHand::class.simpleName.toString(),
        emoji = Res.string.rules_leftHandEmoji,
        label = Res.string.rules_leftHandLabel,
        description = Res.string.rules_leftHandDescription,
        info = Res.string.rules_leftHandInfo
    )

    data object Toilet : Normal(
        id = Toilet::class.simpleName.toString(),
        emoji = Res.string.rules_toiletEmoji,
        label = Res.string.rules_toiletLabel,
        description = Res.string.rules_toiletDescription,
        info = Res.string.rules_toiletInfo
    )
}