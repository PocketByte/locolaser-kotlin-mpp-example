import org.w3c.dom.Document
import kotlin.dom.*
import ru.pocketbyte.locolaser.example.presentation.MainScreenContract
import ru.pocketbyte.locolaser.example.repository.Repository

class MainScreenView(document: Document): MainScreenContract.View {

    private val textTitle = document.getElementById("text_title")
    private val text1 = document.getElementById("text_1")
    private val text2 = document.getElementById("text_2")

    init {
        textTitle?.appendText(Repository.str.app_name)
    }

    override fun showMessage1(message: String) {
        text1?.clear()
        text1?.appendText(message)
    }

    override fun showMessage2(message: String) {
        text2?.clear()
        text2?.appendText(message)
    }
}