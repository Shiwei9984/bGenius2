class CupCakeOrderScreenTest{
    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun selectOptionScreen_verifyContent(){
        val flavour = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subtotal = "$100"
        composeTestRule.setContent{
            CupcakeTheme{
                SelectionOptionScreen(subtotal = subtotal, options = flavour)
            }
        }
        flavour.forEach{flavour->
            composeTestRule.onNodeWithText(flavour).assertIsDisplayed()
        }
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.subtotal_price,
                subtotal
            )
        ).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
    }

}