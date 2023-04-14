import jdk.jfr.Description

class TestDisPatcherRule(val testDisPatcher: TestDisPatcher = UnconfinedTestDispatcher(),
): TestWatcher(){
    override fun starting(descrpition:Description){
        DisPatcher.setMain(testDisPatcher)
    }

    override fun finished(description: Description){
        DispatcherRule.resetMain()
    }
}