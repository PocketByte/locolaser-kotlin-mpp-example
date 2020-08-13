import kotlin.browser.*
import i18next.*
import ru.pocketbyte.locolaser.example.presentation.MainScreenContract
import ru.pocketbyte.locolaser.example.presentation.MainScreenPresenter
import ru.pocketbyte.locolaser.kmpp.JsStringRepository
import ru.pocketbyte.locolaser.example.repository.Repository

fun main() {
    initI18n { _, _ ->
        // 5.3 Init JS Repository
        Repository.init(JsStringRepository(i18next))
        initPresentation()
    }
}

lateinit var presenter: MainScreenContract.Presenter

fun initPresentation() {
    presenter = MainScreenPresenter(MainScreenView(document))
    presenter.start()
}

private val remoteConfig = js("""{
                fallbackLng: 'en',
                debug: true,
                ns: ['strings'],
                defaultNS: 'strings',
                simplifyPluralSuffix: false,
                backend: {
                    loadPath: "./locales/{{lng}}/{{ns}}.json",
                    crossDomain: true
                }
            }""")

private fun initI18n(callback: (err: Any?, t: Any?) -> Unit) {
    i18next.use(i18nextXHRBackend)
            .use(i18nextBrowserLanguageDetector)
            .init(remoteConfig, callback)
}
