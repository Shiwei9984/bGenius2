class ReplyAppTest{
    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    @Test
    @TestCompactWidth
    fun compactDevice_verifyUsingBottomNavigation(){
        composeTestRule.setContent{
            ReplyApp(
                windowSize = WindowWidthSizeClass.Compact
            )
        }
        composeTestRule.onNodeWithTagForStringId(
            R.string.navigation_bottom
        ).assertExists()
    }

    @Test
    @TestMediumWidth
    fun mediumDevice_verifyUsingNavigationRail(){
        composeTestRule.setContent{
            ReplyApp(
                windowSize = WindowWidthSizeClass.Medium
            )
        }
        composeTestRule.onNodeWithTagForStringId(
            R.string.navigation_rail
        ).assertExists()
    }

    @Test
    @TestExpandedWidth
    fun expandedDevice_verifyUsingNavigationRail(){
        composeTestRule.setContent{
            ReplyApp(
                windowSize = WindowWidthSizeClass.Expanded
            )
        }
        composeTestRule.onNodeWithTagForStringId(
            R.string.navigation_drawer
        ).assertExists()
    }
}