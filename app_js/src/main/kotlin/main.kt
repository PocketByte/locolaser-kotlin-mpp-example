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
                ns: ['common'],
                defaultNS: 'common',
                backend: {
                    loadPath: "https://raw.githubusercontent.com/i18next/i18next-gitbook/master/locales/{{lng}}/{{ns}}.json",
                    crossDomain: true
                }
            }""")

private val localConfig = js("""{
                fallbackLng: 'base',
                debug: true,
                simplifyPluralSuffix: false,
                resources: {
                    en: {
                        translation: {
                            "app_name":"locolaser-kotlin-multiplatform-example",
                            "screen_main_plural_string_plural_0":"Plural: one apple",
                            "screen_main_plural_string_plural_1":"Plural: %d apples",
                            "screen_main_hello_text":"Hello Kotlin!"
                        }
                    }
                }
            }""")

private fun initI18n(callback: (err: Any?, t: Any?) -> Unit) {
    i18next.use(i18nextXHRBackend)
            .use(i18nextBrowserLanguageDetector)
            .init(localConfig, callback)
}
