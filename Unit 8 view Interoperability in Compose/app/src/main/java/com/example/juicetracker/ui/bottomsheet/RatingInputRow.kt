@Composable
fun RatingInputRow(rating:Int, onRatingChanged:(Int)->Unit,modifier:Modifier=Modifier){
    InputRow(inputLabel = stringResource(R.string.rating), modifier = modifier) {
        AndroidView(
            modifier = Modifier.padding(40.dp),
            factory = { context ->
                RatingBar(context).apply {
                    stepSize = 1f
                }
            },
            update = { ratingBar ->
                ratingBar.rating = rating.toFloat()
                ratingBar.setOnRatingBarChangeListener { _, _, _ ->
                    onRatingChange(ratingBar.rating.toInt())
                }
            }
        )
    }
}