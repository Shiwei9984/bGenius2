object FakeDataSource{
    const val idOne = "img1"
    const val idTwo = "img2"
    const val imgOne = "ur1.1"
    const val imgTwo = "ur1.2"
    val photosList = listOf(
        MarsPhoto(
            id = idOne,
            imgSrc = imgOne
        ),
        MarsPhoto(
            id = idTwo,
            imgSrc = imgTwo
        )
    )
}