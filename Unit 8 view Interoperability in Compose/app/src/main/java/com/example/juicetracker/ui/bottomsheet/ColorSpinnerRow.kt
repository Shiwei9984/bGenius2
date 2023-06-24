class SpinnerAdapter(val onColorChange:(Int)->Unit): AdapterView.OnItemSelectedListener{
    override fun onItemSelected(parent:AdapterView<*>?,view:View?,position:Int,id:Long){
        onColorChange(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?){
        onColorChange(JuiceColor.Red.ordinal)
    }
}

@Composable
fun ColorSpinnerRow(
    colorSpinnerPosition: Int,
    onColorChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val juiceColorArray =
        JuiceColor.values().map { juiceColor -> stringResource(juiceColor.label) }
    InputRow(inputLabel = stringResource(R.string.color), modifier = modifier) {
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->
                Spinner(context).apply {
                    adapter =
                        ArrayAdapter(
                            context,
                            android.R.layout.simple_spinner_dropdown_item,
                            juiceColorArray
                        )
                }
            },
            update = { spinner ->
                spinner.setSelection(colorSpinnerPosition)
                spinner.onItemSelectedListener = SpinnerAdapter(onColorChange)
            }
        )
    }
}