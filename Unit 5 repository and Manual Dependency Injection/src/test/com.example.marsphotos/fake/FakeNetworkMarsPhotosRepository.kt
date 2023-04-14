class FakeNetworkMarsPhotosRepository{
    override suspend fun getMarsPhoto(): List<MarsPhoto>{
        return FakeDataSource.photoList
    }

    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() = runTest{
        val marsViewModel = MarsViewModel(
            marsPhotosRepository = FakeNetworkMarsPhotosRepository()
        )
        assertEquals(
            MarsUiState.Success("Success:${FakeDataSource.photoList.size} Mars" + "photos retrieved"),
            marsViewModel.marsUiState)
        )
    }
}