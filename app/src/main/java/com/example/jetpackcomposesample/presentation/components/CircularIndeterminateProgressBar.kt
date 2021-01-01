package com.example.jetpackcomposesample.presentation.components

import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.ConstraintSet
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp

@Composable
fun CircularIndeterminateProgressBar(
    isDisplayed: Boolean
) {
    if (isDisplayed) {
        WithConstraints(modifier = Modifier.fillMaxSize()) {
            val constraints = if (maxWidth < 600.dp) {
                circularProgressBarConstraints(0.3f)
            } else {
                circularProgressBarConstraints(0.7f)
            }
            ConstraintLayout(
                modifier = Modifier.fillMaxSize(),
                constraintSet = constraints
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.layoutId("progressBar")
                )
            }
        }
    }
}

private fun circularProgressBarConstraints(verticalBias: Float): ConstraintSet {
    return ConstraintSet {
        val guideline = createGuidelineFromTop(verticalBias)
        val progressBar = createRefFor("progressBar")

        constrain(progressBar) {
            top.linkTo(guideline)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    }
}