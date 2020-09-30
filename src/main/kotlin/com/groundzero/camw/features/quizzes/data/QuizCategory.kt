package com.groundzero.camw.features.quizzes.data

import com.groundzero.camw.core.data.NetworkModel

data class QuizCategory(
        override val itemId: String? = null,
        val text: String? = null,
        val title: String? = null,
        val quizzes: List<Quiz>? = null
) : NetworkModel