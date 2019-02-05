import kotlinx.cinterop.*

import platform.Foundation.*
import platform.UIKit.*

import ru.pocketbyte.locolaser.example.presentation.MainScreenContract
import ru.pocketbyte.locolaser.example.presentation.MainScreenPresenter

fun main(args: Array<String>) {
    memScoped {
        val argc = args.size + 1
        val argv = (arrayOf("konan") + args).map { it.cstr.ptr }.toCValues()

        autoreleasepool {
            UIApplicationMain(argc, argv, null, NSStringFromClass(AppDelegate))
        }
    }
}

class AppDelegate : UIResponder, UIApplicationDelegateProtocol {

    @OverrideInit constructor() : super()

    companion object : UIResponderMeta(), UIApplicationDelegateProtocolMeta {}

    private var _window: UIWindow? = null
    override fun window() = _window
    override fun setWindow(window: UIWindow?) { _window = window }

}

@ExportObjCClass
class ViewController : UIViewController {

    @OverrideInit constructor(coder: NSCoder) : super(coder)

    @ObjCOutlet
    lateinit var labelMessage1: UILabel

    @ObjCOutlet
    lateinit var labelMessage2: UILabel

    override fun viewDidLoad() {
        super.viewDidLoad()

        val presenter: MainScreenContract.Presenter = MainScreenPresenter(ViewImpl(this))

        presenter.start()
    }
}

class ViewImpl(private val controller: ViewController): MainScreenContract.View {

    override fun showMessage1(message: String) {
        this.controller.labelMessage1.text = message
    }

    override fun showMessage2(message: String) {
        this.controller.labelMessage2.text = message
    }
}
