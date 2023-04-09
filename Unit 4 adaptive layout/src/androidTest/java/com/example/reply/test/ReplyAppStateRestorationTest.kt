class ReplyAppStateRestorationTest{
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    @TestCompactWidth
    fun compactDevice_selectedEmailEmailRetained_afterConfigChange() {
        composeTestRule.onNodeWithText(LocalEmailsDataProvider.allEmail[2].body)
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(LocalEmailsDataProvider.allEmail[2].subject)
            .performClick()

        composeTestRule.onNodeWithContentDescriptionForStringId(
            R.string.navigation_back
        ).assertExists()

        composeTestRule.onNodeWithText(LocalEmailsDataProvider.allEmail[2].body).assertExists()

        stateRestorationTester.emulateSavedInstanceStateRestore()

        composeTestRule.onNodeWithContentDescriptionForStringId(
            R.string.navigation_back
        ).assertExists()
        composeTestRule.onNodeWithText(LocalEmailsDataProvider.allEmails[2].body).assertExist()
    }

    @Test
    @Test ExpandedWidth
    fun expandedDevice_selectedEmailEamilRetained_afterConfigChange(){
        composeTestRule.onNodeWithText(LocalEmailDataProvider.allEmails[2].body)
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(LocalEmailDataProvider.allEmails[2].subject)
            .performClick()

        composeTestRule.onNodeWithTagForStringId(R.string.details_screen).onChildren()
            .assertAny(hasAnyDescendant(hasText(LocalEmailDataProvider.allEmails[2].body)))

        stateRestorationTester.emulateSavedInstanceStateRestore()

        composeTestRule.onNodeWithTagForStringId(R.string.details_screen).onChildren()
            .assertAny(hasAnyDescendant(hasText(LocalEmailDataProvider.allEmails[2].body)))
    }
}