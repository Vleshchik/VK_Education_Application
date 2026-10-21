package com.example.vk_education_application.core.data

import com.example.vk_education_application.core.model.App
import com.example.vk_education_application.core.model.Category

/**
 * Единый источник захардкоженных данных.
 * Используется списком, витриной и карточкой приложения.
 */
object AppCatalog {

    val allApps: List<App> = listOf(
        App(
            id = 1,
            name = "Гильдия Героев: Экшен ММО РПГ",
            description = "Легендарный рейд героев в Фэнтези РПГ. Станьте героем гильдии и сразите мастера подземелья!",
            category = Category.GAME,
            iconUrl = "https://static.rustore.ru/imgproxy/APsbtHxkVa4MZ0DXjnIkSwFQ_KVIcqHK9o3gHY6pvOQ/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/393868735/content/ICON/3f605e3e-f5b3-434c-af4d-77bc5f38820e.png@webp",
        ),
        App(
            id = 2,
            name = "VK Мессенджер",
            description = "Общайтесь, звоните и делитесь моментами с близкими в одном приложении.",
            category = Category.APP,
            iconUrl = "https://static.rustore.ru/imgproxy/PTo8g-Giv9VHYo7_Rwxw_1wC07KtDM7eSJgAfMlv53s/preset:web_app_icon_160/plain/https://static.rustore.ru/3f3d7180-6eb9-45ad-8706-f467c6dcf82a@webp",
        ),
        App(
            id = 3,
            name = "Гонки на выживание",
            description = "Динамичные гонки с элементами выживания. Соревнуйтесь с друзьями!",
            category = Category.GAME,
            iconUrl = "https://static.rustore.ru/imgproxy/2XF1i0zgO_qvH4rF7v1nxXlzkMpfAwcrEO988Cp_3Ts/preset:web_app_icon_160/plain/https://static.rustore.ru/apk/2063561486/content/ICON/038f31d7-...", // вставьте ваш полный URL
        ),
        App(
            id = 4,
            name = "VK Видео: кино, сериалы, ТВ, мультфильмы и клипы",
            description = "Смотри кино, мультики, сериалы, ТВ онлайн, спортивные трансляции, короткие видео и фильмы бесплатно.",
            category = Category.APP,
            iconUrl = "https://static.rustore.ru/imgproxy/-fb8TfFrlYjwu7wYCEPuKsm4wWkTLBTx_0_a9GpeKfc/preset:web_app_icon_160/plain/https://static.rustore.ru/apk/2027823295/content/ICON/97486018-5785-424d-9cab-4fe496d27c76.png@webp",
        ),
        App(
            id = 5,
            name = "Mobile Legends: Bang Bang",
            description = "Присоединяйтесь к своим друзьям в захватывающем MOBA-противостоянии реальным оппонентам 5 на 5 — Mobile Legends: Bang Bang!",
            category = Category.GAME,
            iconUrl = "https://static.rustore.ru/imgproxy/X7OXZgI9_9LUUHXvjaDC1gRIQ7Ll1EcNPBfcKW7SqH8/preset:web_app_icon_160/plain/https://static.rustore.ru/2026/9/11/5b/apk/2063753242/content/ICON/279be594-f9a8-4d6c-8302-f2703d2e53f4.png@webp",
        ),
        App(
            id = 6,
            name = "VK Музыка: аудиокниги, песни, подкасты",
            description = "VK Музыка — это стриминговый сервис, где есть миллионы треков, подкасты, аудиокниги и радио.",
            category = Category.APP,
            iconUrl = "https://static.rustore.ru/imgproxy/UVOR3yUWAMokAu1Sx_GRohYCPrcEvWreCRkJJ3x_jGk/preset:web_app_icon_160/plain/https://static.rustore.ru/2026/2/10/ae/apk/313279/content/ICON/62b9b0e2-0e7c-4855-a70d-9d6811fa3b4c.jpg@webp",
        ),
        App(
            id = 7,
            name = "Viking Rise",
            description = "Викинги! Вы готовитесь погрузиться в удивительный мир Viking Rise!",
            category = Category.GAME,
            iconUrl = "https://static.rustore.ru/imgproxy/xmkwQ1aR5iOHU7h8jCyeI6CyLAnRoM3MulGY4bZJ6l0/preset:web_app_icon_160/plain/https://static.rustore.ru/2026/3/18/47/apk/2063634342/content/ICON/0a98bc33-7942-4b71-b39c-66ceba9fff4e.jpg@webp",
        ),
        App(
            id = 8,
            name = "СберБанк Онлайн",
            description = "СберБанк Онлайн — ваш надёжный помощник в ежедневных делах. Все финансовые и нефинансовые возможности доступны в одном приложении!",
            category = Category.APP,
            iconUrl = "https://static.rustore.ru/imgproxy/qriFjN8OV6VBF4CCbWcxPm7SL0Y0YtMfxTeJSzWZ1Rc/preset:web_app_icon_160/plain/https://static.rustore.ru/apk/462271/content/ICON/f1b3c68a-b734-48ce-b62f-490208d3fa0e.png@webp",
        ),
        App(
            id = 9,
            name = "Tanks Blitz",
            description = "Открывай Tanks Blitz — F2P массовый многопользовательский PVP-шутер.",
            category = Category.GAME,
            iconUrl = "https://static.rustore.ru/imgproxy/ugU_ndH6NnkHfQ7bQGOyUUHgsxLa4VUa2_0TclHEN0o/preset:web_app_icon_160/plain/https://static.rustore.ru/2026/9/18/12/apk/2063496338/content/ICON/d93ff605-ed80-4c18-bcdf-9258acf503ff.png@webp",
        ),
    )

    fun getById(id: Int): App? = allApps.firstOrNull { it.id == id }

    /** Ровно 9 приложений для витрины (3 страницы × 3) */
    fun getShowcaseApps(): List<App> = allApps.take(9)
}