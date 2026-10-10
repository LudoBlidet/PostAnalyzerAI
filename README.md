# 📱 محلل المنشورات الذكي - PostAnalyzerAI

تطبيق أندرويد متقدم لتحليل المنشورات باستخدام الذكاء الاصطناعي (Google Gemini API)

## ✨ الميزات

✅ تحليل النصوص والصور والفيديوهات  
✅ تقييم الأداء على TikTok و Instagram و Facebook  
✅ معايير تحليل متقدمة ودقيقة  
✅ تقارير شاملة مع نقاط القوة والضعف  
✅ اقتراحات محددة للتحسين  
✅ حفظ محلي للتحليلات السابقة  
✅ مشاركة النتائج بسهولة  
✅ واجهة مستخدم حديثة بـ Material Design 3  

## 🏗️ البنية التقنية

- **اللغة**: Kotlin  
- **UI**: Jetpack Compose (Material 3)  
- **المعمارية**: MVVM + Clean Architecture  
- **Dependency Injection**: Hilt  
- **Database**: Room  
- **Networking**: Retrofit + OkHttp + Moshi  
- **Image Loading**: Coil  
- **AI**: Google Gemini API (gemini-2.0-flash)  
- **Min SDK**: 24 | **Target SDK**: 35  

## 📦 الاعتماديات الرئيسية

```kotlin
// Compose
implementation(platform("androidx.compose:compose-bom:2024.09.00"))
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.material3:material3")
implementation("androidx.navigation:navigation-compose:2.8.0")

// Hilt
implementation("com.google.dagger:hilt-android:2.51.1")

// Room
implementation("androidx.room:room-runtime:2.6.1")
implementation("androidx.room:room-ktx:2.6.1")

// Retrofit + Moshi
implementation("com.squareup.retrofit2:retrofit:2.11.0")
implementation("com.squareup.retrofit2:converter-moshi:2.11.0")
implementation("com.squareup.okhttp3:okhttp:4.12.1")

// Coil
implementation("io.coil-kt:coil-compose:2.7.0")
```

## 🚀 التثبيت والتشغيل

### المتطلبات
- Android Studio 2024.1 أو أحدث
- Android SDK 35
- Java 17 أو أحدث
- مفتاح Google Gemini API

### الخطوات

1. **استنسخ المشروع**
   ```bash
   git clone https://github.com/LudoBlidet/PostAnalyzerAI.git
   cd PostAnalyzerAI
   ```

2. **احصل على مفتاح Gemini API**
   - اذهب إلى [Google AI Studio](https://aistudio.google.com/)
   - انسخ مفتاحك

3. **أضف المفتاح إلى `local.properties`**
   ```properties
   GEMINI_API_KEY=YOUR_KEY_HERE
   ```

4. **افتح المشروع في Android Studio**
   - File → Open → اختر المشروع

5. **شغّل التطبيق**
   - اضغط Run أو Shift+F10

## 📱 الشاشات الرئيسية

### 🏠 الصفحة الرئيسية
- عنوان التطبيق: "محلل المنشورات الذكي"
- أربعة خيارات:
  - تحليل نص
  - تحليل صورة
  - تحليل فيديو
  - السجل

### 📝 تحليل النص
- حقل إدخال نصي كبير
- اختيار المنصة (TikTok/Instagram/Facebook)
- مؤشر تحميل أثناء التحليل
- عرض الأخطاء بوضوح

### 🖼️ تحليل الصورة
- اختيار صورة من المعرض
- معاينة الصورة
- حقل وصف اختياري
- نفس معالجة المنصة والتحليل

### 🎬 تحليل الفيديو
- اختيار فيديو من المعرض
- حقل وصف الفيديو
- معالجة متطابقة مع الصور

### 📊 شاشة النتائج
- **التقييم العام** (0-100) مع شريط تقدم ملوّن
- **نقاط القوة** ✓
- **نقاط الضعف** ✗
- **اقتراحات التحسين** 💡
- **التفسير المفصل**
- أزرار: مشاركة، حفظ، تحليل جديد

### 📜 شاشة السجل
- عرض جميع التحليلات السابقة
- فتح أي تحليل سابق
- حذف التحليلات

## 🔐 أمان مفتاح API

✅ لا يوجد مفتاح API مباشر في الكود  
✅ قراءة المفتاح من `local.properties`  
✅ إستخدام `BuildConfig` للوصول الآمن  
✅ `local.properties` مستثنى من `.gitignore`  

## 📊 معايير التحليل

### TikTok
- قوة الخطاف في أول 1-3 ثواني (90% من النجاح)
- معدل الإكمال (يجب أن يتجاوز 70%)
- قابلية إعادة المشاهدة
- قابلية المشاركة والحفظ

### Instagram
- معدل الإكمال ومشاهدة الفيديو كاملاً
- عدد مرات إعادة المشاهدة
- المشاركة عبر الرسائل الخاصة (DM)
- جودة الخطاف

### Facebook
- التفاعل النوعي (تعليقات حقيقية، مشاركات)
- وقت المشاهدة الكلي
- ارتباط المحتوى باهتمامات الجمهور

## 🛠️ البنية المشروع

```
PostAnalyzerAI/
├── app/
│   ├── src/main/
│   │   ├── java/com/blidaoui/postanalyzer/
│   │   │   ├── MainActivity.kt
│   │   │   ├── PostAnalyzerApp.kt
│   │   │   ├── data/
│   │   │   │   ├── local/ (Room Database)
│   │   │   │   ├── remote/ (Gemini API)
│   │   │   │   └── repository/ (Data Layer)
│   │   │   ├── domain/
│   │   │   │   ├── model/ (Domain Models)
│   │   │   │   └── usecase/ (Use Cases)
│   │   │   ├── presentation/
│   │   │   │   ├── home/
│   │   │   │   ├── text/
│   │   │   │   ├── image/
│   │   │   │   ├── video/
│   │   │   │   ├── result/
│   │   │   │   ├── history/
│   │   │   │   ├── common/
│   │   │   │   ├── viewmodel/
│   │   │   │   └── navigation/
│   │   │   └── di/ (Hilt Modules)
│   │   ├── AndroidManifest.xml
│   │   └── res/
│   ├── build.gradle.kts
│   └── local.properties (مستثنى من git)
├── build.gradle.kts
├── settings.gradle.kts
├── .gitignore
└── README.md
```

## 🔄 Flow التطبيق

```
Home Screen
    ↓
┌─────────────────────────┬─────────────────┬─────────────────┐
│                         │                 │                 │
Text Analysis Screen   Image Analysis     Video Analysis    History Screen
│                      Screen             Screen            │
└─────────────┬──────────┴─────────────────┴────────────────┴──
              │
              ↓
        Gemini API Analysis
              │
              ↓
        Result Screen (Display & Share)
              │
              ↓
        Local Storage (Room Database)
              │
              ↓
        History Screen
```

## 📝 ملف Moshi Adapter

يتم توليد adapters Moshi تلقائياً باستخدام KSP (Kotlin Symbol Processing):

```kotlin
@JsonClass(generateAdapter = true)
data class AnalysisResult(
    @Json(name = "overallScore") val overallScore: Int,
    @Json(name = "platformFocus") val platformFocus: String,
    // ...
)
```

## 🧪 الاختبار

### نص تجريبي
```
منتج جديد رائع! يحسّن إنتاجيتك بنسبة 300%.
جودة عالية وسعر منخفض. لا تفوّت هذه الفرصة!
#حصري #محدود #الآن
```

### صورة تجريبية
اختر أي صورة ذات محتوى جاذب وقم بوصفها

### فيديو تجريبي
اختر أي فيديو وأضف وصفاً للمحتوى

## 🐛 استكشاف الأخطاء

### المشكلة: مفتاح API غير صحيح
**الحل**: تأكد من نسخ مفتاحك بشكل صحيح في `local.properties`

### المشكلة: لا توجد بيانات في السجل
**الحل**: تأكد من تنفيذ تحليل واحد على الأقل

### المشكلة: لا يمكن اختيار صورة/فيديو
**الحل**: تأكد من منح الصلاحيات في إعدادات الهاتف

## 📞 الدعم

للمزيد من المساعدة:
- ✅ تحقق من `Agent.md` للمتطلبات التقنية
- ✅ راجع سجل التطبيق (Logcat) للأخطاء
- ✅ تأكد من اتصالك بالإنترنت
- ✅ تحديث جميع المكتبات

## 📄 الترخيص

هذا المشروع مفتوح المصدر.

## 👨‍💻 المطور

**BliDaoui** - 2026

تم تطوير هذا التطبيق بعناية وإتقان!

---

**شكراً لاستخدام محلل المنشورات الذكي! 🚀**
