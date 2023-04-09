import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule

fun<A :ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, <A>.onNodeWithStringId(
@StringRes id: Int
): SemanticNodeInteraction = onNodeWithText(activity.getString(id))

private fun navigateToFlavorScreen(){
    composeTestRule.onNodeWithStringId(R.string.one_cupcake)
        .performClick()
    composeTestRule.onNodeWithStringId(R.string.chocolate)
        .performClick()
}

private fun navigateToPickupScreen(){
    navigaeToFlavorScreen()
    composeTestRule.onNodeWithStringId(R.string.next)
        .performClick()
}

private fun navigateToSummaryScreen(){
    navigateToPickupScreen()
    composeTestRule.onNodeWithText(getFormattedDate())
        .performClick()
    composeTestRule.onNodeWithText(R.string.next)
        .performClick()
}


