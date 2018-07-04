import kotlinx.cinterop.*

import sqlite3.*

import platform.Foundation.*
import platform.UIKit.*
import ru.pocketbyte.locolaser.example.presentation.MainScreenContract
import ru.pocketbyte.locolaser.example.presentation.MainScreenPresenter
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
    lateinit var label: UILabel

    private var presenter: MainScreenContract.Presenter? = null

    override fun viewDidLoad() {
        super.viewDidLoad()

        Repository.initInstance(IosStringRepository())

        this.presenter = MainScreenPresenter(MainViewImpl(this))
        this.presenter?.start()
    }
}

class MainViewImpl(private val viewController: ViewController): MainScreenContract.View {

    override fun showMessage(message: String) {
        viewController.label.text = message

        val dbPtr = nativeHeap.alloc<CPointerVar<sqlite3>>()

        if (sqlite3_open("dbpath.db", dbPtr.ptr) != SQLITE_OK) {
            throw SqlError("Cannot open db: ${sqlite3_errmsg(dbPtr.value)}")
        }
    }

}
