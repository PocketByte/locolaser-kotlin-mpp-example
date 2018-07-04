import kotlinx.cinterop.*

import platform.Foundation.*
import platform.UIKit.*

import ru.pocketbyte.locolaser.example.repository.IosStringRepository
import ru.pocketbyte.locolaser.example.repository.Repository

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
    lateinit var labelMessage: UILabel

    override fun viewDidLoad() {
        super.viewDidLoad()

        Repository.initInstance(IosStringRepository())

        this.labelMessage.text = Repository.str.screen_main_hello_text
    }
}
