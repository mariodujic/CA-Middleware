package com.groundzero.camw.features.thoughts.constants

import com.groundzero.camw.core.data.DataType

sealed class ThoughtDataType {
    object English : DataType.English(THOUGHT_EN_COLLECTION)
    object EnglishStaging : DataType.EnglishStaging(THOUGHT_EN_COLLECTION_STAGING)
    object Croatian : DataType.Croatian(THOUGHT_HR_COLLECTION)
    object CroatianStaging : DataType.CroatianStaging(THOUGHT_HR_COLLECTION_STAGING)
    object Slovak : DataType.Croatian(THOUGHT_SK_COLLECTION)
    object SlovakStaging : DataType.CroatianStaging(THOUGHT_SK_COLLECTION_STAGING)
    object Spanish : DataType.Spanish(THOUGHT_ES_COLLECTION)
    object SpanishStaging : DataType.SpanishStaging(THOUGHT_ES_COLLECTION_STAGING)
}