package com.delacrixmorgan.kingscup.ui.extensions

import com.delacrixmorgan.kingscup.data.card.model.Joker
import com.delacrixmorgan.kingscup.data.card.model.Rule

val jokerRules: List<Rule>
    get() = listOf(
        Joker.Viking,
        Joker.NoFirstNames,
        Joker.NoPointing,
        Joker.TRexArms,
        Joker.BeLikeBilly,
        Joker.IWillTellYouWhat,
        Joker.NoPhones,
        Joker.NoSwearing,
        Joker.Photographer,
        Joker.LastOneLaughing,
        Joker.LeftHand,
        Joker.Toilet
    )