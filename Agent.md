# 🤖 Agent Configuration Guide

هذا الملف يحتوي على التعليمات التقنية الكاملة للوكيل (Copilot Agent) لتطوير تطبيق محلل المنشورات الذكي.

---

## 📋 نظرة عامة على المشروع

**اسم التطبيق**: محلل المنشورات الذكي  
**المطور**: BliDaoui  
**الهدف**: تطبيق Android يحلل النصوص والصور والفيديوهات باستخدام Gemini AI ويقيّم أدائها على TikTok و Instagram و Facebook

---

## 🏗️ البنية المعمارية (Architecture)

- **Pattern**: MVVM + Clean Architecture
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose (Material 3)
- **DI**: Hilt
- **Database**: Room
- **Networking**: Retrofit + OkHttp
- **JSON**: Moshi
- **Image Loading**: Coil
- **Async**: Coroutines + Flow
- **Navigation**: Navigation Compose

---

## 📦 الاعتماديات الرئيسية

```kotlin
// في build.gradle.kts
dependencies {
    // Core
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    
    // Compose
    implementation(platform("androidx.compose:compose-bom:2024.09.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-compose:2.8.0")
    
    // Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    ksp("com.google.dagger:hilt-compiler:2.51.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    
    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
    
    // Retrofit + OkHttp
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.11.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.1")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.1")
    
    // Moshi
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")
    ksp("com.squareup.moshi:moshi-codegen:1.15.1")
    
    // Coil
    implementation("io.coil-kt:coil-compose:2.7.0")
}
```

---

## 🔐 إدارة مفتاح Gemini API

**أهم قاعدة**: لا تضع مفتاح API مباشرة في الكود!

### الخطوات:
1. أنشئ ملف `local.properties` في جذر المشروع:
   ```properties
   GEMINI_API_KEY=YOUR_KEY_HERE
   ```

2. اقرأ المفتاح في `build.gradle.kts`:
   ```kotlin
   val localProperties = java.util.Properties()
   val localPropertiesFile = rootProject.file("local.properties")
   if (localPropertiesFile.exists()) {
       localProperties.load(localPropertiesFile.inputStream())
   }
   
   buildConfigField(
       "String",
       "GEMINI_API_KEY",
       "\"${localProperties.getProperty("GEMINI_API_KEY", "")}\""
   )
   ```

3. أضف `local.properties` إلى `.gitignore`:
   ```gitignore
   local.properties
   ```

4. استخدم المفتاح في الكود:
   ```kotlin
   val apiKey = BuildConfig.GEMINI_API_KEY
   ```

---

## 📱 الصلاحيات المطلوبة (AndroidManifest.xml)

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.READ_MEDIA_IMAGES" />
<uses-permission android:name="android.permission.READ_MEDIA_VIDEO" />
<uses-permission
    android:name="android.permission.READ_EXTERNAL_STORAGE"
    android:maxSdkVersion="32" />
```

---

## 🎯 متطلبات SDK

```gradle
minSdk = 24
targetSdk = 35
compileSdk = 35
compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
kotlinOptions {
    jvmTarget = "17"
}
```

---

## 📂 هيكل المشروع المطلوب

```
app/
├── src/main/
│   ├── java/com/blidaoui/postanalyzer/
│   │   ├── MainActivity.kt
│   │   ├── PostAnalyzerApp.kt
│   │   ├── data/
│   │   │   ├── local/
│   │   │   │   ├── AnalysisDao.kt
│   │   │   │   ├── AnalysisDatabase.kt
│   │   │   │   └── AnalysisEntity.kt
│   │   │   ├── remote/
│   │   │   │   ├── GeminiApiService.kt
│   │   │   │   ├── GeminiRequest.kt
│   │   │   │   └── GeminiResponse.kt
│   │   │   └── repository/
│   │   │       └── AnalysisRepository.kt
│   │   ├── domain/
│   │   │   ├── model/
│   │   │   │   └── AnalysisResult.kt
│   │   │   └── usecase/
│   │   │       ├── AnalyzeTextUseCase.kt
│   │   │       ├── AnalyzeImageUseCase.kt
│   │   │       └── AnalyzeVideoUseCase.kt
│   │   ├── presentation/
│   │   │   ├── navigation/
│   │   │   │   └── AppNavigation.kt
│   │   │   ├── home/
│   │   │   │   ├── HomeScreen.kt
│   │   │   │   └── HomeViewModel.kt
│   │   │   ├── text/
│   │   │   │   ├── TextAnalysisScreen.kt
│   │   │   │   └── TextAnalysisViewModel.kt
│   │   │   ├── image/
│   │   │   │   ├── ImageAnalysisScreen.kt
│   │   │   │   └── ImageAnalysisViewModel.kt
│   │   │   ├── video/
│   │   │   │   ├── VideoAnalysisScreen.kt
│   │   │   │   └── VideoAnalysisViewModel.kt
│   │   │   ├── result/
│   │   │   │   ├── ResultScreen.kt
│   │   │   │   └── ResultViewModel.kt
│   │   │   ├── history/
│   │   │   │   ├── HistoryScreen.kt
│   │   │   │   └── HistoryViewModel.kt
│   │   │   └── common/
│   │   │       ├── CommonComposables.kt
│   │   │       └── Theme.kt
│   │   └── di/
│   │       ├── NetworkModule.kt
│   │       ├── DatabaseModule.kt
│   │       └── RepositoryModule.kt
│   └── res/
│       ├── values/
│       │   ├── strings.xml
│       │   └── colors.xml
│       └── drawable/
├── build.gradle.kts
└── local.properties (مستثنى من git)
```

---

## 🎨 المتطلبات الوظيفية

### الشاشة الرئيسية (Home)
- عنوان: "محلل المنشورات الذكي"
- subtitle: "تطوير: BliDaoui"
- ثلاثة أزرار: تحليل نص، تحليل صورة، تحليل فيديو
- زر رابع: السجل

### شاشات التحليل (Text/Image/Video)
- حقل إدخال أو اختيار ملف
- قائمة منسدلة لاختيار المنصة (TikTok/Instagram/Facebook)
- زر "تحليل" مع مؤشر تحميل
- عرض رسالة خطأ عند الفشل

### شاشة النتائج (Results)
- التقييم العام (0-100) مع شريط تقدم ملوّن
- نقاط القوة (قائمة)
- نقاط الضعف (قائمة)
- اقتراحات التحسين (قائمة)
- توقع الأداء (عالي/متوسط/منخفض)
- التفسير المفصّل
- أزرار: حفظ، مشاركة، تحليل جديد

### شاشة السجل (History)
- عرض جميع التحليلات السابقة
- حذف تحليل
- فتح تحليل قديم

---

## 📊 معايير التحليل

### TikTok
- قوة الخطاف في أول 1-3 ثواني (الأهم)
- معدل الإكمال (يجب أن يتجاوز 70%)
- قابلية إعادة المشاهدة
- قابلية المشاركة والحفظ

### Instagram
- معدل الإكمال ومشاهدة الفيديو كاملاً
- عدد مرات إعادة المشاهدة
- المشاركة عبر الرسائل الخاصة (DM)
- جودة الخطاف

### Facebook
- التفاعل النوعي (تعليقات، مشاركات)
- وقت المشاهدة الكلي
- ارتباط المحتوى باهتمامات الجمهور

---

## 🔗 استدعاء Gemini API

### الـ Endpoint:
```
POST https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=YOUR_API_KEY
```

### شكل الطلب:
```json
{
  "contents": [
    {
      "parts": [
        {
          "text": "prompt text here"
        }
      ]
    }
  ],
  "generationConfig": {
    "responseMimeType": "application/json"
  }
}
```

### شكل الاستجابة المتوقعة:
```json
{
  "overall_score": 0-100,
  "platform_focus": "TikTok/Instagram/Facebook",
  "strengths": ["نقطة قوة 1", "نقطة قوة 2", "نقطة قوة 3"],
  "weaknesses": ["نقطة ضعف 1", "نقطة ضعف 2", "نقطة ضعف 3"],
  "improvement_suggestions": ["اقتراح 1", "اقتراح 2", "اقتراح 3", "اقتراح 4", "اقتراح 5"],
  "predicted_performance": "عالي/متوسط/منخفض",
  "reasoning": "شرح مختصر للتقييم"
}
```

---

## 🛠️ ملفات Hilt Modules

### NetworkModule
- إنشاء OkHttpClient مع logging interceptor
- إنشاء Retrofit instance مع Moshi converter
- توفير GeminiApiService

### DatabaseModule
- إنشاء AppDatabase instance
- توفير AnalysisDao

### RepositoryModule
- توفير AnalysisRepository بـ dependencies الصحيحة

---

## 📝 نقاط مهمة أثناء التطوير

1. **Use Cases**: تجنب وضع كل المنطق في ViewModel، استخدم Use Cases
2. **Error Handling**: جميع عمليات الـ API يجب أن تحتوي على try-catch
3. **Loading States**: استخدم `isLoading` في UiState
4. **Navigation**: استخدم Navigation Compose فقط
5. **Composables**: جعل الـ Composables قابلة لإعادة الاستخدام
6. **Testing**: اختبر الـ ViewModels و Use Cases
7. **Theming**: استخدم Material 3 theming

---

## 🚀 خطوات البناء والتشغيل

1. استنسخ المشروع
2. أنشئ ملف `local.properties` بـ `GEMINI_API_KEY`
3. افتح في Android Studio
4. اضغط Build > Make Project
5. شغّل على محاكي أو جهاز حقيقي

---

## 📞 ملاحظات للوكيل

- تأكد من أن جميع الملفات تحتوي على اسم المطور BliDaoui حسب الحاجة
- استخدم دائماً Kotlin idioms و best practices
- اكتب comments واضحة باللغة العربية والإنجليزية
- تجنب hardcoding، استخدم resources و BuildConfig
- اتبع نمط Clean Architecture بدقة
- اختبر الكود محليًا قبل الـ push

---

**آخر تحديث**: October 2026
